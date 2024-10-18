package fatec.bytelabss.api.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fatec.bytelabss.api.models.PdfReportLogs;

public interface PdfReportLogRepository extends JpaRepository<PdfReportLogs, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM pdf_report_logs p WHERE p.report_date >= :weekStart")
    Optional<PdfReportLogs> findReportByWeekStart(LocalDate weekStart);

}
