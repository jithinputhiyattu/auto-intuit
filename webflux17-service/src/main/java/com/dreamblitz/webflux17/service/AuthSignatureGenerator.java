package com.dreamblitz.webflux17.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectStreamException;
import java.nio.charset.StandardCharsets;
import java.security.KeyRep;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.*;
import java.util.stream.Collectors;

public class AuthSignatureGenerator {

    public static void main(String[] args) throws Exception {
        String env = "stg";

        SignatureInput signatureInput = getSignatureInput(env);
        Map<String, String> headerMap =
                SignatureGenerator.getAuthSignature(
                        signatureInput.getConsumerId(),
                        signatureInput.getPrivateKeyVersion(),
                        signatureInput.getPrivateKey());
        List<String> headers =
                headerMap.entrySet().stream()
                        .map(
                                stringStringEntry -> {
                                    String s = stringStringEntry.getKey() + ":" + stringStringEntry.getValue();
                                    System.out.println(s);
                                    return s;
                                })
                        .collect(Collectors.toList());
    }

    public Map<String, String> getApiHeader() throws Exception {
        SignatureInput signatureInput = getSignatureInput("stg");
        Map<String, String> headerMap =
                SignatureGenerator.getAuthSignature(
                        signatureInput.getConsumerId(),
                        signatureInput.getPrivateKeyVersion(),
                        signatureInput.getPrivateKey());
        return headerMap;
    }

    private static SignatureInput getSignatureInput(String env) {
        switch (env) {
         default:
                return new SignatureInput("", "", "");
        }
    }

    private static class SignatureGenerator {

        public static Map<String, String> getAuthSignature(
                String consumerId, String priviateKeyVersion, String privateKey) throws Exception {
            long intimestamp = System.currentTimeMillis();

            Map<String, String> map = new HashMap<>();
            map.put("WM_CONSUMER.ID", consumerId);
            map.put("WM_CONSUMER.INTIMESTAMP", Long.toString(intimestamp));
            map.put("WM_SEC.KEY_VERSION", priviateKeyVersion);

            String[] array = canonicalize(map);

            String authSignature = generateSignature(privateKey, array[1]);
            map.put("WM_SEC.AUTH_SIGNATURE", authSignature);
            return map;
        }

        private static String generateSignature(String key, String stringToSign) throws Exception {
            Signature signatureInstance = Signature.getInstance("SHA256WithRSA");

            ServiceKeyRep keyRep =
                    new ServiceKeyRep(KeyRep.Type.PRIVATE, "RSA", "PKCS#8", Base64.getDecoder().decode(key));

            PrivateKey resolvedPrivateKey = (PrivateKey) keyRep.readResolve();

            signatureInstance.initSign(resolvedPrivateKey);

            byte[] bytesToSign = stringToSign.getBytes(StandardCharsets.UTF_8);
            signatureInstance.update(bytesToSign);
            byte[] signatureBytes = signatureInstance.sign();

            String signatureString = Base64.getEncoder().encodeToString(signatureBytes);


            return signatureString;
        }

        private static String[] canonicalize(Map<String, String> headersToSign) {
            StringBuffer canonicalizedStrBuffer = new StringBuffer();
            StringBuffer parameterNamesBuffer = new StringBuffer();
            Set<String> keySet = headersToSign.keySet();

            // Create sorted key set to enforce order on the key names
            SortedSet<String> sortedKeySet = new TreeSet<String>(keySet);
            for (String key : sortedKeySet) {
                Object val = headersToSign.get(key);
                parameterNamesBuffer.append(key.trim()).append(";");
                canonicalizedStrBuffer.append(val.toString().trim()).append("\n");
            }

            return new String[]{parameterNamesBuffer.toString(), canonicalizedStrBuffer.toString()};
        }

        static class ServiceKeyRep extends KeyRep {
            private static final long serialVersionUID = -7213340660431987616L;

            public ServiceKeyRep(Type type, String algorithm, String format, byte[] encoded) {
                super(type, algorithm, format, encoded);
            }

            protected Object readResolve() throws ObjectStreamException {
                return super.readResolve();
            }
        }
    }

    @Getter
    private static class SignatureInput {
        private String consumerId;
        private String privateKeyVersion;
        private String privateKey;

        public SignatureInput(String consumerId, String privateKeyVersion, String privateKey) {
            this.consumerId = consumerId;
            this.privateKeyVersion = privateKeyVersion;
            this.privateKey = privateKey;
        }
    }
}