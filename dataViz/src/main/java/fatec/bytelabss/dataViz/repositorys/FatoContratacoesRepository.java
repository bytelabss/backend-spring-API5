package fatec.bytelabss.dataViz.repositorys;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.dataViz.dtos.ProcessoSeletivoQuantidadeDto;
import fatec.bytelabss.dataViz.dtos.ProcessoSeletivoTempoMedioDto;
import fatec.bytelabss.dataViz.dtos.QuantidadeContratacoesRhDto;
import fatec.bytelabss.dataViz.models.DimTempo;
import fatec.bytelabss.dataViz.models.FatoContratacoes;

@Repository
public interface FatoContratacoesRepository extends JpaRepository<FatoContratacoes, Integer>{


  	@Query(nativeQuery = true, value =  "SELECT a.processo_seletivo, sum(a.tempo_medio) / count(a.processo_seletivo) tempo_medio, b.nome FROM FATO_CONTRATACOES a INNER JOIN DIM_PROCESSO_SELETIVO b ON b.id_processo_seletivo = a.processo_seletivo WHERE (b.inicio_processo_seletivo < :fim || :fim is null) and (b.fim_processo_seletivo > :inicio or b.fim_processo_seletivo is null)  GROUP BY a.processo_seletivo")
    List<ProcessoSeletivoTempoMedioDto> RetornarTempoMedioProcessoSeletivo(@Param("inicio") LocalDateTime inicio, @Param("fim") Optional<LocalDateTime> fim);


    @Query(nativeQuery = true, value =  "SELECT a.processo_seletivo, b.nome, count(a.processo_seletivo) quantidade FROM fato_contratacoes a INNER JOIN dim_processo_seletivo b ON b.id_processo_seletivo = a.processo_seletivo GROUP BY a.processo_seletivo")
    List<ProcessoSeletivoQuantidadeDto> RetornarQuantidadeProcessoSeletivo();

    @Query(nativeQuery = true, value = "SELECT r.id_participante_rh, r.cargo, SUM(fc.quantidade) AS totalContratacoes " +
    "FROM fato_contratacoes fc " +
    "JOIN dim_participante_rh r ON fc.participante_rh = r.id_participante_rh " +
    "GROUP BY r.id_participante_rh, r.cargo")
    List<QuantidadeContratacoesRhDto> RetornarQuantidadeContratacoesRH();
	
}
