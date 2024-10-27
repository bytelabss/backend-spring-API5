package fatec.bytelabss.api.testesIntegracao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

import fatec.bytelabss.api.services.FatoContratacoesService;

@SpringBootTest
public class FatoContratacoesIntegrationTests {
	
	@Autowired
	private FatoContratacoesService service;
	
	@Test
	void testObterQuantidadeRegistrosTempoMedio() {
		
		var inicio =LocalDateTime.of(2024, 1, 1, 0, 0); 
		Optional<LocalDateTime> fim = Optional.of(LocalDateTime.of(2024, 1, 30, 0, 0));
			
		var listaTempoMedio = service.RetornarTempoMedioProcessoSeletivo(inicio, fim);
		
		assertEquals(3, listaTempoMedio.size());	
	}

}
