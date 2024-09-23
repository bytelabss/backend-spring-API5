package fatec.bytelabss.dataViz.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.dataViz.dtos.ProcessoSeletivoQuantidadeDto;
import fatec.bytelabss.dataViz.dtos.ProcessoSeletivoTempoMedioDto;
import fatec.bytelabss.dataViz.dtos.QuantidadeContratacoesRhDto;
import fatec.bytelabss.dataViz.models.DimTempo;
import fatec.bytelabss.dataViz.models.FatoContratacoes;

@Repository
public interface FatoContratacoesRepository extends JpaRepository<FatoContratacoes, Integer>{

	@Query(nativeQuery = true, value =  "SELECT a.processo_seletivo, sum(a.tempo_medio) / count(a.processo_seletivo) tempo_medio, b.nome FROM fato_contratacoes a INNER JOIN dim_processo_seletivo b ON b.id_processo_seletivo = a.processo_seletivo GROUP BY a.processo_seletivo")
    List<ProcessoSeletivoTempoMedioDto> RetornarTempoMedioProcessoSeletivo();

    @Query(nativeQuery = true, value =  "SELECT a.processo_seletivo, b.nome, count(a.processo_seletivo) quantidade FROM fato_contratacoes a INNER JOIN dim_processo_seletivo b ON b.id_processo_seletivo = a.processo_seletivo GROUP BY a.processo_seletivo")
    List<ProcessoSeletivoQuantidadeDto> RetornarQuantidadeProcessoSeletivo();

    @Query(nativeQuery = true, value = "SELECT r.id_participante_rh, r.cargo, SUM(fc.quantidade) AS totalContratacoes " +
    "FROM fato_contratacoes fc " +
    "JOIN dim_participante_rh r ON fc.participante_rh = r.id_participante_rh " +
    "GROUP BY r.id_participante_rh, r.cargo")
    List<QuantidadeContratacoesRhDto> RetornarQuantidadeContratacoesRH();
	
}
