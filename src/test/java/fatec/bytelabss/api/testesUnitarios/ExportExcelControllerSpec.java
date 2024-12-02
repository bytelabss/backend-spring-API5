package fatec.bytelabss.api.testesUnitarios;

import fatec.bytelabss.api.controllers.ExportExcelController;
import fatec.bytelabss.api.services.excel.DimCandidatoExcelService;
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

public class ExportExcelControllerSpec {

    @InjectMocks
    private ExportExcelController exportExcelController;

    @Mock
    private DimCandidatoExcelService dimCandidatoExcelService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExportCandidatos() throws IOException {
        // Mock do serviço para retornar um fluxo de dados fictício
        byte[] mockExcelData = new byte[] {0, 1, 2, 3};
        ByteArrayInputStream mockStream = new ByteArrayInputStream(mockExcelData);
        when(dimCandidatoExcelService.exportarCandidatoParaExcel()).thenReturn(mockStream);

        // Executa o método de exportação
        ResponseEntity<byte[]> response = exportExcelController.exportCandidatos();

        // Verifica o conteúdo da resposta
        assertEquals(200, response.getStatusCodeValue(), "Status code deve ser 200");
        assertEquals(MediaType.APPLICATION_OCTET_STREAM, response.getHeaders().getContentType(), "Content-Type deve ser APPLICATION_OCTET_STREAM");
        assertEquals("attachment; filename=Candidatos.xlsx", response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION), "Content-Disposition deve estar correto");
        assertEquals(mockExcelData.length, response.getBody().length, "O tamanho do arquivo exportado deve estar correto");

        // Verifica se o conteúdo do arquivo é o esperado
        for (int i = 0; i < mockExcelData.length; i++) {
            assertEquals(mockExcelData[i], response.getBody()[i], "O conteúdo do arquivo exportado deve corresponder ao mock");
        }
    }
}
