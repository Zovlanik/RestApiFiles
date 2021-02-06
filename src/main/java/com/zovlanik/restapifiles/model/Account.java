package com.zovlanik.restapifiles.model;

public class Account {
    int id;
    String name;
    AccountStatus accountStatus;

    public Account(int id, String name, AccountStatus accountStatus) {
        this.id = id;
        this.name = name;
        this.accountStatus = accountStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
