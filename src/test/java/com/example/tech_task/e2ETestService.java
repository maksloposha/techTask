package com.example.tech_task;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class e2ETestService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    public void testAddCustomer() {
        customerService.addCustomer("John", "Doe", 30);
        Iterable<Customer> customers = customerService.getCustomers();
        assertThat(customers).extracting(Customer::getFirstName).contains("John");
        assertThat(customers).extracting(Customer::getLastName).contains("Doe");
        assertThat(customers).extracting(Customer::getAge).contains(30);
    }

    @Test
    public void testFindCustomerById() {
        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setAge(30);
        customerRepository.save(customer1);

        Customer customer2 = customerService.findCustomerById(customer1.getId());

        assertThat(customer2.getFirstName()).isEqualTo("John");
        assertThat(customer2.getLastName()).isEqualTo("Doe");
        assertThat(customer2.getAge()).isEqualTo(30);
    }
}

