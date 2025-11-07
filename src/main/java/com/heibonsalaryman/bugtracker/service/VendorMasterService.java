package com.heibonsalaryman.bugtracker.service;

import com.heibonsalaryman.bugtracker.model.VendorMaster;
import com.heibonsalaryman.bugtracker.repository.VendorMasterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VendorMasterService {

    private final VendorMasterRepository repository;

    public VendorMasterService(VendorMasterRepository repository) {
        this.repository = repository;
    }

    public List<VendorMaster> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void register(VendorMaster vendorMaster) {
        repository.insert(vendorMaster);
    }
}
