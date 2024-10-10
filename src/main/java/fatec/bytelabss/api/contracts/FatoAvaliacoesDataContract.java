package fatec.bytelabss.api.contracts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class FatoAvaliacoesDataContract {

	 	private Long pontuacao;

	    private Long idCriterio;

	    private Long idTempo;

	    private Long idVaga;

	    private Long idCandidato;

}
