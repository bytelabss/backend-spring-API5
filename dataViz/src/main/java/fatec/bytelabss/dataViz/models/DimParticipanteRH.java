package fatec.bytelabss.dataViz.models;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_participante_rh")
    private Long idParticipanteRH;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "feedback_dados")
    private Integer feedbackDados;

}