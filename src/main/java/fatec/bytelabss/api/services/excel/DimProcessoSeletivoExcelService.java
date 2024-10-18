package fatec.bytelabss.api.services.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.api.models.DimProcessoSeletivo;
import fatec.bytelabss.api.repositories.DimProcessoSeletivoRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class DimProcessoSeletivoExcelService {

    @Autowired
    private DimProcessoSeletivoRepository processoSeletivoRepository;

    public ByteArrayInputStream exportarProcessoSeletivoParaExcel() throws IOException {
        List<DimProcessoSeletivo> processosSeletivos = processoSeletivoRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Processos Seletivos");

            // Criando o cabeçalho
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID Processo Seletivo");
            header.createCell(1).setCellValue("Nome");
            header.createCell(2).setCellValue("Status");
            header.createCell(3).setCellValue("Descrição");
            header.createCell(4).setCellValue("Criado Por");
            header.createCell(5).setCellValue("Início Processo Seletivo");
            header.createCell(6).setCellValue("Fim Processo Seletivo");

            // Preenchendo os dados
            int rowIdx = 1;
            for (DimProcessoSeletivo processoSeletivo : processosSeletivos) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(processoSeletivo.getIdProcessoSeletivo());
                row.createCell(1).setCellValue(processoSeletivo.getNome());
                row.createCell(2).setCellValue(processoSeletivo.getStatus());
                row.createCell(3).setCellValue(processoSeletivo.getDescricao());
                row.createCell(4).setCellValue(processoSeletivo.getCriadoPor());
                row.createCell(5).setCellValue(processoSeletivo.getInicioProcessoSeletivo().toString());
                row.createCell(6).setCellValue(processoSeletivo.getFimProcessoSeletivo().toString());
            }

            // Salvando o Excel em um ByteArrayOutputStream
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}


