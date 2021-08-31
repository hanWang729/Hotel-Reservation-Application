package service;

import model.Customer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class CustomerService {
    public Collection<Customer> customers = new HashSet<Customer>();

    private static CustomerService customerService;

    private CustomerService() {}

    public static CustomerService getInstance() {
        if (Objects.isNull(customerService)){
            customerService = new CustomerService();
        }
        return customerService;
    }

    public void addCustomer(String email, String firstname, String lastName){
        Customer customer = new Customer(firstname,lastName,email);
        for(Customer c : customers){
            if(customer.equals(c)){
                System.out.println("Error: The email is occupied, please use another one.");
                return;
            }
        }
        customers.add(customer);
    }
    public Customer getCustomer(String customerEmail){
        for (Customer customer:customers){
            if(customer.getEmail().equals(customerEmail)){
                return customer;
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers(){
        return customers;
    }
}
