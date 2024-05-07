package uz.cus.forumproject.service;

import org.springframework.stereotype.Service;
import uz.cus.forumproject.dto.FormDto;
import uz.cus.forumproject.model.Form;

@Service
public interface FormService {
    String save(FormDto formDto);

    Form validateToken(String token);
}
