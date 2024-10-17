package fatec.bytelabss.api.services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import fatec.bytelabss.api.models.DimCandidato;
import fatec.bytelabss.api.repositories.DimCandidatoRepository;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class DimCandidatoPdfService {

    private final DimCandidatoRepository dimcandidatoRepository;

    public DimCandidatoPdfService(DimCandidatoRepository dimcandidatoRepository) {
        this.dimcandidatoRepository = dimcandidatoRepository;
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
            Paragraph title = new Paragraph("Lista de Candidatos")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER);
            document.add(title);

            // Criar a tabela com 2 colunas
            Table table = new Table(2);
            table.setWidth(UnitValue.createPercentValue(100));

            // Adicionar cabeçalhos
            table.addHeaderCell(new Cell().add(new Paragraph("ID")));
            table.addHeaderCell(new Cell().add(new Paragraph("Nome")));

            // Adicionar os dados dos candidatos
            List<DimCandidato> candidatos = dimcandidatoRepository.findAll(); // Corrigido para usar dimcandidatoRepository
            for (DimCandidato candidato : candidatos) {
                table.addCell(new Cell().add(new Paragraph(candidato.getIdCandidato().toString())));
                table.addCell(new Cell().add(new Paragraph(candidato.getNomeCandidato())));
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
