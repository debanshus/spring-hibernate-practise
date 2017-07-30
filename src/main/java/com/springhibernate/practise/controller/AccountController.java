package com.springhibernate.practise.controller;

import com.springhibernate.practise.form.LoginForm;
import com.springhibernate.practise.form.RegisterForm;
import com.springhibernate.practise.model.Account;
import com.springhibernate.practise.service.AccountService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            Model model) {
        LOGGER.error("Navigating to register");

        model.addAttribute("message", "");
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegister(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            Model model,
            @ModelAttribute RegisterForm registerForm) {

        Account account = new Account();
        account.setName(registerForm.getName());
        account.setEmail(registerForm.getEmail());
        account.setPassword(registerForm.getPassword());

        if (accountService.checkEmail(registerForm.getEmail())) {
            model.addAttribute("message", "Email address already exists");
        } else {
            Account newAccount = accountService.add(account);
            model.addAttribute("message", newAccount == null ? "Error in registration" : "Registration successful");
        }

        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            Model model) {
        LOGGER.error("Navigating to logins");

        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String dologin(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            Model model,
            @ModelAttribute LoginForm loginForm) {

        if (accountService.checkEmail(loginForm.getEmail())) {
            Account account = accountService.getByEmailPassword(loginForm.getEmail(), loginForm.getPassword());
            if (account != null) {
                session.setAttribute("account", account);
                model.addAttribute("account", account);
                return "profile";
            } else {
                model.addAttribute("loginForm", new LoginForm());
                model.addAttribute("message", "Email address and/or password do not match");
                return "login";
            }

        } else {
            model.addAttribute("message", "Email address does not exist");
            model.addAttribute("loginForm", new LoginForm());
            return "login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String dologout(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            Model model,
            @ModelAttribute LoginForm loginForm) {

        session.removeAttribute("account");
        model.addAttribute("message", "You logged out succesfully");

        return "index";
    }
}
