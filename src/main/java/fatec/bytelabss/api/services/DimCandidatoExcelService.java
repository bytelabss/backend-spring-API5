package fatec.bytelabss.api.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fatec.bytelabss.api.models.DimCandidato;
import fatec.bytelabss.api.repositories.DimCandidatoRepository;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class DimCandidatoExcelService {

    @Autowired
    private DimCandidatoRepository candidatoRepository; // Assegure-se de ter este repositório

    public ByteArrayInputStream exportarCandidatoParaExcel() throws IOException {
        List<DimCandidato> candidatos = candidatoRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Candidatos");

            // Criando o cabeçalho
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID Candidato");
            header.createCell(1).setCellValue("Nome");

            // Preencher dados
            int rowNum = 1;
            for (DimCandidato candidato : candidatos) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(candidato.getIdCandidato());
                row.createCell(1).setCellValue(candidato.getNomeCandidato());
            }

                // Salvando o Excel em um ByteArrayOutputStream
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                workbook.write(out);
                return new ByteArrayInputStream(out.toByteArray());
            }
    }
}
