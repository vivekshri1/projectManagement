package com.jrp.pma.dao;

import com.jrp.pma.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface SecurityRepo extends CrudRepository<UserAccount, Long> {



}
