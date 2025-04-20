package com.example.hw04_gymlog.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.hw04_gymlog.Database.GymLogDatabase;

import java.time.LocalDate;
import java.util.Objects;

// Denotes that this object will be stored in a database
@Entity(tableName = GymLogDatabase.GYM_LOG_TABLE)
public class GymLog {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String exercise;
    private double weight;
    private int reps;
    private LocalDate date;

    public GymLog(String exercise, double weight, int reps) {
        this.exercise = exercise;
        this.weight = weight;
        this.reps = reps;
        this.date = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GymLog gymlog = (GymLog) o;
        return id == gymlog.id && Double.compare(weight, gymlog.weight) == 0 && reps == gymlog.reps && Objects.equals(exercise, gymlog.exercise) && Objects.equals(date, gymlog.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, exercise, weight, reps, date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
