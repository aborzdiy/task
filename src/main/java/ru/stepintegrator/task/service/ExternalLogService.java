package ru.stepintegrator.task.service;

import org.springframework.stereotype.Service;
import ru.stepintegrator.task.model.ExternalLog;
import ru.stepintegrator.task.repository.CrudExternalLogRepository;

@Service
public class ExternalLogService {

    private final CrudExternalLogRepository logRepository;

    public ExternalLogService(CrudExternalLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void addLogItem(String request, String response) {
        ExternalLog logItem = new ExternalLog(request, response);
        logRepository.save(logItem);
    }
}
