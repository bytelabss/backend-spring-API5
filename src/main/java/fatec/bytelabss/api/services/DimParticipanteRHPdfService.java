package fatec.bytelabss.api.services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import fatec.bytelabss.api.models.DimParticipanteRH;
import fatec.bytelabss.api.repositories.DimParticipanteRHRepository;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class DimParticipanteRHPdfService {

    private final DimParticipanteRHRepository dimparticipanteRHRepository;

    public DimParticipanteRHPdfService(DimParticipanteRHRepository dimparticipanteRHRepository) {
        this.dimparticipanteRHRepository = dimparticipanteRHRepository;
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
            Paragraph title = new Paragraph("Lista de Participantes RH")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER);
            document.add(title);

            // Criar a tabela com 2 colunas
            Table table = new Table(2);
            table.setWidth(UnitValue.createPercentValue(100));

            // Adicionar cabeçalhos
            table.addHeaderCell(new Cell().add(new Paragraph("ID")));
            table.addHeaderCell(new Cell().add(new Paragraph("Cargo")));

            // Adicionar os dados dos participantes
            List<DimParticipanteRH> participantes = dimparticipanteRHRepository.findAll();
            for (DimParticipanteRH participante : participantes) {
                table.addCell(new Cell().add(new Paragraph(participante.getIdParticipanteRH().toString())));
                table.addCell(new Cell().add(new Paragraph(participante.getCargo())));
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

