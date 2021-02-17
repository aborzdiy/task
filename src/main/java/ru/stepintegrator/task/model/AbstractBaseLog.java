package ru.stepintegrator.task.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractBaseLog {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;

    @Column(name = "created_date")
    protected Date createdDate = new Date();

    @Column(name = "request")
    protected String request;

    @Column(name = "response")
    protected String response;

    public AbstractBaseLog() {
    }

    public AbstractBaseLog(Integer id) {
        this.id = id;
    }

    public AbstractBaseLog(Integer id, String request, String response) {
        this.id = id;
        this.request = request;
        this.response = response;
    }

    public AbstractBaseLog(String request, String response) {
        this(null, request, response);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "AbstractBaseLog{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", request='" + request + '\'' +
                ", response='" + response + '\'' +
                '}';
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String input) {
        this.request = input;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String output) {
        this.response = output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        AbstractBaseLog that = (AbstractBaseLog) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}
