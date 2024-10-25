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
@Table(name = "GRAFICOS")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Grafico {
	
	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "has_alarm")
	private Boolean hasAlarm;

	@Column(name = "ignore_alarm")
	private Boolean ignoreAlarm;

	@Column(name = "ignore_until")
	private LocalDateTime ignoreUntil;

	@Column(name = "max_limit")
	private Double maxLimit;
	
	@Column(name = "min_limit")
	private Double minLimit;

}
