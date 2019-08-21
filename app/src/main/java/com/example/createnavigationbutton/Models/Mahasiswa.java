package com.example.createnavigationbutton.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Mahasiswa {

    @SerializedName("id")
    private int id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("value")
    private String value;

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private List<Mahasiswa> result;

    public Mahasiswa(List<Mahasiswa> result) {
        this.result = result;
    }

    public List<Mahasiswa> getResult() {
        return result;
    }

    public void setResult(List<Mahasiswa> result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
