package fatec.bytelabss.api.testesUnitarios;

import fatec.bytelabss.api.controllers.ExportPdfController;
import fatec.bytelabss.api.services.pdf.DimCandidatoPdfService;
import fatec.bytelabss.api.services.pdf.DimCriterioPdfService;
import fatec.bytelabss.api.services.pdf.DimParticipanteRHPdfService;
import fatec.bytelabss.api.services.pdf.DimProcessoSeletivoPdfService;
import fatec.bytelabss.api.services.pdf.DimTempoPdfService;
import fatec.bytelabss.api.services.pdf.DimVagaPdfService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExportPdfControllerTest {

    @Mock
    private DimCandidatoPdfService dimCandidatoPdfService;
    @Mock
    private DimCriterioPdfService dimCriterioPdfService;
    @Mock
    private DimParticipanteRHPdfService dimParticipanteRHPdfService;
    @Mock
    private DimProcessoSeletivoPdfService dimProcessoSeletivoPdfService;
    @Mock
    private DimTempoPdfService dimTempoPdfService;
    @Mock
    private DimVagaPdfService dimVagaPdfService;

    @InjectMocks
    private ExportPdfController exportPdfController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa os mocks antes de cada teste
    }

    @Test
    public void testControllerInitialization() {
        // Verifica se o controlador foi instanciado corretamente
        assertNotNull(exportPdfController);
    }

    @Test
    public void testExportCandidatosPdfEndpoint() throws IOException {
        // Simula o retorno do serviço para o controlador
        ByteArrayInputStream mockStream = new ByteArrayInputStream(new byte[]{1, 2, 3});
        when(dimCandidatoPdfService.generatePdf()).thenReturn(mockStream);

        // Chama o método do controlador
        ResponseEntity<byte[]> response = exportPdfController.exportCandidatosPdf();

        // Verifica se o status da resposta é 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifica os headers da resposta
        HttpHeaders headers = response.getHeaders();
        assertNotNull(headers);
        assertTrue(headers.containsKey("Content-Disposition"));
        assertEquals("attachment; filename=candidatos.pdf", headers.get("Content-Disposition").get(0));

        // Verifica o corpo da resposta
        byte[] body = response.getBody();
        assertNotNull(body);
        assertTrue(body.length > 0);
    }

    @Test
    public void testExportCriteriosPdfEndpoint() throws IOException {
        // Simula o retorno do serviço para o controlador
        ByteArrayInputStream mockStream = new ByteArrayInputStream(new byte[]{4, 5, 6});
        when(dimCriterioPdfService.generatePdf()).thenReturn(mockStream);

        // Chama o método do controlador
        ResponseEntity<byte[]> response = exportPdfController.exportCriteriosPdf();

        // Verifica se o status da resposta é 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifica os headers da resposta
        HttpHeaders headers = response.getHeaders();
        assertNotNull(headers);
        assertTrue(headers.containsKey("Content-Disposition"));
        assertEquals("attachment; filename=criterios.pdf", headers.get("Content-Disposition").get(0));

        // Verifica o corpo da resposta
        byte[] body = response.getBody();
        assertNotNull(body);
        assertTrue(body.length > 0);
    }

    @Test
    public void testExportParticipantesRHPdfEndpoint() throws IOException {
        // Simula o retorno do serviço para o controlador
        ByteArrayInputStream mockStream = new ByteArrayInputStream(new byte[]{7, 8, 9});
        when(dimParticipanteRHPdfService.generatePdf()).thenReturn(mockStream);

        // Chama o método do controlador
        ResponseEntity<byte[]> response = exportPdfController.exportParticipantesRHPdf();

        // Verifica se o status da resposta é 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifica os headers da resposta
        HttpHeaders headers = response.getHeaders();
        assertNotNull(headers);
        assertTrue(headers.containsKey("Content-Disposition"));
        assertEquals("attachment; filename=participantes_RH.pdf", headers.get("Content-Disposition").get(0));

        // Verifica o corpo da resposta
        byte[] body = response.getBody();
        assertNotNull(body);
        assertTrue(body.length > 0);
    }

    @Test
    public void testExportProcessosSeletivosPdfEndpoint() throws IOException {
        // Simula o retorno do serviço para o controlador
        ByteArrayInputStream mockStream = new ByteArrayInputStream(new byte[]{10, 11, 12});
        when(dimProcessoSeletivoPdfService.generatePdf()).thenReturn(mockStream);

        // Chama o método do controlador
        ResponseEntity<byte[]> response = exportPdfController.exportProcessosSeletivosPdf();

        // Verifica se o status da resposta é 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifica os headers da resposta
        HttpHeaders headers = response.getHeaders();
        assertNotNull(headers);
        assertTrue(headers.containsKey("Content-Disposition"));
        assertEquals("attachment; filename=processos_seletivos.pdf", headers.get("Content-Disposition").get(0));

        // Verifica o corpo da resposta
        byte[] body = response.getBody();
        assertNotNull(body);
        assertTrue(body.length > 0);
    }

    @Test
    public void testExportTempoPdfEndpoint() throws IOException {
        // Simula o retorno do serviço para o controlador
        ByteArrayInputStream mockStream = new ByteArrayInputStream(new byte[]{13, 14, 15});
        when(dimTempoPdfService.generatePdf()).thenReturn(mockStream);

        // Chama o método do controlador
        ResponseEntity<byte[]> response = exportPdfController.exportTempoPdf();

        // Verifica se o status da resposta é 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifica os headers da resposta
        HttpHeaders headers = response.getHeaders();
        assertNotNull(headers);
        assertTrue(headers.containsKey("Content-Disposition"));
        assertEquals("attachment; filename=dimensoes_tempo.pdf", headers.get("Content-Disposition").get(0));

        // Verifica o corpo da resposta
        byte[] body = response.getBody();
        assertNotNull(body);
        assertTrue(body.length > 0);
    }

    @Test
    public void testExportVagasPdfEndpoint() throws IOException {
        // Simula o retorno do serviço para o controlador
        ByteArrayInputStream mockStream = new ByteArrayInputStream(new byte[]{16, 17, 18});
        when(dimVagaPdfService.generatePdf()).thenReturn(mockStream);

        // Chama o método do controlador
        ResponseEntity<byte[]> response = exportPdfController.exportVagasPdf();

        // Verifica se o status da resposta é 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifica os headers da resposta
        HttpHeaders headers = response.getHeaders();
        assertNotNull(headers);
        assertTrue(headers.containsKey("Content-Disposition"));
        assertEquals("attachment; filename=vagas.pdf", headers.get("Content-Disposition").get(0));

        // Verifica o corpo da resposta
        byte[] body = response.getBody();
        assertNotNull(body);
        assertTrue(body.length > 0);
    }
}

