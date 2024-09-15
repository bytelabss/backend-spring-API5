package fatec.bytelabss.dataViz.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BYTELABSS_PARTICIPANTERH")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class BytelabssParticipantesRH {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;

	@Column(name = "cargo")
    private String cargo;

	@Column(name = "feedback_dados")
    private int feedbackdados;

}