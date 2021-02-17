package ru.stepintegrator.task.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "external_log")
public class ExternalLog extends AbstractBaseLog{

    public ExternalLog() {
        super();
    }

    public ExternalLog(String input, String output) {
        super(input, output);
    }

    @Override
    public String toString() {
        return "ExternalLog{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", request='" + request + '\'' +
                ", response='" + response + '\'' +
                '}';
    }

}
