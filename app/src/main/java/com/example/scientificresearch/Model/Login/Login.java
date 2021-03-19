package com.example.scientificresearch.Model.Login;
import java.time.OffsetDateTime;
import  com.example.scientificresearch.Model.Login.Student;
import com.example.scientificresearch.Model.Subject.Mark;

public class Login {
    private String token;
    private OffsetDateTime start;
    private OffsetDateTime end;
    private Student student;

    public String getToken() { return token; }
    public void setToken(String value) { this.token = value; }

    public OffsetDateTime getStart() { return start; }
    public void setStart(OffsetDateTime value) { this.start = value; }

    public OffsetDateTime getEnd() { return end; }
    public void setEnd(OffsetDateTime value) { this.end = value; }

    public Student getStudent() { return student; }
    public void setStudent(Student value) { this.student = value; }
}
class Student {
    private String[] studentClass;
    private String status;
    private String id;
    private String name;
    private String hashPassword;
    private Long age;
    private String email;
    private String image;
    private Mark[] mark;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private Long v;

    public String[] getStudentClass() { return studentClass; }
    public void setStudentClass(String[] value) { this.studentClass = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getHashPassword() { return hashPassword; }
    public void setHashPassword(String value) { this.hashPassword = value; }

    public Long getAge() { return age; }
    public void setAge(Long value) { this.age = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }

    public String getImage() { return image; }
    public void setImage(String value) { this.image = value; }

    public Mark[] getMark() { return mark; }
    public void setMark(Mark[] value) { this.mark = value; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime value) { this.createdAt = value; }

    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime value) { this.updatedAt = value; }

    public Long getV() { return v; }
    public void setV(Long value) { this.v = value; }
}