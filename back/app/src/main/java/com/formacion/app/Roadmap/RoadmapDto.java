package com.formacion.app.Roadmap;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.formacion.app.Milestone.Milestone;
import com.formacion.app.User.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class RoadmapDto {

    private Integer id;
    private String name;
    private String description;
    private Date created_date;
    private List<Milestone> milestones;
    private Integer userId;
    private Integer totalTask;
    private Integer totalDoneTask;
    private Float donePercentage;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getTotalTask() {
        return totalTask;
    }
    public void setTotalTask(Integer totalTask) {
        this.totalTask = totalTask;
    }
    public Integer getTotalDoneTask() {
        return totalDoneTask;
    }
    public void setTotalDoneTask(Integer totalDoneTask) {
        this.totalDoneTask = totalDoneTask;
    }
    public Float getDonePercentage() {
        return this.donePercentage;
    }
    public void setDonePercentage(Float donePercentage) {
        this.donePercentage = donePercentage;
    }


}

