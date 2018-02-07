package com.vijay.ihc.security.app;

public class AppSecretException extends RuntimeException {

    private static final long serialVersionUID = 8625269228585444862L;

    public AppSecretException(String msg){
        super(msg);
    }
}
