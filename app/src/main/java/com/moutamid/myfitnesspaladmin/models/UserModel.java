package com.moutamid.myfitnesspaladmin.models;

public class UserModel {
    String id, name, email, password, gender, height, weight, age;
    boolean isRankedAvailable;
    public UserModel() {
    }

    public UserModel(String id, String name, String email, String password, String gender, String height, String weight, String age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

    public UserModel(String id, String name, String email, String password, String gender, String height, String weight, String age, boolean isRankedAvailable) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.isRankedAvailable = isRankedAvailable;
    }

    public boolean isRankedAvailable() {
        return isRankedAvailable;
    }

    public void setRankedAvailable(boolean rankedAvailable) {
        isRankedAvailable = rankedAvailable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
