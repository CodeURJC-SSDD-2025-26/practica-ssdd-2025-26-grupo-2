package com.ssdd.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ErrorWebController {

    @GetMapping("/error404")
    public String show404Page() {
        return "error/404";
    }

    @GetMapping("/error403")
    public String show403Page() {
        return "error/403";
    }
    
    @GetMapping("/loginFailure")
    public String showLoginFailurePage() {
        return "loginFailure";
    }
}