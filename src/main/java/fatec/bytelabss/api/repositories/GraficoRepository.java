package fatec.bytelabss.api.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fatec.bytelabss.api.dtos.GraficoDtoInterface;
import fatec.bytelabss.api.models.Grafico;

public interface GraficoRepository extends JpaRepository<Grafico, Long>{

	 @Query(nativeQuery = true, value =  "SELECT id, has_alarm, ignore_until, max_limit, min_limit, name  "
	 										+ "FROM graficos "
	 										+ "WHERE has_alarm = 1 ")
	List<GraficoDtoInterface> RetornarGraficosComAlarmesAtivos(@Param("dataAtual") LocalDateTime dataAtual);


}
