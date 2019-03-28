package com.example.instantsapp.model.modelodados;

import com.squareup.moshi.Json;

public class InfoLogin {
    @Json(name = "id")
    private String id;
    @Json(name = "passwd")
    private String passwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
