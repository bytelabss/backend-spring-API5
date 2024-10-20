package fatec.bytelabss.api.services.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import fatec.bytelabss.api.models.DimTempo;
import fatec.bytelabss.api.repositories.DimTempoRepository;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class DimTempoPdfService {

    private final DimTempoRepository tempoRepository;

    public DimTempoPdfService(DimTempoRepository tempoRepository) {
        this.tempoRepository = tempoRepository;
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
            Paragraph title = new Paragraph("Lista de Dimensões de Tempo")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER);
            document.add(title);

            // Criar a tabela com 4 colunas
            Table table = new Table(4);
            table.setWidth(UnitValue.createPercentValue(100));

            // Adicionar cabeçalhos
            table.addHeaderCell(new Cell().add(new Paragraph("ID")));
            table.addHeaderCell(new Cell().add(new Paragraph("Mês")));
            table.addHeaderCell(new Cell().add(new Paragraph("Ano")));
            table.addHeaderCell(new Cell().add(new Paragraph("Semestre")));

            // Adicionar os dados da tabela DIM_TEMPO
            List<DimTempo> tempos = tempoRepository.findAll();
            for (DimTempo tempo : tempos) {
                table.addCell(new Cell().add(new Paragraph(tempo.getIdTempo().toString())));
                table.addCell(new Cell().add(new Paragraph(tempo.getMes().toString())));
                table.addCell(new Cell().add(new Paragraph(tempo.getAno().toString())));
                table.addCell(new Cell().add(new Paragraph(tempo.getSemestre().toString())));
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
