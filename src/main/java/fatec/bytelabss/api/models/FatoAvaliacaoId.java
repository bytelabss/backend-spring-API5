package fatec.bytelabss.api.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Embeddable
public class FatoAvaliacaoId implements Serializable{

	private static final long serialVersionUID = 9106393730452652077L;

	private Long idCandidato;

    private Long idTempo;

    private Long idVaga;

    private Long idCriterio;
}
