package fatec.bytelabss.dataViz.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DIM_VAGA")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class DimVaga {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "id_vaga")
	private Long idVaga;

	@Column(name = "titulo_vaga")
	private String tituloVaga;

	@Column(name = "numero_posicoes")
	private Integer numeroPosicoes;

	@Column(name = "requisitos_vagas")
	private String requisitosVagas;

	@Column(name = "estado")
	private String estado;

}