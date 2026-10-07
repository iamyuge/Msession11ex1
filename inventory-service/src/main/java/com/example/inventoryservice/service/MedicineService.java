package com.example.inventoryservice.service;

import com.example.inventoryservice.entity.Medicine;
import com.example.inventoryservice.repository.MedicineRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Cacheable(value = "medicines", key = "#id")
    public Medicine getMedicine(Long id) {
        System.out.println("Đang truy vấn PostgreSQL cho medicineId = " + id);

        return medicineRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy thuốc với id: " + id));
    }
}