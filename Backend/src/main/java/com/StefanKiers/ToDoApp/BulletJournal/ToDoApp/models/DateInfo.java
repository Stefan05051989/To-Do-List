package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

/**
 * DateInfo
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models
 *
 * @author Stefan Kiers
 * @since 5-8-2026
 */
/* TODO: embedded date class voor to-do list app.
 */
@Embeddable // = heeft geen eigen tabel, maar wordt wel als kolommen "embedded" in elke entity die het gebruikt (zonder duplicates)
public class DateInfo {
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate targetDate;
    private LocalDate finishedAt;

    public DateInfo() {
        this.createdAt = LocalDate.now();
    }

    public DateInfo(LocalDate createdAt, LocalDate updatedAt, LocalDate targetDate, LocalDate finishedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.targetDate = targetDate;
        this.finishedAt = finishedAt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public LocalDate getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDate finishedAt) {
        this.finishedAt = finishedAt;
    }
}
