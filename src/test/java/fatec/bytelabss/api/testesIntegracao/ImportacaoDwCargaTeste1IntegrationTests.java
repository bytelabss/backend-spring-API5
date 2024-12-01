package fatec.bytelabss.api.testesIntegracao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.io.exceptions.IOException;

import fatec.bytelabss.api.models.DimParticipanteRH;
import fatec.bytelabss.api.services.FatoContratacoesService;
import fatec.bytelabss.api.services.ImportService;
import fatec.bytelabss.api.services.excel.DimCandidatoExcelService;
import fatec.bytelabss.api.services.excel.DimParticipanteRHExcelService;
import fatec.bytelabss.api.services.excel.DimProcessoSeletivoExcelService;

@SpringBootTest
@ActiveProfiles("test")
public class ImportacaoDwCargaTeste1IntegrationTests {
	 
	 @Autowired
	 private ImportService importService;
		
	 @Autowired
	 private DimProcessoSeletivoExcelService processoSeletivo;
	 
	 @Autowired
	 private DimParticipanteRHExcelService participanteRH;
	 
	 @Autowired
	 private DimCandidatoExcelService candidatoExcel;
	 
	 @Autowired
	 private FatoContratacoesService fatoContratacoesService;


    @BeforeEach
    public void setUp() {
    	importService.Salvar("Teste1.csv");
    }
    
    @AfterAll
    public static void cleanUp(@Autowired JdbcTemplate jdbcTemplate) {
        // Limpa os dados após a execução de todos os testes
        jdbcTemplate.execute("DELETE FROM fato_contratacoes");
        jdbcTemplate.execute("DELETE FROM fato_avaliacoes");
        jdbcTemplate.execute("DELETE FROM dim_tempo");
        jdbcTemplate.execute("DELETE FROM dim_candidato");
        jdbcTemplate.execute("DELETE FROM dim_vaga");
        jdbcTemplate.execute("DELETE FROM dim_processo_seletivo");
        jdbcTemplate.execute("DELETE FROM dim_participante_rh");
        jdbcTemplate.execute("DELETE FROM dim_criterio");
        jdbcTemplate.execute("DELETE FROM dim_candidato");
    }
    
