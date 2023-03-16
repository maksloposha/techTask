package com.example.tech_task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TechTaskTest {
    @Autowired
    private CustomerService customerService;
    @Test
    void contextLoads() {
        assertThat(customerService).isNotNull();
    }

}
