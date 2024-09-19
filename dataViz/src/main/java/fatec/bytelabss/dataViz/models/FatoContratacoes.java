package fatec.bytelabss.dataViz.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FATO_CONTRATACOES")
@AllArgsConstructor
@Data
@NoArgsConstructor
@IdClass(FatoContratacaoId.class)
public class FatoContratacoes implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2377862234700427607L;


	@Column(name = "quantidade")
    private Long quantidade;
	
	@Column(name = "tempo_medio")
    private Long tempoMedio;
	
	
	@Id
	@ManyToOne
	@JoinColumn(name = "processo_seletivo")
    private DimProcessoSeletivo idProcessoSeletivo;

	@Id
	@ManyToOne
	@JoinColumn(name = "tempo")
    private DimTempo idTempo;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "vaga")
    private DimVaga idVaga;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "participante_rh")
    private DimParticipanteRH idParticipanteRH;
}
