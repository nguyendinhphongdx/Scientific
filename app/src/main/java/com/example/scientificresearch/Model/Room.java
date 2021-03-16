package com.example.scientificresearch.Model;

import java.io.Serializable;
import java.util.List;

public class Room {
    String _id;
    String name;
    List<User> users;

    public Room(String _id, String name) {
        this._id = _id;
        this.name = name;
    }
    public Room() {
    }
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


}

