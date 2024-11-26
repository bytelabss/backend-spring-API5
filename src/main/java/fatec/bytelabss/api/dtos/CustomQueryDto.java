package fatec.bytelabss.api.dtos;

import java.time.LocalDateTime;

import fatec.bytelabss.api.models.CustomQuery;
import fatec.bytelabss.api.models.CustomQuerySQL;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CustomQueryDto {
	
	private Long id;

	private CustomQuerySQL query;

	private String description;

	private LocalDateTime createdAt;
}
