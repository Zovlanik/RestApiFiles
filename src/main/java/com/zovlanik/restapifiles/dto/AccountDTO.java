package com.zovlanik.restapifiles.dto;

import com.zovlanik.restapifiles.model.Account;

public class AccountDTO {
    private Integer id;
    private String name;

    public AccountDTO() {
    }

    public AccountDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static AccountDTO fromAccount(Account account){
        return new AccountDTO(account.getId(), account.getName());
    }
}
