package fatec.bytelabss.api.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.api.models.DimParticipanteRH;
import fatec.bytelabss.api.repositories.DimParticipanteRHRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class DimParticipanteRHExcelService {

    @Autowired
    private DimParticipanteRHRepository participanteRHRepository; // Assegure-se de ter este repositório

    public ByteArrayInputStream exportarParticipanteRHParaExcel() throws IOException {
        List<DimParticipanteRH> participanteRHs = participanteRHRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("PaticipanteRH");

            // Criando o cabeçalho
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID Participante RH");
            header.createCell(1).setCellValue("Cargo");

            // Preencher dados
            int rowNum = 1;
            for (DimParticipanteRH participanteRH : participanteRHs) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(participanteRH.getIdParticipanteRH());
                row.createCell(1).setCellValue(participanteRH.getCargo());
            }

                // Salvando o Excel em um ByteArrayOutputStream
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                workbook.write(out);
                return new ByteArrayInputStream(out.toByteArray());
            }
        
    }
}

