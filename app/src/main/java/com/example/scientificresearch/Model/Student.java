package com.example.scientificresearch.Model;

import com.example.scientificresearch.Model.Subject.Mark;

import java.util.List;

public class Student {
    private  String name;
    private  int age;
    private  String email;
    private List<String> _class;
    private String status;
    private String phone;
    private String image;
    private String description;
    private String hash_password;
    private List<Mark> mark;

    public Student(String name, int age, String email, List<String> aClass, String status, String phone, String image, String description, String hash_password, List<Mark> mark) {
        this.name = name;
        this.age = age;
        this.email = email;
        _class = aClass;
        this.status = status;
        this.phone = phone;
        this.image = image;
        this.description = description;
        this.hash_password = hash_password;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> get_class() {
        return _class;
    }

    public void set_class(List<String> _class) {
        this._class = _class;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHash_password() {
        return hash_password;
    }

    public void setHash_password(String hash_password) {
        this.hash_password = hash_password;
    }

    public List<Mark> getMark() {
        return mark;
    }

    public void setMark(List<Mark> mark) {
        this.mark = mark;
    }
}



