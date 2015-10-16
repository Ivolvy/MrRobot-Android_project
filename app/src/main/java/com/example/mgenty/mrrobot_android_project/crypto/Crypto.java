package com.example.mgenty.mrrobot_android_project.crypto;


import android.util.Base64;
import android.util.Log;
import com.tozny.crypto.android.AesCbcWithIntegrity;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public class Crypto {
    private static final String TAG = "RsaEcb";

    public static AesCbcWithIntegrity.CipherTextIvMac cipherMessage(String messageToCipher, String password) {

        AesCbcWithIntegrity.SecretKeys keys = null;
        try {
            keys = AesCbcWithIntegrity
                    .generateKeyFromPassword(password, Base64.encodeToString("salt".getBytes(), Base64.DEFAULT));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        if (keys == null) throw new AssertionError();

        AesCbcWithIntegrity.CipherTextIvMac encrypted = null;
        try {
            encrypted = AesCbcWithIntegrity.encrypt(messageToCipher, keys);
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            e.printStackTrace();
        }

        if (encrypted == null) throw new AssertionError();

        return encrypted;
    }

    public static String decipherMessage(AesCbcWithIntegrity.CipherTextIvMac encryptedMessage, String password) {

        AesCbcWithIntegrity.SecretKeys keysDecrypt = null;
        String decrypted = null;
        try {
            keysDecrypt = AesCbcWithIntegrity
                    .generateKeyFromPassword(password, Base64.encodeToString("salt".getBytes(), Base64.DEFAULT));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        try {
            if (keysDecrypt != null) {
                decrypted = AesCbcWithIntegrity.decryptString(encryptedMessage, keysDecrypt);
            }
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            e.printStackTrace();
        }

        /*if (decrypted != null) {
            if (!decrypted.equals("secret")) throw new AssertionError();
        }*/

        return decrypted;
    }

}
