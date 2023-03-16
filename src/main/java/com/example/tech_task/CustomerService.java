package com.example.tech_task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void addCustomer(String first, String last, int age) {
        Customer customer = new Customer();
        customer.setFirstName(first);
        customer.setLastName(last);
        customer.setAge(age);
        customerRepository.save(customer);
    }

    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerById(Integer id) {
        return customerRepository.findCustomerById(id);
    }
}
