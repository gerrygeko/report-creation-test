package handlers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import resources.UserRepository;
import utils.CsvExporter;
import utils.ExcelExporter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Path("/report")
@AllArgsConstructor
public class ReportServlet {

    private UserRepository userRepository;

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/csv")
    public Response fetchCsvFile() {

        log.info("report CSV handler");

        long start = System.currentTimeMillis();
        ByteArrayInputStream byteArrayStream = CsvExporter.export(userRepository.getUserRepo());
        long finish = System.currentTimeMillis();
        log.info("Time to generate report: {} ms", finish - start);
        String fileName = new SimpleDateFormat("'csv-report-'yyyy-MM-dd_HH.mm.ss'.csv'").format(new Date());

        return Response.ok(byteArrayStream, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "inline; filename=\"" + fileName + "\"")
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/excel")
    public Response fetchExcelFile() {

        log.info("report XLSX handler");

        long start = System.currentTimeMillis();
        ByteArrayInputStream byteArrayStreamExcel;
        try {
            byteArrayStreamExcel = ExcelExporter.export(userRepository.getUserRepo());
        } catch (IOException e) {
            return Response.serverError().build();
        }
        long finish = System.currentTimeMillis();
        log.info("Time to generate report: {} ms", finish - start);
        String fileName = new SimpleDateFormat("'excel-report-'yyyy-MM-dd_HH.mm.ss'.xlsx'").format(new Date());

        return Response.ok(byteArrayStreamExcel, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "inline; filename=\"" + fileName + "\"")
                .build();
    }

}
