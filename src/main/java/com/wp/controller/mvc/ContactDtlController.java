package com.wp.controller.mvc;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller("ContactDtlMvcController")
public class ContactDtlController {
    @GetMapping("/contact-page")
    public String displayContactForm() {
        return "contact-detail";
    }
}
