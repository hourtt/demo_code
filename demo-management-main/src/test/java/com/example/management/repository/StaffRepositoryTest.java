package com.example.management.repository;


import com.example.management.domain.Staff;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class StaffRepositoryTest {

    @Autowired
    StaffRepository repository;

    @Test
    void insert() {
        // given
        Staff test1 = new Staff();
        test1.setLoginId("test01");
        test1.setPassword("password");
        test1.setName("name");
        test1.setBirthDate(LocalDate.of(1980, 4, 1));
        test1.setEmail("test01@lifeun.edu.kh");

        // when
        Staff savedStaff = repository.save(test1);

        // then
        Assertions.assertThat(test1.getLoginId()).isEqualTo(savedStaff.getLoginId());
    }

}