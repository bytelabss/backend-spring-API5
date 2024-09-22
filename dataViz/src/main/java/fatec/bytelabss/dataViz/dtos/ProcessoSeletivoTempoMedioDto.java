package fatec.bytelabss.dataViz.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public interface ProcessoSeletivoTempoMedioDto {
	
	Long getProcesso_seletivo();
	
	String  getNome();
	
	Long getTempo_medio();
	
}
