package uz.cus.forumproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import uz.cus.forumproject.model.Form;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExportXLSXFile {
    private final FormServiceImpl formService;
    public XSSFWorkbook workbook = new XSSFWorkbook();
    public XSSFSheet sheet = workbook.createSheet("Visitors");
    private final Row headerRow = sheet.createRow(0);
    public String[] columns = {"ID", "Full name", "Email", "Phone number", "Company name", "Business"};

    public String exportXLSXFile() throws IOException {
        List<Form> forms = formService.getAll();
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }
        for (int i = 0; i < forms.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(forms.get(i).getId());
            row.createCell(1).setCellValue(forms.get(i).getFullName());
            row.createCell(2).setCellValue(forms.get(i).getEmail());
            row.createCell(3).setCellValue(forms.get(i).getPhoneNumber());
            row.createCell(4).setCellValue(forms.get(i).getCompanyName());
            row.createCell(5).setCellValue(forms.get(i).getBusiness());
        }
        Path UPLOAD_DIR = Path.of(System.getProperty("user.home") + "/" + "EXCEL"+ File.separator + "files" + File.separator);
        FileOutputStream out;
        out = new FileOutputStream(UPLOAD_DIR + "form.xlsx");
        workbook.write(out);
        out.close();
        return UPLOAD_DIR + "form.xlsx";
    }
}
