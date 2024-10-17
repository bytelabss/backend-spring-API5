package fatec.bytelabss.api.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.bytelabss.api.services.DimCandidatoPdfService;
import fatec.bytelabss.api.services.DimCriterioPdfService;
import fatec.bytelabss.api.services.DimParticipanteRHPdfService;
import fatec.bytelabss.api.services.DimProcessoSeletivoPdfService;
import fatec.bytelabss.api.services.DimTempoPdfService;
import fatec.bytelabss.api.services.DimVagaPdfService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/api/pdf")
public class ExportPdfController {

    private final DimCandidatoPdfService dimcandidatoPdfService;
    private final DimCriterioPdfService dimcriterioPdfService;
    private final DimParticipanteRHPdfService dimparticipanteRHPdfService;
    private final DimProcessoSeletivoPdfService dimprocessoSeletivoPdfService;
    private final DimTempoPdfService dimtempoPdfService;
    private final DimVagaPdfService dimvagaPdfService;


    public ExportPdfController(DimCandidatoPdfService dimcandidatoPdfService, DimCriterioPdfService dimcriterioPdfService, DimParticipanteRHPdfService dimparticipanteRHPdfService, DimProcessoSeletivoPdfService dimprocessoSeletivoPdfService, DimTempoPdfService dimtempoPdfService, DimVagaPdfService dimvagaPdfService) {
        this.dimcandidatoPdfService = dimcandidatoPdfService;
        this.dimcriterioPdfService = dimcriterioPdfService;
        this.dimparticipanteRHPdfService = dimparticipanteRHPdfService;
        this.dimprocessoSeletivoPdfService = dimprocessoSeletivoPdfService;
        this.dimtempoPdfService = dimtempoPdfService;
        this.dimvagaPdfService = dimvagaPdfService; 
    }

    @GetMapping("/export/pdf/zip")
    public ResponseEntity<byte[]> exportPdfsAsZip() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ZipOutputStream zipOut = new ZipOutputStream(baos)) {

            // Gerar PDF de candidatos
            ByteArrayInputStream candidatoPdf = dimcandidatoPdfService.generatePdf();
            ZipEntry candidatoEntry = new ZipEntry("candidatos.pdf");
            zipOut.putNextEntry(candidatoEntry);
            byte[] candidatoBytes = candidatoPdf.readAllBytes();
            zipOut.write(candidatoBytes, 0, candidatoBytes.length);
            zipOut.closeEntry();

            // Gerar PDF de critérios
            ByteArrayInputStream criterioPdf = dimcriterioPdfService.generatePdf();
            ZipEntry criterioEntry = new ZipEntry("criterios.pdf");
            zipOut.putNextEntry(criterioEntry);
            byte[] criterioBytes = criterioPdf.readAllBytes();
            zipOut.write(criterioBytes, 0, criterioBytes.length);
            zipOut.closeEntry();

            // Gerar PDF de participantes RH
            ByteArrayInputStream participanteRhPdf = dimparticipanteRHPdfService.generatePdf();
            ZipEntry participanteRhEntry = new ZipEntry("participantes_RH.pdf");
            zipOut.putNextEntry(participanteRhEntry);
            byte[] participanteRhBytes = participanteRhPdf.readAllBytes();
            zipOut.write(participanteRhBytes, 0, participanteRhBytes.length);
            zipOut.closeEntry();

            // Gerar PDF de processos seletivos
             ByteArrayInputStream processoSeletivoPdf = dimprocessoSeletivoPdfService.generatePdf();
             ZipEntry processoSeletivoEntry = new ZipEntry("processos_seletivos.pdf");
             zipOut.putNextEntry(processoSeletivoEntry);
             byte[] processoSeletivoBytes = processoSeletivoPdf.readAllBytes();
             zipOut.write(processoSeletivoBytes, 0, processoSeletivoBytes.length);
             zipOut.closeEntry();

            // Gerar PDF de dimensões de tempo
            ByteArrayInputStream tempoPdf = dimtempoPdfService.generatePdf();
            ZipEntry tempoEntry = new ZipEntry("dimensoes_tempo.pdf");
            zipOut.putNextEntry(tempoEntry);
            byte[] tempoBytes = tempoPdf.readAllBytes();
            zipOut.write(tempoBytes, 0, tempoBytes.length);
            zipOut.closeEntry();

            // Gerar PDF de vagas
            ByteArrayInputStream vagaPdf = dimvagaPdfService.generatePdf();
            ZipEntry vagaEntry = new ZipEntry("vagas.pdf");
            zipOut.putNextEntry(vagaEntry);
            byte[] vagaBytes = vagaPdf.readAllBytes();
            zipOut.write(vagaBytes, 0, vagaBytes.length);
            zipOut.closeEntry();

            zipOut.finish();

            // Retornar o arquivo ZIP
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=exported_files.zip");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(baos.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
