package uz.cus.forumproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FormDto {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String companyName;
    private String business;
    private String suggestion;
}
