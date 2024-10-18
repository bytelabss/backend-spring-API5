package fatec.bytelabss.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.bytelabss.api.services.DimCandidatoExcelService;
import fatec.bytelabss.api.services.DimCriterioExcelService;
import fatec.bytelabss.api.services.DimParticipanteRHExcelService;
import fatec.bytelabss.api.services.DimProcessoSeletivoExcelService;
import fatec.bytelabss.api.services.DimTempoExcelService;
import fatec.bytelabss.api.services.DimVagaExcelService;

import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/excel")
public class ExportExcelController {

    @Autowired
    private DimCandidatoExcelService dimCandidatoExcelService;

    @Autowired
    private DimCriterioExcelService dimCriterioExcelService;

    @Autowired
    private DimParticipanteRHExcelService dimParticipanteRHExcelService;

    @Autowired
    private DimProcessoSeletivoExcelService dimProcessoSeletivoExcelService;

    @Autowired
    private DimTempoExcelService dimTempoExcelService;

    @Autowired
    private DimVagaExcelService dimVagaExcelService;

    @GetMapping("/relatorios")
    public ResponseEntity<byte[]> exportAllToZip(HttpServletResponse response) throws IOException {
        // ByteArrayOutputStream para armazenar o ZIP
        ByteArrayOutputStream zipOutputStream = new ByteArrayOutputStream();

        // Criar um ZipOutputStream
        try (ZipOutputStream zipOut = new ZipOutputStream(zipOutputStream)) {
            // Gerar cada arquivo Excel e adicioná-lo ao ZIP
            addExcelToZip(zipOut, "Candidatos.xlsx", dimCandidatoExcelService.exportarCandidatoParaExcel());
            addExcelToZip(zipOut, "Criterios.xlsx", dimCriterioExcelService.exportarCriterioParaExcel());
            addExcelToZip(zipOut, "ParticipantesRH.xlsx", dimParticipanteRHExcelService.exportarParticipanteRHParaExcel());
            addExcelToZip(zipOut, "ProcessosSeletivos.xlsx", dimProcessoSeletivoExcelService.exportarProcessoSeletivoParaExcel());
            addExcelToZip(zipOut, "Tempo.xlsx", dimTempoExcelService.exportarTempoParaExcel());
            addExcelToZip(zipOut, "Vagas.xlsx", dimVagaExcelService.exportarVagaParaExcel());
        }

        // Definir os headers e preparar a resposta
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=relatorios.zip");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(zipOutputStream.toByteArray());
    }

    // Método auxiliar para adicionar cada arquivo Excel no ZIP
    private void addExcelToZip(ZipOutputStream zipOut, String fileName, ByteArrayInputStream excelStream) throws IOException {
        // Criar uma entrada no ZIP para o arquivo Excel
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);

        // Escrever o conteúdo do arquivo Excel para o ZIP
        byte[] buffer = new byte[1024];
        int len;
        while ((len = excelStream.read(buffer)) > 0) {
            zipOut.write(buffer, 0, len);
        }

        zipOut.closeEntry();
        excelStream.close();  // Fechar o stream do arquivo Excel
    }
}






