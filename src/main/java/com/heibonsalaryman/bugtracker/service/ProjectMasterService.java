package com.heibonsalaryman.bugtracker.service;

import com.heibonsalaryman.bugtracker.model.ProjectMaster;
import com.heibonsalaryman.bugtracker.repository.ProjectMasterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectMasterService {

    private final ProjectMasterRepository repository;

    public ProjectMasterService(ProjectMasterRepository repository) {
        this.repository = repository;
    }

    public List<ProjectMaster> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void register(ProjectMaster projectMaster) {
        repository.insert(projectMaster);
    }
}
