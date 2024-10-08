package fatec.bytelabss.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DIM_CANDIDATO")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class DimCandidato {

	@Id
    @Column(name = "id_candidato")
    private Long idCandidato;

    @Column(name = "nome")
    private String nome;
}
