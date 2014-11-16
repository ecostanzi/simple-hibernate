package com.lesula.db.table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by enrico on 16/11/14.
 */
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private Long id;

    @Column(name="Name")
    private String name;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @Cascade(value = {CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private Set<CourseSession> sessions = new HashSet<CourseSession>();

    public Course(){}

    public Course(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CourseSession> getSessions() {
        return sessions;
    }

    public void setSessions(Set<CourseSession> sessions) {
        this.sessions = sessions;
    }
}
