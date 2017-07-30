package com.springhibernate.practise.service;

import com.springhibernate.practise.dao.AccountDAO;
import com.springhibernate.practise.model.Account;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountDAO accountDAO;

    public List<Account> getAll() {
        Iterable<Account> iterable = accountDAO.findAll();
        List<Account> accounts = new ArrayList<>();
        for (Account account : iterable) {
            accounts.add(account);
        }
        return accounts;
    }

    public Account get(Long id) {
        return accountDAO.findOne(id);
    }

    public Account getByEmailPassword(String email, String password) {
        List<Account> iterable = getAll();
        Account account = null;
        for (Account itr : iterable) {
            if (itr.getEmail().equals(email) && itr.getPassword().equals(password)) {
                account = itr;
                break;
            }
        }
        return account;
    }

    public boolean checkEmail(String email) {
        List<Account> iterable = getAll();
        boolean exist = false;
        for (Account itr : iterable) {
            if (itr.getEmail().equals(email)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    public Account add(Account account) {
        return accountDAO.save(account);
    }

    public Account update(Account account) {
        return accountDAO.save(account);
    }

    public void delete(Long id) {
        accountDAO.delete(id);
    }

}
