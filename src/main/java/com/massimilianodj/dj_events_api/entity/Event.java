package com.massimilianodj.dj_events_api.entity;

import java.time.LocalDate;

public class Event {

    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private String venue;
    private String city;
    private String posterUrl;

    public Event() {
    }

    public Event(Long id, String title, String venue, String city, LocalDate date, String description, String posterUrl) {
        this.id = id;
        this.title = title;
        this.venue = venue;
        this.city = city;
        this.date = date;
        this.description = description;
        this.posterUrl = posterUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return posterUrl;
    }

    public void setImageUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
