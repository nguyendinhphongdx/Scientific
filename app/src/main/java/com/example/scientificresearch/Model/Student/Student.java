package com.example.scientificresearch.Model.Student;

import com.example.scientificresearch.Model.Mark.Mark;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class Student implements Serializable {
    @SerializedName("studentClass")
    @Expose
    private String[] studentClass;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("hashPassword")
    @Expose
    private String hashPassword;
    @SerializedName("age")
    @Expose
    private Long age;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("mark")
    @Expose
    private Mark[] mark;
    @SerializedName("createdAt")
    @Expose
    private OffsetDateTime createdAt;
    @SerializedName("updatedAt")
    @Expose
    private OffsetDateTime updatedAt;

    public Student() {
    }

    public Student(String[] studentClass, String status, String id, String name, String hashPassword, Long age, String email, String image, Mark[] mark, OffsetDateTime createdAt, OffsetDateTime updatedAt, Long v) {
        this.studentClass = studentClass;
        this.status = status;
        this.id = id;
        this.name = name;
        this.hashPassword = hashPassword;
        this.age = age;
        this.email = email;
        this.image = image;
        this.mark = mark;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.v = v;
    }

    private Long v;

    public String[] getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String[] studentClass) {
        this.studentClass = studentClass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Mark[] getMark() {
        return mark;
    }

    public void setMark(Mark[] mark) {
        this.mark = mark;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getV() {
        return v;
    }

    public void setV(Long v) {
        this.v = v;
    }
}
