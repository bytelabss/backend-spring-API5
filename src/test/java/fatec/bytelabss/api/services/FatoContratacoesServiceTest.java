package fatec.bytelabss.api.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fatec.bytelabss.api.dtos.ProcessoSeletivoTempoMedioDto;
import fatec.bytelabss.api.repositories.FatoContratacoesRepository;
import fatec.bytelabss.api.services.FatoContratacoesService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.*;

@SpringBootTest
public class FatoContratacoesServiceTest {

    @Mock
    private FatoContratacoesRepository repo;

    @InjectMocks
    private FatoContratacoesService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObterTempoMedioPorVagaComSucesso() {
        List<Object[]> mockResultado = Arrays.asList(
            new Object[]{"Analista de Sistemas", new BigDecimal(45.7)},
            new Object[]{"Desenvolvedor Java", new BigDecimal(50.2)}
        );
        when(repo.TempoMedioContratacoesPorVaga(1, 2023, 12, 2023)).thenReturn(mockResultado);

        Map<String, Double> resultado = service.obterTempoMedioPorVaga(1, 2023, 12, 2023);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(45.7, resultado.get("Analista de Sistemas"));
        assertEquals(50.2, resultado.get("Desenvolvedor Java"));
    }

    @Test
    public void testObterTempoMedioComParametrosIncompletos() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.obterTempoMedioPorVaga(1, null, 12, 2023);
        });
        assertEquals("Todos os parâmetros (mesInicial, anoInicial, mesFinal, anoFinal) devem ser fornecidos.", exception.getMessage());
    }

    @Test
    public void testValidarParametrosComErro() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.obterTempoMedioPorVaga(13, 2023, 12, 2023);
        });
        assertEquals("Mês inicial inválido. Deve ser entre 1 e 12.", exception.getMessage());
    }

    @Test
    public void testObterTempoMedioSemParametros() {
        List<Object[]> mockResultado = Arrays.asList(
            new Object[]{"Analista de Sistemas", new BigDecimal(60.5)},
            new Object[]{"Desenvolvedor Java", new BigDecimal(55.3)}
        );
        // Simulando o repository com parâmetros padrão
        when(repo.TempoMedioContratacoesPorVaga(1, 1900, 12, 2100)).thenReturn(mockResultado);

        Map<String, Double> resultado = service.obterTempoMedioPorVaga(null, null, null, null);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(60.5, resultado.get("Analista de Sistemas"));
        assertEquals(55.3, resultado.get("Desenvolvedor Java"));

        // Verifica se o método do repositório foi chamado com os valores padrões
        verify(repo, times(1)).TempoMedioContratacoesPorVaga(1, 1900, 12, 2100);
    }
}
