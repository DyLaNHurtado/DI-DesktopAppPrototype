package es.dylanhurtado.menulateraldinamico.menulateraldinamico.model;

public class Person {
    private Long id;
    private String name;
    private String email;
    private String gender;

    public Person(Long id, String name, String email, String gender) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
