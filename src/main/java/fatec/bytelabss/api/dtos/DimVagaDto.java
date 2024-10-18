package fatec.bytelabss.api.dtos;

import java.time.LocalDateTime;

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