    @Test
    void testPassagemErradaParametros_DataFimAnterior() {
        var inicio = LocalDateTime.of(2024, 1, 30, 0, 0);  // Data de início
        var fim = Optional.of(LocalDateTime.of(2024, 1, 1, 0, 0));  // Data de fim anterior ao início
        
        // Espera-se que o serviço lance uma exceção, como um IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            fatoContratacoesService.RetornarTempoMedioProcessoSeletivo(inicio, fim);
        }, "Data de fim não pode ser anterior à data de início.");
    }
    
    @Test
    void testImportacaoArquivoInexistente() {
        // Espera-se que o serviço lance uma exceção, como um IllegalArgumentException
        assertThrows(AnalysisException.class, () -> {
        	importService.Salvar("Teste4.csv");
        });
    }
    
	
	  @Test
	  public void testExportarProcessoSeletivoParaExcel() throws IOException, java.io.IOException {  
	        // Ação: Executar o método a ser testado
	        ByteArrayInputStream excelStream = processoSeletivo.exportarProcessoSeletivoParaExcel();

	        // Verificação: Validar se o ByteArrayInputStream contém dados e corresponde a um arquivo Excel válido
	        assertNotNull(excelStream, "O fluxo de saída não pode ser nulo");

	        try (Workbook workbook = new XSSFWorkbook(excelStream)) {
	            Sheet sheet = workbook.getSheetAt(0);
	            assertNotNull(sheet, "A planilha não pode ser nula");

	            // Verificar se o cabeçalho está correto
	            Row header = sheet.getRow(0);
	            assertEquals("ID Processo Seletivo", header.getCell(0).getStringCellValue());
	            assertEquals("Nome", header.getCell(1).getStringCellValue());
	            assertEquals("Status", header.getCell(2).getStringCellValue());
	            assertEquals("Descrição", header.getCell(3).getStringCellValue());
	            assertEquals("Criado Por", header.getCell(4).getStringCellValue());
	            assertEquals("Início Processo Seletivo", header.getCell(5).getStringCellValue());
	            assertEquals("Fim Processo Seletivo", header.getCell(6).getStringCellValue());

	            // Verificar os dados dos processos seletivos
	            Row row1 = sheet.getRow(1);
	            assertEquals(1L, row1.getCell(0).getNumericCellValue(), "ID Processo Seletivo na linha 1 deve ser 1");
	            assertEquals("Novos estagiarios e aprendizes", row1.getCell(1).getStringCellValue(), "Nome do Processo Seletivo na linha 1 deve ser 'Novos estagiarios e aprendizes'");
	            assertEquals("pendente", row1.getCell(2).getStringCellValue(), "Status do Processo Seletivo na linha 1 deve ser 'pendente'");
	            assertEquals("Contratar novos estagiarios e aprendizes para o ano de 2024", row1.getCell(3).getStringCellValue(), "Descrição do Processo Seletivo na linha 1 deve ser 'Contratar novos estagiarios e aprendizes para o ano de 2024'");
	            assertEquals("Rodrigo", row1.getCell(4).getStringCellValue(), "Criado Por na linha 1 deve ser 'Rodrigo'");
	            assertEquals("2024-01-01T00:00", row1.getCell(5).getStringCellValue(), "Data de Início do Processo Seletivo na linha 1 deve ser '2024-01-01'");
	            assertEquals("2024-12-30T00:00", row1.getCell(6).getStringCellValue(), "Data de Fim do Processo Seletivo na linha 1 deve ser '2024-12-30'");

	            Row row2 = sheet.getRow(2);
	            assertEquals(2L, row2.getCell(0).getNumericCellValue(), "ID Processo Seletivo na linha 2 deve ser 2");
	            assertEquals("Processo seletivo 2", row2.getCell(1).getStringCellValue(), "Nome do Processo Seletivo na linha 2 deve ser 'Desenvolvedores'");
	            assertEquals("Em andamento", row2.getCell(2).getStringCellValue(), "Status do Processo Seletivo na linha 2 deve ser 'Em andamento'");
	            assertEquals("processo seletivo para devs", row2.getCell(3).getStringCellValue(), "Descrição do Processo Seletivo na linha 2 deve ser 'processo seletivo para devs'");
	            assertEquals("Rodrigo", row2.getCell(4).getStringCellValue(), "Criado Por na linha 2 deve ser 'Rodrigo'");
	            assertEquals("2024-01-01T00:00", row2.getCell(5).getStringCellValue(), "Data de Início do Processo Seletivo na linha 2 deve ser '2024-01-01'");
	            assertEquals("2024-04-05T00:00", row2.getCell(6).getStringCellValue(), "Data de Fim do Processo Seletivo na linha 2 deve ser '2024-04-05'");
	        }
	    }
	  
	  @Test
	  public void testExportarParticipantesRhParaExcel() throws IOException, java.io.IOException {  
	        // Ação: Executar o método a ser testado
	        ByteArrayInputStream excelStream = participanteRH.exportarParticipanteRHParaExcel();

	        // Verificação: Validar se o ByteArrayInputStream contém dados e corresponde a um arquivo Excel válido
	        assertNotNull(excelStream, "O fluxo de saída não pode ser nulo");

	        try (Workbook workbook = new XSSFWorkbook(excelStream)) {
	            Sheet sheet = workbook.getSheetAt(0);
	            assertNotNull(sheet, "A planilha não pode ser nula");

	            // Verificar se o cabeçalho está correto
	            Row header = sheet.getRow(0);
	            assertEquals("ID Participante RH", header.getCell(0).getStringCellValue());
	            assertEquals("Cargo", header.getCell(1).getStringCellValue());
	            
	            // Verificar os dados dos processos seletivos
	            Row row1 = sheet.getRow(1);
	            assertEquals(1L, row1.getCell(0).getNumericCellValue(), "ID Participante Rh deve ser 1");
	            assertEquals("Analista", row1.getCell(1).getStringCellValue(), "Nome deve ser 'Analista'");
	            
	            Row row2 = sheet.getRow(2);
	            assertEquals(2L, row2.getCell(0).getNumericCellValue(), "ID Participante Rh deve ser 2");
	            assertEquals("supervisor", row2.getCell(1).getStringCellValue(), "Nome deve ser 'supervisor'");
	           
	        }
	    }
	  
	  @Test
	  public void testExportarCandidatoParaExcel() throws IOException, java.io.IOException {  
	        // Ação: Executar o método a ser testado
	        ByteArrayInputStream excelStream = candidatoExcel.exportarCandidatoParaExcel();

	        // Verificação: Validar se o ByteArrayInputStream contém dados e corresponde a um arquivo Excel válido
	        assertNotNull(excelStream, "O fluxo de saída não pode ser nulo");

	        try (Workbook workbook = new XSSFWorkbook(excelStream)) {
	            Sheet sheet = workbook.getSheetAt(0);
	            assertNotNull(sheet, "A planilha não pode ser nula");

	            // Verificar se o cabeçalho está correto
	            Row header = sheet.getRow(0);
	            assertEquals("ID Candidato", header.getCell(0).getStringCellValue());
	            assertEquals("Nome", header.getCell(1).getStringCellValue());
	            
	            // Verificar os dados dos processos seletivos
	            Row row1 = sheet.getRow(1);
	            assertEquals(1L, row1.getCell(0).getNumericCellValue(), "ID candidato deve ser 1");
	            assertEquals("Ana", row1.getCell(1).getStringCellValue(), "Nome deve ser 'Ana'");
	            
	            Row row2 = sheet.getRow(2);
	            assertEquals(2L, row2.getCell(0).getNumericCellValue(), "ID candidato deve ser 2");
	            assertEquals("Bruno", row2.getCell(1).getStringCellValue(), "Nome deve ser 'Bruno'");
	           
	        }
	    }
	  
	  @Test
	  void testObterTempoMedioProcessoSeletivo() {
			
			var inicio =LocalDateTime.of(2024, 1, 1, 0, 0); 
			Optional<LocalDateTime> fim = Optional.of(LocalDateTime.of(2024, 1, 30, 0, 0));
				
			var listaTempoMedio = fatoContratacoesService.RetornarTempoMedioProcessoSeletivo(inicio, fim);
			
			for (var tempoMedio : listaTempoMedio) {
				if(tempoMedio.getProcesso_seletivo() == 1) {
					assertEquals(49, tempoMedio.getTempo_medio());
				}
				else if(tempoMedio.getProcesso_seletivo() == 2) {
					assertEquals(7, tempoMedio.getTempo_medio());
				}
				else if(tempoMedio.getProcesso_seletivo() == 3) {
					assertEquals(65, tempoMedio.getTempo_medio());
				}
				
			}
	  }
	  
	  @Test
	  void testObterQuantidadeContratacoesProcessoSeletivo() {
			
			var inicio =LocalDateTime.of(2024, 1, 1, 0, 0); 
			Optional<LocalDateTime> fim = Optional.of(LocalDateTime.of(2024, 1, 30, 0, 0));
				
			var listaQuantidadeProcessoSeletivo = fatoContratacoesService.RetornarQuantidadeProcessoSeletivo(inicio, fim);
			
			for (var qunatidadeProcesso : listaQuantidadeProcessoSeletivo) {
				if(qunatidadeProcesso.getProcesso_seletivo() == 1) {
					assertEquals(21, qunatidadeProcesso.getQuantidade());
				}
				else if(qunatidadeProcesso.getProcesso_seletivo() == 2) {
					assertEquals(48, qunatidadeProcesso.getQuantidade());
				}
				else if(qunatidadeProcesso.getProcesso_seletivo() == 3) {
					assertEquals(13, qunatidadeProcesso.getQuantidade());
				}
				
			}
	  }
	  
	  @Test
	  void testObterTempoMedioVaga() {
				
			var tempoMedioVaga = fatoContratacoesService.obterTempoMedioPorVaga(1,2024,12,2025);
			

			assertEquals(null, tempoMedioVaga.get("Estagiario"));

			assertEquals(16.75, tempoMedioVaga.get("Aprendiz"));

			assertEquals(18.0, tempoMedioVaga.get("Dev Jr"));

	  }
}
