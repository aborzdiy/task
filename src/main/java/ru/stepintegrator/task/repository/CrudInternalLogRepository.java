package ru.stepintegrator.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepintegrator.task.model.InternalLog;

public interface CrudInternalLogRepository extends JpaRepository<InternalLog, Integer> {

}
