package fatec.bytelabss.api.models;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DIM_PROCESSO_SELETIVO")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class DimProcessoSeletivo {

	@Id
	@Column(name = "id_Processo_Seletivo")
    private Long idProcessoSeletivo;

	@Column(name = "nome")
    private String nome;

	@Column(name = "status")
    private String status;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "criado_por")
    private String criadoPor;

	@Column(name = "inicio_processo_seletivo")
    private LocalDateTime inicioProcessoSeletivo;

	@Column(name = "fim_processo_seletivo")
    private LocalDateTime fimProcessoSeletivo;


}
