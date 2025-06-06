package com.tuling.tccaccountservice.controller;

import com.tuling.tccaccountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fox
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    
    @Autowired
    private AccountService accountService;
    
    @RequestMapping("/debit")
    public Boolean debit(String xid,String userId, int money) throws Exception {
        // 用户账户扣款
        return accountService.debit(userId, money);

    }
    
}
