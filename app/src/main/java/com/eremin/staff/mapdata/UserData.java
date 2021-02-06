package com.eremin.staff.mapdata;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserData extends RealmObject {
    @PrimaryKey
    private String id; // Уникальный идентификатор в БД
    private String firstName; // Имя
    private String fatherName; // Отчество
    private String lastName; // Фамилия
    private String position; // Должность

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFatherName() {
        return fatherName;
    }
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
}
