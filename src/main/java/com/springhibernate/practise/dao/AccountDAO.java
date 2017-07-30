package com.springhibernate.practise.dao;

import com.springhibernate.practise.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountDAO extends CrudRepository<Account, Long> {

}
