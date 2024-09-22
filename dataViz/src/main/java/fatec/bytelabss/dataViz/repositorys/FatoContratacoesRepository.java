package fatec.bytelabss.dataViz.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.dataViz.dtos.ProcessoSeletivoTempoMedioDto;
import fatec.bytelabss.dataViz.models.DimTempo;
import fatec.bytelabss.dataViz.models.FatoContratacoes;

@Repository
public interface FatoContratacoesRepository extends JpaRepository<FatoContratacoes, Integer>{

	@Query(nativeQuery = true, value =  "SELECT a.processo_seletivo, sum(a.tempo_medio) / count(a.processo_seletivo) tempo_medio, b.nome FROM FATO_CONTRATACOES a INNER JOIN DIM_PROCESSO_SELETIVO b ON b.id_processo_seletivo = a.processo_seletivo GROUP BY a.processo_seletivo")
    List<ProcessoSeletivoTempoMedioDto> RetornarTempoMedioProcessoSeletivo();
	
}
