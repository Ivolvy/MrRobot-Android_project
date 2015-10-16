package com.example.mgenty.mrrobot_android_project.communication;

import com.tozny.crypto.android.AesCbcWithIntegrity;

import static com.example.mgenty.mrrobot_android_project.crypto.Crypto.decipherMessage;

public class Chat {

    private String message;
    private String author;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private Chat() {
    }

    public Chat(String author, AesCbcWithIntegrity.CipherTextIvMac message) {
        this.message = message.toString();
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }
}
