package com.example.scientificresearch.Model.Class;

import com.example.scientificresearch.Model.Subject.Subject;

import java.util.List;

public class Class {
    private List<Member> member;
    private Professor professor;
    private String status;
    private Long startDate;
    private String _id;
    private String name;
    private Schedule schedule1;
    private Schedule schedule2;
    private List<Subject> subject;

    public Class() {
    }

    public Class(List<Member> member, Professor professor, String status, Long startDate, String _id, String name, Schedule schedule1, Schedule schedule2, List<Subject> subject) {
        this.member = member;
        this.professor = professor;
        this.status = status;
        this.startDate = startDate;
        this._id = _id;
        this.name = name;
        this.schedule1 = schedule1;
        this.schedule2 = schedule2;
        this.subject = subject;
    }

    public List<Member> getMember() { return member; }
    public void setMember(List<Member> value) { this.member = value; }

    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor value) { this.professor = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public Long getStartDate() { return startDate; }
    public void setStartDate(Long value) { this.startDate = value; }

    public String getID() { return _id; }
    public void setID(String value) { this._id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public Schedule getSchedule1() { return schedule1; }
    public void setSchedule1(Schedule value) { this.schedule1 = value; }

    public Schedule getSchedule2() { return schedule2; }
    public void setSchedule2(Schedule value) { this.schedule2 = value; }

    public List<Subject> getSubject() { return subject; }
    public void setSubject(List<Subject> value) { this.subject = value; }
}
