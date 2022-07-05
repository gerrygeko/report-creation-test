package utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import resources.User;

import java.io.*;
import java.lang.reflect.Field;
import java.util.List;

@Slf4j
public class ExcelExporter {

    public static ByteArrayInputStream export(List<User> usersList) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Report");
            createHeadersRow(sheet);
            for (int i = 0; i < usersList.size(); i++) {
                createUserRow(sheet, i + 1, usersList.get(i));
            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos);
            byte[] content = bos.toByteArray();
            return new ByteArrayInputStream(content);
        }
    }

    private static void createHeadersRow(Sheet sheet) {
        Field[] declaredFields = User.class.getDeclaredFields();
        Row row = sheet.createRow(0);
        List<Field> listOfFields = List.of(declaredFields);
        for (int i = 0; i < listOfFields.size(); i++) {
            row.createCell(i).setCellValue(listOfFields.get(i).getName());
        }
    }

    private static void createUserRow(Sheet sheet, int index, User user) {
        Row row = sheet.createRow(index);
        row.createCell(0).setCellValue(user.getUserName());
        row.createCell(1).setCellValue(user.getLoginName());
        row.createCell(2).setCellValue(user.isLicensed());
        row.createCell(3).setCellValue(user.isOnline());
    }

}
