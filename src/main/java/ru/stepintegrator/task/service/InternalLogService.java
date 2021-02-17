package ru.stepintegrator.task.service;

import org.springframework.stereotype.Service;
import ru.stepintegrator.task.model.InternalLog;
import ru.stepintegrator.task.repository.CrudInternalLogRepository;

@Service
public class InternalLogService {

    private final CrudInternalLogRepository logRepository;

    public InternalLogService(CrudInternalLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void addLogItem(String request, String response) {
        InternalLog logItem = new InternalLog(request, response);
        logRepository.save(logItem);
    }
}
