package uz.cus.forumproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SendDto {
    private Long chat_id;
    private String text;
}
