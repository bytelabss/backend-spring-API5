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
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import fatec.bytelabss.api.controllers.ExportPdfController;
import fatec.bytelabss.api.models.PdfReportLogs;
import fatec.bytelabss.api.repositories.PdfReportLogRepository;

@Service
public class PdfReportLogService {
    
    @Autowired
    private PdfReportLogRepository pdfReportLogRepository;

    @Autowired
    private ExportPdfController pdfController;

    private static final String DEFAULT_PDF_FILE_PATH = "relatorios/";

    public PdfReportLogService(PdfReportLogRepository pdfReportLogRepository) {
        this.pdfReportLogRepository = pdfReportLogRepository;
    }

    public void generateWeeklyPdfReport() {
        try {
            // Geração e salvamento de cada PDF individualmente
            saveIndividualPdf("candidatos.pdf", pdfController.exportCandidatosPdf());
            saveIndividualPdf("criterios.pdf", pdfController.exportCriteriosPdf());
            saveIndividualPdf("participantes_RH.pdf", pdfController.exportParticipantesRHPdf());
            saveIndividualPdf("processos_seletivos.pdf", pdfController.exportProcessosSeletivosPdf());
            saveIndividualPdf("dimensoes_tempo.pdf", pdfController.exportTempoPdf());
            saveIndividualPdf("vagas.pdf", pdfController.exportVagasPdf());

            // Log de relatório
            PdfReportLogs log = new PdfReportLogs();
            log.setReportDate(LocalDateTime.now()); 
            log.setGeneratedAt(LocalDateTime.now());
            pdfReportLogRepository.save(log);

            System.out.println("Weekly PDF reports generated and saved individually.");
        } catch (Exception e) {
            System.err.println("Error generating individual PDF reports: " + e.getMessage() + e);
        }
    }

    private void saveIndividualPdf(String fileName, ResponseEntity<byte[]> response) throws IOException {
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            byte[] pdfData = response.getBody();
            
            // Use a default or custom path to save each PDF file
            String filePath = Paths.get(DEFAULT_PDF_FILE_PATH, fileName).toString();
            
            // Ensure that the folder exists
            java.nio.file.Files.createDirectories(Paths.get(DEFAULT_PDF_FILE_PATH));

            // Write the PDF file to the specified path
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                fileOutputStream.write(pdfData);
            }

            System.out.println("PDF file saved locally at: " + filePath);
        } else {
            System.err.println("Failed to generate or download the PDF: " + fileName + " Status code: " + response.getStatusCode());
        }
    }

    private boolean isCurrentWeekReportGenerated() {
        LocalDate startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.SUNDAY));
        Optional<PdfReportLogs> reportLog = pdfReportLogRepository.findReportByWeekStart(startOfWeek);
        return reportLog.isPresent();
    }

    @EventListener(ContextRefreshedEvent.class)
    public void checkAndGenerateReportOnStartup() {
        if (!isCurrentWeekReportGenerated()) {
            try {
                generateWeeklyPdfReport();
            } catch (Exception e) {
                System.err.println("Error generating individual PDF reports on startup: " + e.getMessage());
            }
        } else {
            System.out.println("O relatório dessa semana já foi gerado, cheque a pasta 'relatorios'");
        }
    }

}
