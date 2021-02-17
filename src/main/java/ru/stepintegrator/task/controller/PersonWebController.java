package ru.stepintegrator.task.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.stepintegrator.task.service.PersonService;
import ru.stepintegrator.task.to.PersonTo;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PersonWebController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    public static final String PERSON_LINK = "person";
    public static final String PERSON_URL = "/person";

    private final PersonService personService;

    @Autowired
    public PersonWebController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(PERSON_URL)
    public String personInfo(HttpServletRequest request,
                             @RequestParam(name = "email", required = false, defaultValue = "") String email,
                             @RequestParam(name = "phone", required = false, defaultValue = "") String phone, Model model) {

        final String requestUrl = request.getRequestURL() + "?" + request.getQueryString();
        log.info("request={}", requestUrl);

        PersonTo result = null;
         if (StringUtils.hasText(email)) {
            log.info("get with email={}", email);
            result = personService.getByParam("email", email);
        } else if (StringUtils.hasText(phone)) {
            log.info("get with phone={}", phone);
            result = personService.getByParam("phone", phone);
        }
        
        model.addAttribute("person", result);
        return PERSON_LINK;
    }
}
