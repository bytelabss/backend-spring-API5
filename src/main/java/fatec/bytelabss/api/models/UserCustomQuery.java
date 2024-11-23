package fatec.bytelabss.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios_custom_query")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserCustomQuery {
	
	@Id
    @Column(name = "id")
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "usuario")
    private User idUsuario;

	@ManyToOne
	@JoinColumn(name = "custom_queries")
    private CustomQuery idCustomQuery;

}
