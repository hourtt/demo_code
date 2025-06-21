package com.example.management.service;

import com.example.management.domain.Staff;
import com.example.management.repository.StaffRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    private final StaffRepository repository;

    public StaffService(StaffRepository repository) {
        this.repository = repository;
    }

    public Staff getStaff(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Staff getStaffByLoginId(String loginId) {
        return repository.findByLoginId(loginId);
    }

    public Staff addStaff(Staff staff) {
        return repository.save(staff);
    }

    public Staff editStaff(Staff staff) {
        if (repository.existsById(staff.getId())) {
            return repository.save(staff);
        } else {
            throw new IllegalArgumentException("staff edit error");
        }
    }

    public List<Staff> list() {
        return repository.findAll();
    }

    public Page<Staff> listType1(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    public Slice<Staff> listType2(PageRequest pageRequest) {
        Slice<Staff> list = repository.findAllByName("name01", pageRequest);
//         Slice<Staff> list = repository.findAll(pageRequest);
        return list;
    }
}
