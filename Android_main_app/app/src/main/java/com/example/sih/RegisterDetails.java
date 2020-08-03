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

    @SerializedName("fuel_tank_capacity")
    @Expose
    private
    String fuel_tank_capacity;

    public String getFuel_tank_capacity() {
        return fuel_tank_capacity;
    }

    public void setFuel_tank_capacity(String fuel_tank_capacity) {
        this.fuel_tank_capacity = fuel_tank_capacity;
    }



    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public RegisterDetails(String name , String phone, String password,String fuel_tank_capacity){
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.fuel_tank_capacity = fuel_tank_capacity;
    }
}
