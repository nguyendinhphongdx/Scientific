package com.example.scientificresearch.Model;

public class User {
    String _id;
    String fullname;
    String password;
    String mail;
    String phone;
    imageClass image ;
    public User() {
    }

    public User(String _id, String fullname, String password, String mail, String phone, imageClass image) {
        this._id = _id;
        this.fullname = fullname;
        this.password = password;
        this.mail = mail;
        this.phone = phone;
        this.image = image;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public imageClass getImage() {
        return image;
    }

    public void setImage(imageClass image) {
        this.image = image;
    }

    public static  class imageClass{
        String image;
        String url;

        public imageClass(String image, String url) {
            this.image = image;
            this.url = url;
        }
    }
}



