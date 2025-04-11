package com.tarun.stusurvey.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "studsurvtable")
public class StudentSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long id;

    @JsonProperty("first_name")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @JsonProperty("last_name")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @JsonProperty("street_address")
    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    @JsonProperty("city")
    @Column(name = "city", nullable = false)
    private String city;

    @JsonProperty("state")
    @Column(name = "state", nullable = false)
    private String state;

    @JsonProperty("zip")
    @Column(name = "zip", nullable = false)
    private String zip;

    @JsonProperty("telephone")
    @Column(name = "telephone", nullable = false)
    private String telephone;

    @JsonProperty("email")
    @Column(name = "email", nullable = false)
    private String email;

    @JsonProperty("survey_date")
    @Column(name = "survey_date", nullable = false)
    private LocalDate surveyDate;

    @JsonProperty("liked")
    @Column(name = "liked")
    private String liked;

    @JsonProperty("interested")
    @Column(name = "interested")
    private String interested;

    @JsonProperty("recommendation")
    @Column(name = "recommendation")
    private String recommendation;

    // Constructors
    public StudentSurvey() {}

    public StudentSurvey(Long id, String firstName, String lastName, String streetAddress, String city,
                         String state, String zip, String telephone, String email, LocalDate surveyDate,
                         String liked, String interested, String recommendation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.telephone = telephone;
        this.email = email;
        this.surveyDate = surveyDate;
        this.liked = liked;
        this.interested = interested;
        this.recommendation = recommendation;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getStreetAddress() { return streetAddress; }
    public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getSurveyDate() { return surveyDate; }
    public void setSurveyDate(LocalDate surveyDate) { this.surveyDate = surveyDate; }

    public String getLiked() { return liked; }
    public void setLiked(String liked) { this.liked = liked; }

    public String getInterested() { return interested; }
    public void setInterested(String interested) { this.interested = interested; }

    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
}
