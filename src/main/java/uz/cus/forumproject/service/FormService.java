package uz.cus.forumproject.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.cus.forumproject.dto.FormDto;
import uz.cus.forumproject.model.Form;

import java.util.List;

@Service
public interface FormService {
    String save(FormDto formDto);

    Form validateToken(String token);

    List<Form> getAll();
}
