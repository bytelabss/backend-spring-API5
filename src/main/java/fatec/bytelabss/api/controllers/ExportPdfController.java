package fatec.bytelabss.api.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.bytelabss.api.services.pdf.DimCandidatoPdfService;
import fatec.bytelabss.api.services.pdf.DimCriterioPdfService;
import fatec.bytelabss.api.services.pdf.DimParticipanteRHPdfService;
import fatec.bytelabss.api.services.pdf.DimProcessoSeletivoPdfService;
import fatec.bytelabss.api.services.pdf.DimTempoPdfService;
import fatec.bytelabss.api.services.pdf.DimVagaPdfService;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pdf")
public class ExportPdfController {

    private final DimCandidatoPdfService dimcandidatoPdfService;
    private final DimCriterioPdfService dimcriterioPdfService;
    private final DimParticipanteRHPdfService dimparticipanteRHPdfService;
    private final DimProcessoSeletivoPdfService dimprocessoSeletivoPdfService;
    private final DimTempoPdfService dimtempoPdfService;
    private final DimVagaPdfService dimvagaPdfService;

    public ExportPdfController(DimCandidatoPdfService dimcandidatoPdfService,
                               DimCriterioPdfService dimcriterioPdfService,
                               DimParticipanteRHPdfService dimparticipanteRHPdfService,
                               DimProcessoSeletivoPdfService dimprocessoSeletivoPdfService,
                               DimTempoPdfService dimtempoPdfService,
                               DimVagaPdfService dimvagaPdfService) {
        this.dimcandidatoPdfService = dimcandidatoPdfService;
        this.dimcriterioPdfService = dimcriterioPdfService;
        this.dimparticipanteRHPdfService = dimparticipanteRHPdfService;
        this.dimprocessoSeletivoPdfService = dimprocessoSeletivoPdfService;
        this.dimtempoPdfService = dimtempoPdfService;
        this.dimvagaPdfService = dimvagaPdfService;
    }

    @GetMapping("/candidatos")
    public ResponseEntity<byte[]> exportCandidatosPdf() throws IOException {
        return exportSinglePdf("candidatos.pdf", dimcandidatoPdfService.generatePdf());
    }

    @GetMapping("/criterios")
    public ResponseEntity<byte[]> exportCriteriosPdf() throws IOException {
        return exportSinglePdf("criterios.pdf", dimcriterioPdfService.generatePdf());
    }

    @GetMapping("/participantesRH")
    public ResponseEntity<byte[]> exportParticipantesRHPdf() throws IOException {
        return exportSinglePdf("participantes_RH.pdf", dimparticipanteRHPdfService.generatePdf());
    }

    @GetMapping("/processosSeletivos")
    public ResponseEntity<byte[]> exportProcessosSeletivosPdf() throws IOException {
        return exportSinglePdf("processos_seletivos.pdf", dimprocessoSeletivoPdfService.generatePdf());
    }

    @GetMapping("/tempo")
    public ResponseEntity<byte[]> exportTempoPdf() throws IOException {
        return exportSinglePdf("dimensoes_tempo.pdf", dimtempoPdfService.generatePdf());
    }

    @GetMapping("/vagas")
    public ResponseEntity<byte[]> exportVagasPdf() throws IOException {
        return exportSinglePdf("vagas.pdf", dimvagaPdfService.generatePdf());
    }

    private ResponseEntity<byte[]> exportSinglePdf(String fileName, ByteArrayInputStream pdfStream) throws IOException {
        byte[] pdfBytes = pdfStream.readAllBytes();
        pdfStream.close();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + fileName);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
