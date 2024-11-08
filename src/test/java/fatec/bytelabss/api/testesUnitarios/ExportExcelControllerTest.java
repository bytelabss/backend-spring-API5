package fatec.bytelabss.api.testesUnitarios;

import fatec.bytelabss.api.controllers.ExportExcelController;
import fatec.bytelabss.api.services.excel.DimCandidatoExcelService;
import fatec.bytelabss.api.services.excel.DimCriterioExcelService;
import fatec.bytelabss.api.services.excel.DimParticipanteRHExcelService;
import fatec.bytelabss.api.services.excel.DimProcessoSeletivoExcelService;
import fatec.bytelabss.api.services.excel.DimTempoExcelService;
import fatec.bytelabss.api.services.excel.DimVagaExcelService;
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

public class ExportExcelControllerTest {

    @Mock
    private DimCandidatoExcelService dimCandidatoExcelService;
    @Mock
    private DimCriterioExcelService dimCriterioExcelService;
    @Mock
    private DimParticipanteRHExcelService dimParticipanteRHExcelService;
    @Mock
    private DimProcessoSeletivoExcelService dimProcessoSeletivoExcelService;
    @Mock
    private DimTempoExcelService dimTempoExcelService;
    @Mock
    private DimVagaExcelService dimVagaExcelService;

    @InjectMocks
    private ExportExcelController exportExcelController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa os mocks antes de cada teste
    }

    @Test
    public void testControllerInitialization() {
        // Verifica se o controlador foi instanciado corretamente
        assertNotNull(exportExcelController);
    }

    @Test
    public void testExportCandidatosEndpoint() throws IOException {
        // Simula o retorno do serviço para o controlador
        ByteArrayInputStream mockStream = new ByteArrayInputStream(new byte[]{1, 2, 3});
        when(dimCandidatoExcelService.exportarCandidatoParaExcel()).thenReturn(mockStream);

        // Chama o método do controlador
        ResponseEntity<byte[]> response = exportExcelController.exportCandidatos();

        // Verifica se o status da resposta é 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifica os headers da resposta
        HttpHeaders headers = response.getHeaders();
        assertNotNull(headers);
        assertTrue(headers.containsKey("Content-Disposition"));
        assertEquals("attachment; filename=Candidatos.xlsx", headers.get("Content-Disposition").get(0));

        // Verifica o corpo da resposta
        byte[] body = response.getBody();
        assertNotNull(body);
        assertTrue(body.length > 0);
    }

    @Test
    public void testExportCriteriosEndpoint() throws IOException {
        // Simula o retorno do serviço para o controlador
        ByteArrayInputStream mockStream = new ByteArrayInputStream(new byte[]{4, 5, 6});
        when(dimCriterioExcelService.exportarCriterioParaExcel()).thenReturn(mockStream);

        // Chama o método do controlador
        ResponseEntity<byte[]> response = exportExcelController.exportCriterios();

        // Verifica se o status da resposta é 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifica os headers da resposta
        HttpHeaders headers = response.getHeaders();
        assertNotNull(headers);
        assertTrue(headers.containsKey("Content-Disposition"));
        assertEquals("attachment; filename=Criterios.xlsx", headers.get("Content-Disposition").get(0));

        // Verifica o corpo da resposta
        byte[] body = response.getBody();
        assertNotNull(body);
        assertTrue(body.length > 0);
    }

    // Teste similar para os outros métodos
    @Test
    public void testExportParticipantesRHEndpoint() throws IOException {
        // Simula o retorno do serviço para o controlador
        ByteArrayInputStream mockStream = new ByteArrayInputStream(new byte[]{7, 8, 9});
        when(dimParticipanteRHExcelService.exportarParticipanteRHParaExcel()).thenReturn(mockStream);

        // Chama o método do controlador
        ResponseEntity<byte[]> response = exportExcelController.exportParticipantesRH();

        // Verifica se o status da resposta é 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifica os headers da resposta
        HttpHeaders headers = response.getHeaders();
        assertNotNull(headers);
        assertTrue(headers.containsKey("Content-Disposition"));
        assertEquals("attachment; filename=ParticipantesRH.xlsx", headers.get("Content-Disposition").get(0));

        // Verifica o corpo da resposta
        byte[] body = response.getBody();
        assertNotNull(body);
        assertTrue(body.length > 0);
    }

}
