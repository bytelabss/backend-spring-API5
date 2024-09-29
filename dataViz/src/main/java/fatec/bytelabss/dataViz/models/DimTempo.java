package fatec.bytelabss.dataViz.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DIM_TEMPO", uniqueConstraints = { @UniqueConstraint(name = "UniqueNumberAndStatus", columnNames = { "mes", "ano", "semestre", "trimestre" }) })
@AllArgsConstructor
@Data
@NoArgsConstructor
public class DimTempo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tempo")
    private Long idTempo;

	
	@Column(name = "mes")
    private Integer mes;

	@Column(name = "ano")
    private Integer ano;

	@Column(name = "semestre")
	private Integer semestre;

	@Column(name = "trimestre")
    private Integer trimestre;
	
	//@OneToMany(mappedBy = "DimTempo")
	//private List<FatoContratacoes> fatoContratacoesItens;
}
