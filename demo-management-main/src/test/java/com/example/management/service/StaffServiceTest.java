package com.example.management.service;

import com.example.management.domain.Staff;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StaffServiceTest {

    @Autowired
    StaffService service;

    @Test
    void addStaff() {
        // given
        Staff test1 = new Staff();
        test1.setLoginId("test01");
        test1.setPassword("password");
        test1.setName("name");
        test1.setBirthDate(LocalDate.of(1980, 4, 1));
        test1.setEmail("test01@lifeun.edu.kh");

        // when
        service.addStaff(test1);
        Staff savedStaff = service.getStaffByLoginId(test1.getLoginId());

        // then
        Assertions.assertThat(test1.getLoginId()).isEqualTo(savedStaff.getLoginId());
    }
}