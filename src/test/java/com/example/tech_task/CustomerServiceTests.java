package com.example.tech_task;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerServiceTests {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testAddCustomer() {
        String first = "John";
        String last = "Doe";
        int age = 30;
        customerService.addCustomer(first, last, age);
        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository, times(1)).save(customerCaptor.capture());
        Customer capturedCustomer = customerCaptor.getValue();
        assertEquals(first, capturedCustomer.getFirstName());
        assertEquals(last, capturedCustomer.getLastName());
        assertEquals(age, capturedCustomer.getAge());
    }

    @Test
    public void testGetCustomers() {
        List<Customer> customers = Arrays.asList(
                new Customer("John", "Doe", 30),
                new Customer("Jane", "Doe", 25)
        );
        when(customerRepository.findAll()).thenReturn(customers);
        Iterable<Customer> result = customerService.getCustomers();
        assertEquals(customers, result);
    }

    @Test
    public void testFindCustomerById() {
        Integer id = 1;
        Customer customer = new Customer("John", "Doe", 30);
        when(customerRepository.findCustomerById(id)).thenReturn(customer);
        Customer result = customerService.findCustomerById(id);
        assertEquals(customer, result);
    }
}
