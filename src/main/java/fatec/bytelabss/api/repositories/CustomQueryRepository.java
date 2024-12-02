package fatec.bytelabss.api.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.api.dtos.CustomQueryDto;
import fatec.bytelabss.api.dtos.VagaTempoMedioDto;
import fatec.bytelabss.api.models.CustomQuery;

@Repository
public interface CustomQueryRepository extends JpaRepository<CustomQuery, Long> {
	
	@Query(nativeQuery = true, value =  "select distinct a.id, a.query, a.description,a.created_at, c.name from custom_queries a " 
			+ "      inner join usuarios_custom_query b on a.id = b.custom_query "
			+ "      inner join usuarios c on c.id = b.usuario "
			+ "      where b.usuario = :userId ")
	List<Object[]> getAllQueriesByUserId(@Param("userId") Long userId);
}
