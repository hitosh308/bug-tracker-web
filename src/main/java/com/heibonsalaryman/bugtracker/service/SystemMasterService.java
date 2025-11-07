package com.heibonsalaryman.bugtracker.service;

import com.heibonsalaryman.bugtracker.model.SystemMaster;
import com.heibonsalaryman.bugtracker.repository.SystemMasterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemMasterService {

    private final SystemMasterRepository repository;

    public SystemMasterService(SystemMasterRepository repository) {
        this.repository = repository;
    }

    public List<SystemMaster> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void register(SystemMaster systemMaster) {
        repository.insert(systemMaster);
    }
}
