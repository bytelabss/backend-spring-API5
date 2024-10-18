package fatec.bytelabss.api.contracts;

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
