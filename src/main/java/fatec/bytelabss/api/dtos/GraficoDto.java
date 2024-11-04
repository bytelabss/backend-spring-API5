package fatec.bytelabss.api.dtos;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GraficoDto {

	private Long id;

	private Boolean hasAlarm;

	private String ignoreUntil;

	private Double maxLimit;
	
	private Double minLimit;
	
	private String name;
}
