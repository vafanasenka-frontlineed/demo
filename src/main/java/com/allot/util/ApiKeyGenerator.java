package com.allot.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Random;


@Slf4j
@Component
public class ApiKeyGenerator {

    @Value("${allot.superkey.name}")
    protected String superKeyName;

    @Value("${allot.superkey.pwd}")
    protected String superKeyPwd;

    private static final String HMAC256 = "HmacSHA256";

    @Nullable
    public String generate() {
        String timestamp = String.valueOf(System.currentTimeMillis()).substring(0, 10);
        String randomHex = getRandomHex(8);
        String message = String.join(":", superKeyName, timestamp, randomHex);
        String encodedhash;
        try {
            encodedhash = calculateHMAC(message, superKeyPwd, HMAC256);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            return null;
        }
        String apikey = String.join(":", superKeyName, timestamp, randomHex, encodedhash);
        log.info("API Key generated : {}", apikey);
        return apikey;
    }

    private String getRandomHex(int size) {
        byte[] resBuf = new byte[size];
        new Random().nextBytes(resBuf);
        return new String(Hex.encodeHex(resBuf));
    }

    /**
     * Method that encrypt the data by specified algorithm and convert into hex values
     */
    private String calculateHMAC(String msg, String key, String algorithm) throws NoSuchAlgorithmException,
            InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
        Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
        mac.init(secretKeySpec);
        return toHexString(mac.doFinal(msg.getBytes(StandardCharsets.UTF_8)));
    }

    private String toHexString(byte[] bytes) {
        StringBuilder hash = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                hash.append('0');
            }
            hash.append(hex);
        }
        return hash.toString();
    }

}
