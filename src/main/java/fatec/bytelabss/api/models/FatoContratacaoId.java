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
public class FatoContratacaoId implements Serializable{


	/**
	 *
	 */
	private static final long serialVersionUID = -1376506488121924900L;


	private Long idProcessoSeletivo;


    private Long idTempo;

    private Long idVaga;

    private Long idParticipanteRH;
}
