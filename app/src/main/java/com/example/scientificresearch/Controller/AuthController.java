package com.example.scientificresearch.Controller;

import android.content.Context;

import com.example.scientificresearch.Common.DatabaseHandler;
import com.example.scientificresearch.Model.Account;
import com.example.scientificresearch.Model.Memory;

import java.util.List;

public class AuthController {
    private String keyAccount = "account";
    public AuthController() {
    }

    public void RememberAccount(Account account, Context context){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseHandler handler = new DatabaseHandler(context);
                Memory memory = new Memory(keyAccount,account.getStringObject());
                if(handler.existsMemory(keyAccount)){
                    handler.updateMemory(memory);
                }else{
                    handler.addMemory(memory);
                }
            }
        }).start();
    }
    public Memory GetRememberAccount(Context context){
        final Memory[] memory = {new Memory()};
        DatabaseHandler handler = new DatabaseHandler(context);
        if(handler.existsMemory(keyAccount)){
            memory[0] = handler.getMemory(keyAccount);
        }
        return memory[0];
    }
}
