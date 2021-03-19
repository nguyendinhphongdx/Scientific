package com.example.scientificresearch.Model.Subject;

public class Mark {
        private Subject subject;
        private String id;
        private Final middle;
        private Final test;
        private Final markFinal;

        public Subject getSubject() { return subject; }
        public void setSubject(Subject value) { this.subject = value; }

        public String getID() { return id; }
        public void setID(String value) { this.id = value; }

        public Final getMiddle() { return middle; }
        public void setMiddle(Final value) { this.middle = value; }

        public Final getTest() { return test; }
        public void setTest(Final value) { this.test = value; }

        public Final getMarkFinal() { return markFinal; }
        public void setMarkFinal(Final value) { this.markFinal = value; }
}
class Final {
    private String mark;
    private Long date;

    public String getMark() { return mark; }
    public void setMark(String value) { this.mark = value; }

    public Long getDate() { return date; }
    public void setDate(Long value) { this.date = value; }
}