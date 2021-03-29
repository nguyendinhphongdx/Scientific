package com.example.scientificresearch.Model.Mark;

import com.example.scientificresearch.Model.Final.Final;
import com.example.scientificresearch.Model.Subject.Subject;

public class Mark {
    private Subject subject;
    private String id;
    private Final middle;
    private Final test;
    private Final markFinal;

    public Mark(Subject subject, String id, Final middle, Final test, Final markFinal) {
        this.subject = subject;
        this.id = id;
        this.middle = middle;
        this.test = test;
        this.markFinal = markFinal;
    }

    public Mark() {
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Final getMiddle() {
        return middle;
    }

    public void setMiddle(Final middle) {
        this.middle = middle;
    }

    public Final getTest() {
        return test;
    }

    public void setTest(Final test) {
        this.test = test;
    }

    public Final getMarkFinal() {
        return markFinal;
    }

    public void setMarkFinal(Final markFinal) {
        this.markFinal = markFinal;
    }
}
