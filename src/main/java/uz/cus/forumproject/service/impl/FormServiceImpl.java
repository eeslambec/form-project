package uz.cus.forumproject.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.cus.forumproject.dto.FormDto;
import uz.cus.forumproject.dto.SendDto;
import uz.cus.forumproject.model.Form;
import uz.cus.forumproject.repo.FormRepository;
import uz.cus.forumproject.service.FormService;

import java.net.URI;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {
    private final FormRepository formRepository;
    private final TokenServiceImpl tokenServiceImpl;
    private final String BASE_URL = "http://185.217.131.187:8080";

    @Override
    public String save(FormDto formDto) {
         Form form = formRepository.save(Form.builder()
                .email(formDto.getEmail())
                .business(formDto.getBusiness())
                .fullName(formDto.getFullName())
                .suggestion(formDto.getSuggestion())
                .companyName(formDto.getCompanyName())
                .phoneNumber(formDto.getPhoneNumber())
                .build());
         addToFile(form);
        return generateQRCode(form);
    }

    @Async
    @SneakyThrows
    public void addToFile(Form form) {
        RestTemplate template = new RestTemplate();
        String text = "Name : " + form.getFullName() + "\n" +
                "Email : " + form.getEmail() + "\n" +
                "Business : " + form.getBusiness() + "\n" +
                "CompanyName : " + form.getCompanyName() + "\n" +
                "PhoneNumber : " + form.getPhoneNumber() + "\n";
        String url = "https://api.telegram.org/bot7159954472:AAGHuLDQlnzsAZZ_HsjLzVYomJrg7uRHjr0/sendMessage";
        System.out.println(url);
        template.postForObject(URI.create(url),new SendDto(1084271471L,text),Object.class);
    }

    @Override
    public Form validateToken(String token) {
        Claims claims = tokenServiceImpl.parseAllClaims(token);
        Long id = Long.parseLong(claims.getSubject());
        return formRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Token is invalid"));
    }

    @Override
    public List<Form> getAll() {
        return formRepository.findAll();
    }

    @SneakyThrows
    public String generateQRCode(final Form form) {
        String data = "https://auto-parts-six.vercel.app/user?token=" + tokenServiceImpl.generateToken(form);
        UUID uuid = UUID.randomUUID();
        String location = Paths.get("src", "main", "resources", "static", "data").toFile().getAbsolutePath() + "/" + form.getId() + uuid + ".jpg";
        BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
        MatrixToImageWriter.writeToPath(bitMatrix, "jpg", Paths.get(location));
        return BASE_URL + "/form/qrcode/" + form.getId() + uuid + ".jpg";
    }
}
