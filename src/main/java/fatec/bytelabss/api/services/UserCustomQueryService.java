package fatec.bytelabss.api.services;

import org.apache.hadoop.yarn.webapp.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.api.dtos.UserCustomQueryDto;
import fatec.bytelabss.api.dtos.UserCustomQueryForm;
import fatec.bytelabss.api.models.CustomQuery;
import fatec.bytelabss.api.models.UserCustomQuery;
import fatec.bytelabss.api.repositories.CustomQueryRepository;
import fatec.bytelabss.api.repositories.UserCustomQueryRepository;
import fatec.bytelabss.api.repositories.UserRepository;

@Service
public class UserCustomQueryService {

	@Autowired(required = true)
	private UserCustomQueryRepository repository;
	
	@Autowired(required = true)
	private UserRepository user;
	
	@Autowired(required = true)
	private CustomQueryRepository customQuery;
	
	public UserCustomQueryDto saveUserCustomQuery(UserCustomQueryForm form) {
		
		var usuario = user.findById(form.getIdUsuario());
		
		if(usuario == null) {
			throw new NotFoundException("Usuário não encontrado");
		}
		
		var query = customQuery.findById(form.getIdCustomQuery());
		
		if(query == null) {
			throw new NotFoundException("Consulta não encontrada");
		}
			
		var entidade = new UserCustomQuery();
		
		entidade.setIdCustomQuery(query.get());
		
		entidade.setIdUsuario(usuario.get());
		
		var entidadeSalva = repository.save(entidade);
		
        return converteParaDto(entidadeSalva);
    }
	
	public void delete(long id) {
		repository.deleteById(id);
	}
	
	
	private UserCustomQueryDto converteParaDto(UserCustomQuery entidade) {
		var dto = new UserCustomQueryDto();
		
		dto.setId(entidade.getId());
		dto.setIdCustomQuery(entidade.getIdCustomQuery().getId());
		dto.setIdUsuario(entidade.getIdUsuario().getId());
		
		return dto;
	}
	
}
