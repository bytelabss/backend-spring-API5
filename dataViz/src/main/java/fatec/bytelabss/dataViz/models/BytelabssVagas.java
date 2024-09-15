package fatec.bytelabss.dataViz.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BYTELABSS_VAGAS")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class BytelabssVagas {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;

	@Column(name = "titulo_vaga")
    private String titulovaga;

	@Column(name = "numero_posicoes")
    private int numeroposicoes;

	@Column(name = "requisitos_vagas")
	private String requisitosvagas;

	@Column(name = "estado")
    private String estado;

}