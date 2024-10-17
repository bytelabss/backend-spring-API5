package fatec.bytelabss.api.services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import fatec.bytelabss.api.models.DimVaga;
import fatec.bytelabss.api.repositories.DimVagaRepository;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class DimVagaPdfService {

    private final DimVagaRepository vagaRepository;

    public DimVagaPdfService(DimVagaRepository vagaRepository) {
        this.vagaRepository = vagaRepository;
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
            Paragraph title = new Paragraph("Lista de Vagas")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER);
            document.add(title);

            // Criar a tabela com 5 colunas
            Table table = new Table(5);
            table.setWidth(UnitValue.createPercentValue(100));

            // Adicionar cabeçalhos
            table.addHeaderCell(new Cell().add(new Paragraph("ID")));
            table.addHeaderCell(new Cell().add(new Paragraph("Título da Vaga")));
            table.addHeaderCell(new Cell().add(new Paragraph("Número de Posições")));
            table.addHeaderCell(new Cell().add(new Paragraph("Requisitos")));
            table.addHeaderCell(new Cell().add(new Paragraph("Estado")));

            // Adicionar os dados da tabela DIM_VAGA
            List<DimVaga> vagas = vagaRepository.findAll();
            for (DimVaga vaga : vagas) {
                table.addCell(new Cell().add(new Paragraph(vaga.getIdVaga().toString())));
                table.addCell(new Cell().add(new Paragraph(vaga.getTituloVaga())));
                table.addCell(new Cell().add(new Paragraph(vaga.getNumeroPosicoes().toString())));
                table.addCell(new Cell().add(new Paragraph(vaga.getRequisitosVagas())));
                table.addCell(new Cell().add(new Paragraph(vaga.getEstado())));
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
