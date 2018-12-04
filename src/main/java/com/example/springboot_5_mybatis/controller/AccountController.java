package com.example.springboot_5_mybatis.controller;

import com.example.springboot_5_mybatis.bean.Account;
import com.example.springboot_5_mybatis.service.AccountService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Account> getAccountList(){
        return accountService.findAccontList();
    }

    @RequestMapping(value="/id",method = RequestMethod.GET)
    public Account getAccountByID(@PathVariable("id")int id){
        return accountService.findAccount(id);
    }

    @RequestMapping(value="/id",method = RequestMethod.PUT)
    public String updateAccount(@PathVariable("id")int id , @RequestParam(value = "name",required = true)String name
            ,@RequestParam(value="money",required = true)double money){
        int i = accountService.update(name,money,id);
        if(i == 1){
            return "success!";
        }else {
            return "failed!";
        }

    }

    @RequestMapping(value="/id" ,method = RequestMethod.DELETE)
    public String deleteAccount(@PathVariable("id")int id){
        int i = accountService.delete(id);

        if(i == 1){
            return "success!";
        }else {
            return "failed!";
        }
    }

    @RequestMapping(value="",method = RequestMethod.POST)
    public String addAccount(@RequestParam(value="name",required = true)String name,
                             @RequestParam(value="money",required = true)double money){
        int i = accountService.add(name, money);
        if(i == 1){
            return "success!";
        }else {
            return "failed!";
        }
    }

}
