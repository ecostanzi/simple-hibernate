package com.lesula.db.table;

import javax.persistence.*;

/**
 * Created by enrico on 16/11/14.
 */
@Entity
public class ExamResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="StudentId")
    private Student student;

    @ManyToOne
    @JoinColumn(name="ExamId")
    private Exam exam;

    @Column(name="Result")
    private Integer result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
