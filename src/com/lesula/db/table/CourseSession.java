package com.lesula.db.table;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by enrico on 16/11/14.
 */
@Entity
public class CourseSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private Long id;

    public CourseSession(){

    }
    public CourseSession(Course course, Date startDate, Date endDate) {
        this.course = course;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Temporal(TemporalType.DATE)
    @JoinColumn(name="StartDate")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @JoinColumn(name="EndDate")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name="CourseId")
    private Course course;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private Set<Exam> exams;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }
}
