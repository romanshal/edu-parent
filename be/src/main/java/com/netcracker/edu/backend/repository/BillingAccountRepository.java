package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.BillingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BillingAccountRepository extends JpaRepository <BillingAccount, Long> {

    BillingAccount findById(long id);
}
