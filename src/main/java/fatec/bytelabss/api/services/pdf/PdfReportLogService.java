package fatec.bytelabss.api.services.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fatec.bytelabss.api.controllers.ExportPdfController;
import fatec.bytelabss.api.models.PdfReportLogs;
import fatec.bytelabss.api.repositories.PdfReportLogRepository;
import jakarta.annotation.PostConstruct;

@Service
public class PdfReportLogService {
    
    @Autowired
    private PdfReportLogRepository pdfReportLogRepository;

    @Autowired
    private ExportPdfController pdfController;

    private static final String DEFAULT_ZIP_FILE_PATH = "relatorios/";

    public PdfReportLogService(PdfReportLogRepository pdfReportLogRepository) {
        this.pdfReportLogRepository = pdfReportLogRepository;
    }

    public void generateWeeklyPdfReport() {
        try {

            ResponseEntity<byte[]> response = pdfController.exportPdfsAsZip();

            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] zipData = response.getBody();

                String zipFileName = "weekly_report_" + LocalDate.now() + ".zip";

                saveZipFileLocally(zipData, zipFileName);

                PdfReportLogs log = new PdfReportLogs();
                log.setReportDate(LocalDateTime.now()); 
                log.setGeneratedAt(LocalDateTime.now());
                pdfReportLogRepository.save(log);

                System.out.println("Weekly PDF report generated and saved successfully.");
            } else {
                System.err.println("Failed to generate or download the PDF report. Status code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("Error generating or downloading the PDF report: " + e.getMessage() + e);
        }
    }

    private void saveZipFileLocally(byte[] zipData, String fileName) throws IOException {
       
        // Use a default or custom path to save the ZIP file
        String filePath = Paths.get(DEFAULT_ZIP_FILE_PATH, fileName).toString();

        // Ensure that the folder exists
        java.nio.file.Files.createDirectories(Paths.get(DEFAULT_ZIP_FILE_PATH));

        // Write the ZIP file to the specified path
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write(zipData);
        }

        System.out.println("ZIP file saved locally at: + filePath" );
    }

    private boolean isCurrentWeekReportGenerated() {
        LocalDate startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.SUNDAY));
        Optional<PdfReportLogs> reportLog = pdfReportLogRepository.findReportByWeekStart(startOfWeek);
        return reportLog.isPresent();
    }

    // @PostConstruct
    // public void checkAndGenerateReportOnStartup() {
    //     if (!isCurrentWeekReportGenerated()) {
    //         generateWeeklyPdfReport();
    //     } else {
    //         System.out.println("O relatório dessa semana já foi gerado, cheque a pasta 'relatórios'");
    //     }
    // }

}
