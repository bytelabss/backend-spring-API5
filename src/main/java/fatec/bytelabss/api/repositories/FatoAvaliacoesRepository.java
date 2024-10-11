package fatec.bytelabss.api.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.api.dtos.CandidatoPorProcessoSeletivoDto;
import fatec.bytelabss.api.models.FatoAvaliacoes;

@Repository
public interface FatoAvaliacoesRepository extends JpaRepository<FatoAvaliacoes, Integer>{

  	@Query(nativeQuery = true, value =  "SELECT DISTINCT av.candidato as idCandidato, av.vaga, ct.processo_seletivo as idProcessoSeletivo, ca.nome as nomeCandidato, pr.nome as nomeProcessoSeletivo\n" + //
                "FROM dataviz_bytelabss.fato_avaliacoes av\n" + //
                "INNER JOIN dataviz_bytelabss.fato_contratacoes ct\n" + //
                "    ON av.vaga = ct.vaga\n" + //
                "INNER JOIN dataviz_bytelabss.dim_candidato ca\n" + //
                "    ON av.candidato = ca.id_candidato\n" + //
                "INNER JOIN dataviz_bytelabss.dim_processo_seletivo pr\n" + //
                "    ON ct.processo_seletivo = pr.id_processo_seletivo\n" + //
                "WHERE ct.processo_seletivo = :numeroProcessoSeletivo;")
    List<CandidatoPorProcessoSeletivoDto> RetornarCandidatoPorProcessoSeletivo(@Param("numeroProcessoSeletivo") int numeroProcessoSeletivo);

}
