package fatec.bytelabss.api.services.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.api.models.DimTempo;
import fatec.bytelabss.api.repositories.DimTempoRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class DimTempoExcelService {

    @Autowired
    private DimTempoRepository tempoRepository;

    public ByteArrayInputStream exportarTempoParaExcel() throws IOException {
        List<DimTempo> tempos = tempoRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Tempos");

            // Criando o cabeçalho
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID Tempo");
            header.createCell(1).setCellValue("Mês");
            header.createCell(2).setCellValue("Ano");
            header.createCell(3).setCellValue("Semestre");
            header.createCell(4).setCellValue("Trimestre");

            // Preencher dados
            int rowNum = 1;
            for (DimTempo tempo : tempos) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(tempo.getIdTempo());
                row.createCell(1).setCellValue(tempo.getMes());
                row.createCell(2).setCellValue(tempo.getAno());
                row.createCell(3).setCellValue(tempo.getSemestre());
                row.createCell(4).setCellValue(tempo.getTrimestre());
            }

                // Salvando o Excel em um ByteArrayOutputStream
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                workbook.write(out);
                return new ByteArrayInputStream(out.toByteArray());
            }
    }
}
