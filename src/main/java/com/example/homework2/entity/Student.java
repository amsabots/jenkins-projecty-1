package com.example.homework2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "students")
public class Student implements Serializable {

    static final long serialVersionUID = 20L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("name")
    @Column(nullable = false)
    private String name;

    @JsonProperty("birthYear")
    @Column(nullable = false)
    private int birthYear;

    @JsonProperty("email")
    @Column(nullable = false)
    private String email;

    @JsonProperty("parentName")
    @Column(nullable = false)
    private String parentsName;

    @JsonProperty("status")
    @Column(nullable = false)
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student() {
    }

    public String getParentsName() {
        return parentsName;
    }

    public void setParentsName(String parentsName) {
        this.parentsName = parentsName;
    }

    public Student(long id, String name, int birthYear, String email) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.email = email;
    }

    public Student(long id, String name, int birthYear, String email, Group group) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.email = email;
        this.group = group;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupId")
    private Group group;


    @JsonBackReference
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", email='" + email + '\'' +
                '}';
    }
}
