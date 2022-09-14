package com.tencent.kona.crypto.provider;

import com.tencent.kona.crypto.TestUtils;
import com.tencent.kona.crypto.spec.SM2PrivateKeySpec;
import com.tencent.kona.crypto.spec.SM2PublicKeySpec;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.KeySpec;

import static com.tencent.kona.crypto.TestUtils.PROVIDER;

/**
 * The test for SM2KeyFactory.
 */
public class SM2KeyFactoryTest {

    @BeforeAll
    public static void setup() {
        TestUtils.addProviders();
    }

    @Test
    public void testGetKeySpecs() throws Exception {
        KeyPairGenerator keyPairGenerator
                = KeyPairGenerator.getInstance("SM2", PROVIDER);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        KeyFactory keyFactory = KeyFactory.getInstance("SM2", PROVIDER);

        KeySpec pubKeySpec = keyFactory.getKeySpec(
                keyPair.getPublic(), SM2PublicKeySpec.class);
        Assertions.assertNotNull(pubKeySpec);

        KeySpec priKeySpec = keyFactory.getKeySpec(
                keyPair.getPrivate(), SM2PrivateKeySpec.class);
        Assertions.assertNotNull(priKeySpec);
    }

    @Test
    public void testGenerateRawKeys() throws Exception {
        KeyPairGenerator keyPairGenerator
                = KeyPairGenerator.getInstance("SM2", PROVIDER);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        KeyFactory keyFactory = KeyFactory.getInstance("SM2", PROVIDER);

        KeySpec publicKeySpec = keyFactory.getKeySpec(
                publicKey, SM2PublicKeySpec.class);
        ECPublicKey ecPublicKey = (ECPublicKey) keyFactory.generatePublic(
                publicKeySpec);
        Assertions.assertArrayEquals(
                publicKey.getEncoded(), ecPublicKey.getEncoded());

        KeySpec privateKeySpec = keyFactory.getKeySpec(
                privateKey, SM2PrivateKeySpec.class);
        ECPrivateKey ecPrivateKey = (ECPrivateKey) keyFactory.generatePrivate(
                privateKeySpec);
        Assertions.assertArrayEquals(
                privateKey.getEncoded(), ecPrivateKey.getEncoded());
    }
}
