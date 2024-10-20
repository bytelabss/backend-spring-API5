package fatec.bytelabss.api.services.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import fatec.bytelabss.api.models.DimProcessoSeletivo;
import fatec.bytelabss.api.repositories.DimProcessoSeletivoRepository;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class DimProcessoSeletivoPdfService {

    private final DimProcessoSeletivoRepository processoSeletivoRepository;

    public DimProcessoSeletivoPdfService(DimProcessoSeletivoRepository processoSeletivoRepository) {
        this.processoSeletivoRepository = processoSeletivoRepository;
    }

    public ByteArrayInputStream generatePdf() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // Criar um PDFWriter com o stream de saída
            PdfWriter writer = new PdfWriter(out);
            // Criar o documento PDF
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Título
            Paragraph title = new Paragraph("Lista de Processos Seletivos")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER);
            document.add(title);

            // Criar a tabela com 4 colunas
            Table table = new Table(4);
            table.setWidth(UnitValue.createPercentValue(100));


            // Adicionar cabeçalhos
            table.addHeaderCell(new Cell().add(new Paragraph("ID")));
            table.addHeaderCell(new Cell().add(new Paragraph("Nome")));
            table.addHeaderCell(new Cell().add(new Paragraph("Status")));
            table.addHeaderCell(new Cell().add(new Paragraph("Início")));

            // Adicionar os dados dos processos seletivos
            List<DimProcessoSeletivo> processos = processoSeletivoRepository.findAll();
            for (DimProcessoSeletivo processo : processos) {
                table.addCell(new Cell().add(new Paragraph(processo.getIdProcessoSeletivo().toString())));
                table.addCell(new Cell().add(new Paragraph(processo.getNome())));
                table.addCell(new Cell().add(new Paragraph(processo.getStatus())));
                table.addCell(new Cell().add(new Paragraph(processo.getInicioProcessoSeletivo().toString())));
            }

            // Adicionar a tabela ao documento
            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
