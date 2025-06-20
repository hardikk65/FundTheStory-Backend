package com.app.FundTheStory.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    // Fields related to the Story
    @NotBlank(message = "Title cannot be empty. ")
    private String title;

    @NotBlank(message = "Description cannot be empty. ")
    private String description;

    @NotNull(message = "Goal amount cannot be null. ")
    private double goalAmount;

    @NotNull(message = "Start date cannot be null. ")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null. ")
    private LocalDate endDate;

    private double amountRaised = 0.0;

    @NotNull(message = "Category cannot be null. ")
    private String category;

    



    // No args Constructor for JPA
    public Campaign(){}


    public Campaign(String title, String description, String category, double goalAmount, LocalDate startDate, LocalDate endDate){
        this.title = title;
        this.description = description;
        this.category = category;
        this.goalAmount = goalAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Campaign(String title, String description, String category, double goalAmount, LocalDate startDate, LocalDate endDate, double amountRaised){
        this.title = title;
        this.description = description;
        this.category = category;
        this.goalAmount = goalAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amountRaised = amountRaised;
    }

    public Long getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getCategory(){
        return category;
    }

    public double getGoalAmount(){
        return goalAmount;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public double getAmountRaised(){
        return amountRaised;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setGoalAmount(double goalAmount){
        this.goalAmount = goalAmount;
    }

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    public void setAmountRaised(double amountRaised){
        this.amountRaised = amountRaised;
    }

}
