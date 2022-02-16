package es.dylanhurtado.menulateraldinamico.menulateraldinamico.model;

import java.util.UUID;

public class Person {
    private UUID id;
    private String avatar;
    private String name;
    private String email;
    private String gender;

    public Person(UUID id, String avatar, String name, String email, String gender) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public Person() {
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return name;
    }
}
