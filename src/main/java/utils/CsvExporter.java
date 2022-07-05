package utils;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import resources.User;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvExporter {

    public static ByteArrayInputStream export(List<User> usersList) {
        StringWriter output = new StringWriter();
        StatefulBeanToCsv<User> beanToCsv = new StatefulBeanToCsvBuilder<User>(output).build();
        try {
            beanToCsv.write(usersList);
            String content = output.toString();
            return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
            return null;
        }
    }

}
