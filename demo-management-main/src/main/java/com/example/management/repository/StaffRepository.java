package com.example.management.repository;

import com.example.management.domain.Staff;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    Staff findByLoginId(String loginId);

    Slice<Staff> findAllByName(String name01, PageRequest pageRequest);

}
