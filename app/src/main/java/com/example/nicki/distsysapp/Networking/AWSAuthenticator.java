package com.example.nicki.distsysapp.Networking;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.util.escape.CharEscapers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.TreeSet;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by hjorthen on 4/28/17.
 */

class Crypto{
    /**

     Begin copy from: http://docs.aws.amazon.com/general/latest/gr/signature-v4-examples.html#signature-v4-examples-java
     Dato: 14/3/2017 kl. 00:34
     *
     */

    public static byte[] HmacSHA256(String data, byte[] key) {
        try{
        String algorithm="HmacSHA256";
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(key, algorithm));
        return mac.doFinal(data.getBytes("UTF8"));
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] getSignatureKey(String key, String dateStamp, String regionName, String serviceName){
        byte[] kSecret = null;
        try {
            kSecret = ("AWS4" + key).getBytes("UTF8");
            byte[] kDate = HmacSHA256(dateStamp, kSecret);
            byte[] kRegion = HmacSHA256(regionName, kDate);
            byte[] kService = HmacSHA256(serviceName, kRegion);
            byte[] kSigning = HmacSHA256("aws4_request", kService);
            return kSigning;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**

     End copy from: http://docs.aws.amazon.com/general/latest/gr/signature-v4-examples.html#signature-v4-examples-java

     */

    public static String byteToHexString(byte[] bytes)
    {
        StringBuilder hexes = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            hexes.append(String.format("%02x", bytes[i]));
        }
        return hexes.toString();
    }

    public static byte[] sha256(String s) throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(s.getBytes(StandardCharsets.UTF_8));

    }

}

public class AWSAuthenticator implements HttpExecuteInterceptor {
    final static String roleArn = "arn:aws:iam::619517212226:role/Cognito_WEWOUsersAuth_Role";
    final static String version = "2011-06-15";
    final static String action = "AssumeRoleWithWebIdentity";
    final static String stsUrl = "https://sts.amazonaws.com/";
    final static String tmpAccessUrl = stsUrl + "?Action=" + action + "&RoleArn=" + roleArn + "&Version=" + version;
    private String clientSecret;
    private String accessKey;

    public AWSAuthenticator(String clientSecret, String accessKey){
        this.clientSecret = clientSecret;
        this.accessKey = accessKey;
    }
    private static String getDateStamp(Date d)
    {
        //return "20170312";
        SimpleDateFormat datestamp = new SimpleDateFormat("YYYYMMdd");
        datestamp.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
        return datestamp.format(d);
    }

    private static String getAmzDate(Date d)
    {
        //return "20170312T133959Z";
        SimpleDateFormat amzdate = new SimpleDateFormat("YYYYMMdd'T'HHmmss'Z'");
        amzdate.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
        return amzdate.format(d);
    }

    private static class Entry implements Comparator<Entry>{
        public Entry(String key, String value){
            this.key = key;
            this.value = value;
        }

        public String key;
        public String value;

        @Override
        public int compare(Entry o1, Entry o2) {
            return o1.key.compareTo(o2.key);
        }
    }

    private static void constructCanonicalQuery(GenericUrl url, StringBuilder queryBuilder){
        TreeSet<Entry> queries = new TreeSet<Entry>(); //Collects all keys to sort

        Set<Map.Entry<String, Object>> set = url.entrySet();
        for(Map.Entry<String, Object> entry : set){
            if(entry.getValue() instanceof Collection<?>){ //Supporting duplicate keys
                Collection<?> coll = (Collection<?>) entry.getValue();
                for(Object replicatedKey :  coll){
                    queries.add(new Entry(entry.getKey(), coll.toString()));
                }
            }
            else
            {
               queries.add(new Entry(entry.getKey(), entry.getValue().toString()));
            }
        }

        for(Entry sortedEntry : queries){
            queryBuilder.append(sortedEntry.key);
            queryBuilder.append('=');
            queryBuilder.append(CharEscapers.escapeUriQuery(sortedEntry.value));
            queryBuilder.append('&');
        }
        queryBuilder.deleteCharAt(queryBuilder.length() - 1); //Removes trailing &
    }

    private static void buildCanonicalHeaders(HttpRequest request, StringBuilder builder, StringBuilder signedHeaders){
        HttpHeaders headers = request.getHeaders();
        Set<Map.Entry<String, Object>> set = headers.entrySet();
        TreeSet<Entry> sortedSet = new TreeSet<Entry>();
        for(Map.Entry<String, Object> entry : set)
        {
            sortedSet.add(new Entry(entry.getKey().toLowerCase(), entry.getValue().toString()));
        }
        for(Entry entry : sortedSet){
            signedHeaders.append(entry.key);
            signedHeaders.append(';');
            builder.append(entry.key);
            builder.append(':');
            builder.append(entry.value);
            builder.append('\n');
        }
        signedHeaders.deleteCharAt(signedHeaders.length() - 1); //Removes trailing ;
        builder.append('\n');
        builder.append(signedHeaders);
    }

    private String buildCanonicalRequest(HttpRequest request, StringBuilder signedHeaders) {
        StringBuilder builder = new StringBuilder();
        builder.append(request.getRequestMethod());
        builder.append('\n');
        String path = request.getUrl().getRawPath();
        if(path == null)
            path = "/";
        builder.append(path);
        builder.append('\n');
        constructCanonicalQuery(request.getUrl(), builder);
        builder.append('\n');
        buildCanonicalHeaders(request, builder, signedHeaders);
        builder.append('\n');
        try {
            builder.append(Crypto.byteToHexString(Crypto.sha256(request.getContent().toString())));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            //Java standard defines SHA256 always is available, so we should never reach this point...
        }
        return builder.toString();
    }

    @Override
    public void intercept(HttpRequest request) throws IOException {
        Date d = new Date();
        StringBuilder hackFix = new StringBuilder();
        String canonicalRequest = buildCanonicalRequest(request, hackFix);
        String canonicalHash = null;
        try {
            canonicalHash = Crypto.byteToHexString(Crypto.sha256(canonicalRequest));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String algorithm = "AWS4-HMAC-SHA256";
        String credential_scope = getDateStamp(d) + "/" + "eu-west-1" + "/" + "execute-api" + "/" + "aws4_request";
        String signString = algorithm + "\n" + getAmzDate(d) + "\n" + credential_scope + "\n" + canonicalHash; //Task 2 - done
        byte[] signKey = Crypto.getSignatureKey(clientSecret, getDateStamp(d), "eu-west-1", "execute-api");
        String signature = Crypto.byteToHexString(Crypto.HmacSHA256(signString, signKey)); //Task 3 - done
        String authHeader = algorithm + ' ' + "Credential=" + accessKey + "/" + credential_scope + ", " + "SignedHeaders=" + hackFix.toString() + ", Signature=" + signature;
        HttpHeaders headers = request.getHeaders();
        headers.setAuthorization(authHeader);
        headers.set("x-amz-date", getAmzDate(d));
    }
}
