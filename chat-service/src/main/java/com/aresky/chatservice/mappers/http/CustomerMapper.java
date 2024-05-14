package com.aresky.chatservice.mappers.http;

import com.aresky.chatservice.dto.request.CustomerRequest;
import com.aresky.chatservice.dto.response.CustomerResponse;
import com.aresky.chatservice.entity.Customer;
import com.aresky.chatservice.entity.EActivationStatus;

public class CustomerMapper {
    public static Customer mapCustomerRequestToCustomer(CustomerRequest request){
        return Customer.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .status(EActivationStatus.OFFLINE)
                .build();
    }

    public CustomerResponse mapCustomerToCustomerResponse(Customer customer){
        return CustomerResponse.builder()
               .id(customer.getId())
               .fullName(customer.getFullName())
               .email(customer.getEmail())
               .status(customer.getStatus().name())
               .build();
    }
}
