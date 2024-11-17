package fatec.bytelabss.api.testesUnitarios;

import fatec.bytelabss.api.controllers.ExportPdfController;
import fatec.bytelabss.api.services.pdf.DimCandidatoPdfService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ExportPdfControllerSpec {

    @InjectMocks
    private ExportPdfController exportPdfController;

    @Mock
    private DimCandidatoPdfService dimCandidatoPdfService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExportCandidatosPdf() throws IOException {
        // Mock do serviço para retornar um fluxo de dados fictício
        byte[] mockPdfData = new byte[]{0, 1, 2, 3, 4};
        ByteArrayInputStream mockStream = new ByteArrayInputStream(mockPdfData);
        when(dimCandidatoPdfService.generatePdf()).thenReturn(mockStream);

        // Executa o método de exportação
        ResponseEntity<byte[]> response = exportPdfController.exportCandidatosPdf();

        // Verifica o status da resposta
        assertEquals(200, response.getStatusCodeValue(), "O status da resposta deve ser 200 (OK)");

        // Verifica os headers
        assertEquals(MediaType.APPLICATION_PDF, response.getHeaders().getContentType(), "Content-Type deve ser APPLICATION_PDF");
        assertEquals("attachment; filename=candidatos.pdf", response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION), "Content-Disposition deve estar correto");

        // Verifica o conteúdo do arquivo PDF
        byte[] responseBody = response.getBody();
        assertEquals(mockPdfData.length, responseBody.length, "O tamanho do conteúdo deve corresponder ao mock");
        for (int i = 0; i < mockPdfData.length; i++) {
            assertEquals(mockPdfData[i], responseBody[i], "O conteúdo do PDF deve corresponder ao mock");
        }
    }
}
