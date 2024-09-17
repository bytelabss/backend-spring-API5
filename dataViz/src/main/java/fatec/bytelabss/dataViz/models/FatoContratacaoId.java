package fatec.bytelabss.dataViz.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@Data
@NoArgsConstructor
@Embeddable
public class FatoContratacaoId implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1376506488121924900L;


	private Long idProcessoSeletivo;


    private Long idTempo;
}
