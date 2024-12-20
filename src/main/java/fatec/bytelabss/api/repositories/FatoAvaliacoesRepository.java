package fatec.bytelabss.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.api.dtos.CandidatoPorProcessoSeletivoDto;
import fatec.bytelabss.api.models.FatoAvaliacoes;

@Repository
public interface FatoAvaliacoesRepository extends JpaRepository<FatoAvaliacoes, Integer>{

    @Query(nativeQuery = true, value =  "SELECT DISTINCT av.candidato as idCandidato, av.vaga, vg.titulo_vaga as tituloVaga, ct.processo_seletivo as idProcessoSeletivo, ca.nome as nomeCandidato, pr.nome as nomeProcessoSeletivo\n" + //
            "FROM dataviz_bytelabss.fato_avaliacoes av\n" + //
            "INNER JOIN dataviz_bytelabss.fato_contratacoes ct\n" + //
            "    ON av.vaga = ct.vaga\n" + //
            "INNER JOIN dataviz_bytelabss.dim_candidato ca\n" + //
            "    ON av.candidato = ca.id_candidato\n" + //
            "INNER JOIN dataviz_bytelabss.dim_processo_seletivo pr\n" + //
            "    ON ct.processo_seletivo = pr.id_processo_seletivo\n" + //
            "INNER JOIN dataviz_bytelabss.dim_vaga vg\n" +
            "    ON av.vaga = vg.id_vaga\n" + //
            "WHERE ct.processo_seletivo = :numeroProcessoSeletivo;")
    List<CandidatoPorProcessoSeletivoDto> findByNumeroProcessoSeletivo(@Param("numeroProcessoSeletivo") int numeroProcessoSeletivo);
    
@Query(nativeQuery = true, value = "SELECT DISTINCT av.candidato AS idCandidato, av.vaga, vg.titulo_vaga AS tituloVaga, " + 
            "ct.processo_seletivo AS idProcessoSeletivo, ca.nome AS nomeCandidato, pr.nome AS nomeProcessoSeletivo " + 
            "FROM dataviz_bytelabss.fato_avaliacoes av " + 
            "INNER JOIN dataviz_bytelabss.fato_contratacoes ct ON av.vaga = ct.vaga " + 
            "INNER JOIN dataviz_bytelabss.dim_candidato ca ON av.candidato = ca.id_candidato " + 
            "INNER JOIN dataviz_bytelabss.dim_processo_seletivo pr ON ct.processo_seletivo = pr.id_processo_seletivo " + 
            "INNER JOIN dataviz_bytelabss.dim_vaga vg ON av.vaga = vg.id_vaga " + 
            "WHERE pr.nome LIKE CONCAT('%', :nomeProcessoSeletivo, '%')")
List<CandidatoPorProcessoSeletivoDto> findByNomeProcessoSeletivo(@Param("nomeProcessoSeletivo") String nomeProcessoSeletivo);

}
