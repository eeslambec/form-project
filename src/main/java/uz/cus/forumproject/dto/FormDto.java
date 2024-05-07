package uz.cus.forumproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.cus.forumproject.model.Form;

@Getter
@AllArgsConstructor
public class FormDto {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String companyName;
    private String business;
    private String suggestion;

    public FormDto(Form form) {
        this.fullName = form.getFullName();
        this.email = form.getEmail();
        this.phoneNumber = form.getPhoneNumber();
        this.companyName = form.getCompanyName();
        this.business = form.getBusiness();
        this.suggestion = form.getSuggestion();
    }
}
