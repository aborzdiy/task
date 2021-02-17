package ru.stepintegrator.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepintegrator.task.model.ExternalLog;

public interface CrudExternalLogRepository extends JpaRepository<ExternalLog, Integer> {

}
