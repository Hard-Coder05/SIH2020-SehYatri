package com.example.sih;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisterDetails implements Serializable {

    @SerializedName("name")
    @Expose
    String name;

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

    public String getName() {
        return name;
    }

    public RegisterDetails(String name , String phone, String password){
        this.name = name;
        this.password = password;
        this.phone = phone;
    }
}
