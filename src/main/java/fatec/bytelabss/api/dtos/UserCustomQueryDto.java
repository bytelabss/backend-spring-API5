package fatec.bytelabss.api.dtos;

import fatec.bytelabss.api.models.CustomQuery;
import fatec.bytelabss.api.models.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class UserCustomQueryDto {

	
	private long id;
	
	private long idUsuario;

    private long idCustomQuery;

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    
	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public long getIdCustomQuery() {
		return idCustomQuery;
	}

	public void setIdCustomQuery(long idCustomQuery) {
		this.idCustomQuery = idCustomQuery;
	}
    
    
}
