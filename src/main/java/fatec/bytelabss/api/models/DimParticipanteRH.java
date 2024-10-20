package fatec.bytelabss.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DIM_PARTICIPANTE_RH")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class DimParticipanteRH {


	@Id
    @Column(name = "id_participante_rh")
    private Long idParticipanteRH;

    @Column(name = "cargo")
    private String cargo;


}
