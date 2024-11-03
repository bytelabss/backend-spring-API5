package fatec.bytelabss.api.dtos;

import java.time.LocalDateTime;

public interface GraficoDtoInterface {
	
	Long getId();

	Boolean getHasAlarm();

	LocalDateTime getIgnoreUntil();

	Double getMaxLimit();
	
    Double getMinLimit();
    
    String getName();

}
