package com.jrp.pma.service;

import com.jrp.pma.dao.SecurityRepo;
import com.jrp.pma.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    @Autowired
    SecurityRepo securityRepo;

    public UserAccount saveUser(UserAccount userAccount){
        return securityRepo.save(userAccount);
    }
}
