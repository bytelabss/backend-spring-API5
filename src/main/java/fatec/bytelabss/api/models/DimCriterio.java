package fatec.bytelabss.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DIM_CRITERIO")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class DimCriterio {

	@Id
    @Column(name = "id_criterio")
    private Long idCriterio;

    @Column(name = "nome")
    private String nomeCriterio;
}
