package com.example.sih;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AuthResponse implements Serializable {
    @SerializedName("name")
    @Expose
    private
    String name;

    @SerializedName("token")
    @Expose
    private
    String token;

    @SerializedName("phone")
    @Expose
    private
    String phone;

    @SerializedName("password")
    @Expose
    private
    String password;

    @SerializedName("message")
    @Expose
    private
    String message;

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getMessage() {
        return message;
    }
}
