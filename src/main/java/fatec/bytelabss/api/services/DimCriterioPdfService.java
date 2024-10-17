package fatec.bytelabss.api.services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import fatec.bytelabss.api.models.DimCriterio;
import fatec.bytelabss.api.repositories.DimCriterioRepository;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class DimCriterioPdfService {

    private final DimCriterioRepository criterioRepository;

    public DimCriterioPdfService(DimCriterioRepository criterioRepository) {
        this.criterioRepository = criterioRepository;
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
            Paragraph title = new Paragraph("Lista de Critérios")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER);
            document.add(title);

            // Criar a tabela com 2 colunas
            Table table = new Table(2);
            table.setWidth(UnitValue.createPercentValue(100));

            // Adicionar cabeçalhos
            table.addHeaderCell("ID");
            table.addHeaderCell("Nome");

            // Adicionar os dados dos critérios
            List<DimCriterio> criterios = criterioRepository.findAll();
            for (DimCriterio criterio : criterios) {
                table.addCell(new Cell().add(new Paragraph(criterio.getIdCriterio().toString())));
                table.addCell(new Cell().add(new Paragraph(criterio.getNomeCriterio())));
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
