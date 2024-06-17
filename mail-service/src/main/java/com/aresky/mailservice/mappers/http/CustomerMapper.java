package com.aresky.mailservice.mappers.http;

import com.aresky.mailservice.dto.request.MailRequest;
import com.aresky.mailservice.dto.response.CustomerResponse;
import com.aresky.mailservice.entity.Customer;

public class CustomerMapper {
    public static CustomerResponse toCustomerResponse(Customer customer){
        if(customer == null) return null;

        return CustomerResponse.builder()
               .id(customer.getId())
               .fullName(customer.getFullName())
               .email(customer.getEmail())
               .build();
    }

    public static Customer toCustomer(MailRequest request){
        return Customer.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .build();
    }

    public static Customer toCustomer(String email, String fullName){
        return Customer.builder()
                .email(email)
                .fullName(fullName)
                .build();
    }
}
