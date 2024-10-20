package fatec.bytelabss.api.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PDF_REPORT_LOGS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PdfReportLogs {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "report_date", nullable = false)
    private LocalDateTime reportDate;

    @Column(name = "generated_at", nullable = false)
    private LocalDateTime generatedAt;

    // public PdfReportLog(LocalDateTime reportDate) {
    //     this.reportDate = reportDate;
    //     this.generatedAt = LocalDateTime.now();
    // }

}
