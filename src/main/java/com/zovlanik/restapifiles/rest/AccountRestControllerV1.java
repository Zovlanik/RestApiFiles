package com.zovlanik.restapifiles.rest;

import com.google.gson.Gson;
import com.zovlanik.restapifiles.dto.AccountDTO;
import com.zovlanik.restapifiles.model.Account;
import com.zovlanik.restapifiles.repository.AccountRepository;
import com.zovlanik.restapifiles.repository.hibernate.HibernateAccountRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class AccountRestControllerV1 extends HttpServlet {
    private final AccountRepository accountRepository = new HibernateAccountRepositoryImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer urlString = request.getRequestURL();

        Gson gsonAccount = new Gson();
        int lastIndexOfSlash = urlString.lastIndexOf("/");
        String whatToReturn = urlString.substring(lastIndexOfSlash + 1);

        if (whatToReturn.equalsIgnoreCase("accounts")){
            response.getWriter().println(gsonAccount.toJson(
                    accountRepository.getAll().stream()
                    .map(account -> {return new AccountDTO(account.getId(),account.getName());})
                    .collect(Collectors.toList())));

        }else{
            try{
                int accountId = Integer.valueOf(whatToReturn);
                Account account = accountRepository.getById(accountId);
                AccountDTO accountDTO = AccountDTO.fromAccount(account);
                response.getWriter().println(gsonAccount.toJson(accountDTO));
            }catch(Exception ex){
                ex.printStackTrace();
                response.getWriter().println("Wrong type of parameter ID");
            }
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("application/json");

            Account newAccount = new Gson().fromJson(request.getReader(), Account.class);

            try {
                accountRepository.create(newAccount);
                response.getWriter().println("Account with name = " + newAccount.getName() + " was successfully created.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }


    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("application/json");

        StringBuffer urlString = request.getRequestURL();

        int lastIndexOfSlash = urlString.lastIndexOf("/");
        String accountid = urlString.substring(lastIndexOfSlash + 1);
        if(!accountid.equalsIgnoreCase("accounts")) {
            try {
                Account accountToChange = new Gson().fromJson(request.getReader(), Account.class);
                accountToChange.setId(Integer.parseInt(accountid));
                accountRepository.update(accountToChange);
                response.getWriter().println("Account was changed to :");
                response.getWriter().println("id = " + accountToChange.getId());
                response.getWriter().println("Name = " + accountToChange.getName());
                response.getWriter().println("AccountStatus = " + accountToChange.getAccountStatus());
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }else{
            response.getWriter().println("Account with id '" + accountid + "' does not exist");
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        StringBuffer urlString = request.getRequestURL();

        int lastIndexOfSlash = urlString.lastIndexOf("/");
        String accountIdToDelete = urlString.substring(lastIndexOfSlash + 1);

            try{
                int accountId = Integer.valueOf(accountIdToDelete);
                accountRepository.deleteById(accountId);
                response.getWriter().println("Account with id = " + accountId + " was successfully deleted.");
            }catch(Exception ex){
                ex.printStackTrace();
                response.getWriter().println("Wrong type of parameter ID or account with that id does not exist");
            }
    }

}
