package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.BillingAccount;

import java.util.List;
import java.util.Optional;

public interface BillingAccountService {

    BillingAccount save(BillingAccount billingAccount);
    BillingAccount findById(long id);
    List <BillingAccount> findAll();
    void delete(Long id);
}
