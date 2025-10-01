package com.v_ia_backend.kipa.config;

import java.io.Serializable;

import com.v_ia_backend.kipa.modelview.UserView;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
    private final UserView user;

    public JwtResponse(String token, UserView user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserView getUser(){
        return user;
    }
}
