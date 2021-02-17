package ru.stepintegrator.task.to;

public class PersonTo {

    protected Integer id;
    protected String name;
    protected String username;

    public PersonTo() {
    }

    public PersonTo(Integer id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PersonTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
