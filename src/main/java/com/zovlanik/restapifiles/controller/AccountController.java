package com.zovlanik.restapifiles.controller;

import com.zovlanik.restapifiles.model.Account;
import com.zovlanik.restapifiles.model.AccountStatus;
import com.zovlanik.restapifiles.repository.AccountRepository;
import com.zovlanik.restapifiles.repository.hibernate.HibernateAccountRepositoryImpl;

import java.util.List;


public class AccountController {
    private final AccountRepository accountRepository = new HibernateAccountRepositoryImpl();

    public boolean create(String accountName) {

        if (accountName.length() > 2) {
            accountRepository.create(new Account(1, accountName, AccountStatus.ACTIVE));
            return true;
        }
        return false;
    }

    public Account getById(int id) {
        return accountRepository.getById(id);
    }


    public boolean deleteById(int accountId) {
        int tempSizeBefore = accountRepository.getAll().size();
        accountRepository.deleteById(accountId);
        int tempSizeAfter = accountRepository.getAll().size();
        return tempSizeAfter < tempSizeBefore;
    }

    public void update(Account newAccount) {
        accountRepository.update(newAccount);
    }

    public List<Account> getAll(){
        return accountRepository.getAll();
    }
}

