package fatec.bytelabss.api.dtos;

import java.time.LocalDateTime;

import fatec.bytelabss.api.models.DimCandidato;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DimVagaDto {

	private Long id;
	
	private String nome;
	
	private LocalDateTime dataCriacao;
	
	private String requisitos;
	
	private String status;
	
	
}
