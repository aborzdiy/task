package ru.stepintegrator.task.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "internal_log")
public class InternalLog extends AbstractBaseLog{

    public InternalLog() {
        super();
    }

    @Override
    public String toString() {
        return "InternalLog{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", request='" + request + '\'' +
                ", response='" + response + '\'' +
                '}';
    }

    public InternalLog(String request, String response) {
        super(request, response);
    }

}
