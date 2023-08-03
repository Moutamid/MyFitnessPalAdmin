package com.moutamid.myfitnesspaladmin.models;

public class CompModel {
    String id;
    double squart, deadLift, bench;
    String name;
    String videoUrl;
    int rank;

    public CompModel() {
    }

    public CompModel(String id, double squart, double deadLift, double bench, String name, String videoUrl) {
        this.id = id;
        this.squart = squart;
        this.deadLift = deadLift;
        this.bench = bench;
        this.name = name;
        this.videoUrl = videoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getDeadLift() {
        return deadLift;
    }

    public void setDeadLift(double deadLift) {
        this.deadLift = deadLift;
    }

    public double getSquart() {
        return squart;
    }

    public void setSquart(double squart) {
        this.squart = squart;
    }

    public double getBench() {
        return bench;
    }

    public void setBench(double bench) {
        this.bench = bench;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getTotalPoints() {
        return (int) (Math.floor(squart) + Math.floor(deadLift) + Math.floor(bench));
    }

}
