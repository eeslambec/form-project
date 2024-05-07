package uz.cus.forumproject.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import uz.cus.forumproject.dto.FormDto;
import uz.cus.forumproject.model.Form;
import uz.cus.forumproject.repo.FormRepository;
import uz.cus.forumproject.service.FormService;

import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {
    private final FormRepository formRepository;
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
        return generateQRCode(form.getId());
    }
    @SneakyThrows
    public String generateQRCode(Long id) {
        String data = "https://github.com/eeslambec";
        String location = "/home/eeslambec/Pictures/"+ id + UUID.randomUUID() +".jpg";
        BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
        MatrixToImageWriter.writeToPath(bitMatrix, "jpg", Paths.get(location));
        return "";
    }
}
