package com.example.scientificresearch.Model.Student;

import java.io.Serializable;

public class Student implements Serializable {
    private String status;
    private String _id;
    private String name;
    private Long age;
    private String email;
    private String image;

    public Student(String status, String id, String name, Long age, String email, String image) {
        this.status = status;
        this._id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.image = image;
    }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public String getID() { return _id; }
    public void setID(String value) { this._id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public Long getAge() { return age; }
    public void setAge(Long value) { this.age = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }

    public String getImage() { return image; }
    public void setImage(String value) { this.image = value; }
}
