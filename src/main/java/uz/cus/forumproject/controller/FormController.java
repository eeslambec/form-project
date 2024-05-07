package uz.cus.forumproject.controller;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.cus.forumproject.dto.FormDto;
import uz.cus.forumproject.model.Form;
import uz.cus.forumproject.service.FormService;

import javax.swing.filechooser.FileSystemView;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class FormController {
    private final FormService formService;
    @PostMapping("/save")
    public String form(@RequestBody FormDto formDto) {
        return formService.save(formDto);
    }
    @SneakyThrows
    @GetMapping("/qrcode/{filename}")
    public ResponseEntity<?> qrcode(@PathVariable String filename) {
        Path path = Paths.get(FileSystemView.getFileSystemView().getHomeDirectory().getPath() + "/data" + filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        InputStreamResource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok().headers(headers).body(resource);
    }
        @GetMapping("/token/{token}")
    public ResponseEntity<?> validateToken(@PathVariable String token) {
        System.out.println(token);
        return ResponseEntity.ok(formService.validateToken(token));
    }

}