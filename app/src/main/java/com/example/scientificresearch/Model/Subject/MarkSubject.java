package com.example.scientificresearch.Model.Subject;

public class MarkSubject {
    private Subject subject;
    private Mark test;
    private Mark middle;
    private Mark _final;
    private int total;

    public MarkSubject(Subject subject, Mark test, Mark middle, Mark aFinal, int total) {
        this.subject = subject;
        this.test = test;
        this.middle = middle;
        _final = aFinal;
        this.total = total;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Mark getTest() {
        return test;
    }

    public void setTest(Mark test) {
        this.test = test;
    }

    public Mark getMiddle() {
        return middle;
    }

    public void setMiddle(Mark middle) {
        this.middle = middle;
    }

    public Mark get_final() {
        return _final;
    }

    public void set_final(Mark _final) {
        this._final = _final;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
