package com.formacion.app.Roadmap;

import java.util.Date;
import java.util.List;

import com.formacion.app.Milestone.Milestone;
import com.formacion.app.User.User;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Roadmap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date created_date;

    @OneToMany(mappedBy = "roadmap", cascade = CascadeType.ALL )
    private List<Milestone> milestones;

    @ManyToOne
    private User user;


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public Roadmap(){}


    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Date getCreated_date() {
        return created_date;
    }


    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }



    public List<Milestone> getMilestones() {
        return milestones;
    }


    public void setMilestones(List<Milestone> milestones) {
        this.milestones = milestones;
    }

    

    

}
