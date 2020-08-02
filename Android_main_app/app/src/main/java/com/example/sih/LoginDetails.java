package com.example.sih;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginDetails implements Serializable {
    @SerializedName("phone")
    @Expose
    String phone;

    @SerializedName("password")
    @Expose
    String password;

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public LoginDetails(String phone,String password){
        this.password = password;
        this.phone = phone;
    }

}
