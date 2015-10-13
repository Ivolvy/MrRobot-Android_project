package com.example.mgenty.mrrobot_android_project;

import android.util.Base64;
import android.util.Log;

import com.tozny.crypto.android.AesCbcWithIntegrity;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public class Crypto {
    public static void TestCrypto() {
        AesCbcWithIntegrity.SecretKeys keys = null;
        try {
            keys = AesCbcWithIntegrity
                    .generateKeyFromPassword("password", Base64.encodeToString("salt".getBytes(), Base64.DEFAULT));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        assert keys != null;

        AesCbcWithIntegrity.CipherTextIvMac encrypted = null;
        try {
            encrypted = AesCbcWithIntegrity.encrypt("secret", keys);
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            e.printStackTrace();
        }

        assert encrypted != null;

        String decrypted = null;
        try {
            decrypted = AesCbcWithIntegrity.decryptString(encrypted, keys);
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            e.printStackTrace();
        }

        assert decrypted == "secret";

        Log.d("Crypto", "Crypto is working!"
                        + "\n keys: " + keys.toString()
                        + "\n encrypted: " + encrypted.toString()
                        + "\n decrypted: " + decrypted.toString()
        );
    }
}
