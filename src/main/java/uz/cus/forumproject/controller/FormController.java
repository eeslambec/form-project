package uz.cus.forumproject.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.cus.forumproject.service.FormService;

@RestController
@RequestMapping("/salom")
@RequiredArgsConstructor
public class FormController {
    private final FormService formService;

}
