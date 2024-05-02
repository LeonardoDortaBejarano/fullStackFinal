package com.formacion.app.Auth;

public class AuthReponse {

    public static final String HttpStatus = null;
    private String token;

    public AuthReponse() {
    }

    public AuthReponse(String token) {
        this.token = token;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }





}
