package fatec.bytelabss.dataViz.DataContracts;

import fatec.bytelabss.dataViz.models.DimProcessoSeletivo;
import fatec.bytelabss.dataViz.models.DimTempo;
import fatec.bytelabss.dataViz.models.FatoContratacaoId;
import fatec.bytelabss.dataViz.models.FatoContratacoes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class FatoContratacoesDataContract {
	
    private Long quantidade;
	
    private Long idProcessoSeletivo;

    private Long idTempo;
    
    private Long idVaga;
    
    private Long idParticipanteRh;
    
    private Double tempoMedio;
}
