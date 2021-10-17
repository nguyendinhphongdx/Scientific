package com.example.scientificresearch.Model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

public class Account {
    String username;
    String password;

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getStringObject() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("username",this.username);
            jsonObject.put("password",this.password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return String.valueOf(jsonObject);
    }
    public Account getAccountFromObject(JSONObject jsonObject) {
        Account account = new Account();
        try {
            account.username = jsonObject.getString("username");
            account.username = jsonObject.getString("password");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return account;
    }
    public Account getAccountFromStringObject(String string) {
        Account account = new Account();
        JsonObject jsonObject = new JsonParser().parse(string).getAsJsonObject();
        account.setUsername(jsonObject.get("username").getAsString());
        account.setPassword(jsonObject.get("password").getAsString());
        return account;
    }
}
