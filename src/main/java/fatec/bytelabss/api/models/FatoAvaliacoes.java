package fatec.bytelabss.api.models;

import java.io.Serializable;

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

@Entity
@Table(name = "fato_avaliacoes")
@AllArgsConstructor
@Data
@NoArgsConstructor
@IdClass(FatoAvaliacaoId.class)
public class FatoAvaliacoes implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -5653218123323751193L;

	@Column(name = "pontuacao")
    private Long pontuacao;

	@Id
	@ManyToOne
	@JoinColumn(name = "criterio")
    private DimProcessoSeletivo idCriterio;

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
	@JoinColumn(name = "candidato")
    private DimParticipanteRH idCandidato;
}
