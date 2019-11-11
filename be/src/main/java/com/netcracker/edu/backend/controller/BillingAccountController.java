package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.BillingAccount;
import com.netcracker.edu.backend.service.BillingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/billing-account")
public class BillingAccountController {

    @Autowired
    private BillingAccountService billingAccountService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity <BillingAccount> getBillingAccount(@PathVariable(name = "id") Long id) {
        BillingAccount billingAccount = billingAccountService.findById(id);
        if(billingAccount== null){
            return new ResponseEntity<BillingAccount>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<BillingAccount>(billingAccount,HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<BillingAccount> findAll() {
        return billingAccountService.findAll();
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public BillingAccount save(@RequestBody BillingAccount billingAccount) {
//        if(billingAccount==null){
//            return new ResponseEntity<BillingAccount>(HttpStatus.BAD_REQUEST);
//        }
//
//        this.billingAccountService.save(billingAccount);
//        return new ResponseEntity<BillingAccount>(billingAccount, HttpStatus.CREATED);
//
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBillingAccount(@PathVariable(name = "id") Long id) {
        billingAccountService.delete(id);
    }
}
