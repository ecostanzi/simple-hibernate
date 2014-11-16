package com.lesula.db.table;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by enrico on 16/11/14.
 */
@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="CourseSessionId")
    private CourseSession courseSession;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="StartDate")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="EndDate")
    private Date endDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseSession getCourseSession() {
        return courseSession;
    }

    public void setCourseSession(CourseSession courseSession) {
        this.courseSession = courseSession;
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
}
