package fatec.bytelabss.api.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fatec.bytelabss.api.models.DimVaga;
import fatec.bytelabss.api.repositories.DimVagaRepository;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class DimVagaExcelService {

    @Autowired
    private DimVagaRepository vagaRepository;

    public ByteArrayInputStream exportarVagaParaExcel() throws IOException {
        List<DimVaga> vagas = vagaRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Vagas");

            // Criando o cabeçalho
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID Vaga");
            header.createCell(1).setCellValue("Título vaga");
            header.createCell(2).setCellValue("Número de Posições");
            header.createCell(3).setCellValue("Requisitos");
            header.createCell(4).setCellValue("Estado");

            // Preencher dados
            int rowNum = 1;
            for (DimVaga vaga : vagas) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(vaga.getIdVaga());
                row.createCell(1).setCellValue(vaga.getTituloVaga());
                row.createCell(2).setCellValue(vaga.getNumeroPosicoes());
                row.createCell(3).setCellValue(vaga.getRequisitosVagas());
                row.createCell(4).setCellValue(vaga.getEstado());
            }

                // Salvando o Excel em um ByteArrayOutputStream
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                workbook.write(out);
                return new ByteArrayInputStream(out.toByteArray());
            }
    }
}
