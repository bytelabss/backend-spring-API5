package fatec.bytelabss.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.bytelabss.api.services.excel.DimCandidatoExcelService;
import fatec.bytelabss.api.services.excel.DimCriterioExcelService;
import fatec.bytelabss.api.services.excel.DimParticipanteRHExcelService;
import fatec.bytelabss.api.services.excel.DimProcessoSeletivoExcelService;
import fatec.bytelabss.api.services.excel.DimTempoExcelService;
import fatec.bytelabss.api.services.excel.DimVagaExcelService;

import java.io.ByteArrayInputStream;
import java.io.IOException;

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

    @GetMapping("/candidatos")
    public ResponseEntity<byte[]> exportCandidatos() throws IOException {
        return exportSingleFile("Candidatos.xlsx", dimCandidatoExcelService.exportarCandidatoParaExcel());
    }

    @GetMapping("/criterios")
    public ResponseEntity<byte[]> exportCriterios() throws IOException {
        return exportSingleFile("Criterios.xlsx", dimCriterioExcelService.exportarCriterioParaExcel());
    }

    @GetMapping("/participantesRH")
    public ResponseEntity<byte[]> exportParticipantesRH() throws IOException {
        return exportSingleFile("ParticipantesRH.xlsx", dimParticipanteRHExcelService.exportarParticipanteRHParaExcel());
    }

    @GetMapping("/processosSeletivos")
    public ResponseEntity<byte[]> exportProcessosSeletivos() throws IOException {
        return exportSingleFile("ProcessosSeletivos.xlsx", dimProcessoSeletivoExcelService.exportarProcessoSeletivoParaExcel());
    }

    @GetMapping("/tempo")
    public ResponseEntity<byte[]> exportTempo() throws IOException {
        return exportSingleFile("Tempo.xlsx", dimTempoExcelService.exportarTempoParaExcel());
    }

    @GetMapping("/vagas")
    public ResponseEntity<byte[]> exportVagas() throws IOException {
        return exportSingleFile("Vagas.xlsx", dimVagaExcelService.exportarVagaParaExcel());
    }

    private ResponseEntity<byte[]> exportSingleFile(String fileName, ByteArrayInputStream excelStream) throws IOException {
        byte[] excelBytes = excelStream.readAllBytes();
        excelStream.close();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + fileName);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelBytes);
    }
}
