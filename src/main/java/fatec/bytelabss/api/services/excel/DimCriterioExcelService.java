package fatec.bytelabss.api.services.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.api.models.DimCriterio;
import fatec.bytelabss.api.repositories.DimCriterioRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class DimCriterioExcelService {

    @Autowired
    private DimCriterioRepository criterioRepository; // Assegure-se de ter este repositório

    public ByteArrayInputStream exportarCriterioParaExcel() throws IOException {
        List<DimCriterio> criterios = criterioRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Criterios");

            // Criando o cabeçalho
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID Criterio");
            header.createCell(1).setCellValue("Nome Criterio");

            // Preencher dados
            int rowNum = 1;
            for (DimCriterio criterio : criterios) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(criterio.getIdCriterio());
                row.createCell(1).setCellValue(criterio.getNomeCriterio());
            }


                // Salvando o Excel em um ByteArrayOutputStream
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                workbook.write(out);
                return new ByteArrayInputStream(out.toByteArray());
            }
        
    }
}
