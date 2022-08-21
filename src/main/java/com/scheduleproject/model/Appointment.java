package com.scheduleproject.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Appointment {
    private int appointmentId;
    private String title;
    private String description;


    private String location;

    private String type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime updatedDate;
    private String updatedBy;
    private int customerId;
    private int userId;
    private int contactId;

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate startDateWithoutTime;
    private LocalDate endDateWithoutTime;


    public Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime createdDate, String createdBy, LocalDateTime updatedDate, String updatedBy, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.startTime = this.startDate.toLocalTime();
        this.endTime = this.endDate.toLocalTime();
        this.endDateWithoutTime = this.endDate.toLocalDate();
        this.startDateWithoutTime = this.startDate.toLocalDate();

    }

    public Appointment(String title, String description, String location, String type, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime createdDate, String createdBy, LocalDateTime updatedDate, String updatedBy, int customerId, int userId, int contactId) {

        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.startTime = this.startDate.toLocalTime();
        this.endTime = this.endDate.toLocalTime();
        this.endDateWithoutTime = this.endDate.toLocalDate();
        this.startDateWithoutTime = this.startDate.toLocalDate();

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getStartDateWithoutTime() {
        return startDateWithoutTime;
    }

    public void setStartDateWithoutTime(LocalDate startDateWithoutTime) {
        this.startDateWithoutTime = startDateWithoutTime;
    }

    public LocalDate getEndDateWithoutTime() {
        return endDateWithoutTime;
    }

    public void setEndDateWithoutTime(LocalDate endDateWithoutTime) {
        this.endDateWithoutTime = endDateWithoutTime;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                ", updatedDate=" + updatedDate +
                ", updatedBy='" + updatedBy + '\'' +
                ", customerId=" + customerId +
                ", userId=" + userId +
                ", contactId=" + contactId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", startDateWithoutTime=" + startDateWithoutTime +
                ", endDateWithoutTime=" + endDateWithoutTime +
                '}';
    }
}
