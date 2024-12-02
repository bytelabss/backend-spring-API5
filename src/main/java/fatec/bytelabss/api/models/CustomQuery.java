package fatec.bytelabss.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "custom_queries")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CustomQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "query", columnDefinition = "TEXT")
    @Convert(converter = CustomQuerySQLConverter.class)
    private CustomQuerySQL query;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;

    @Column(name = "visualization_model")
    private String visualizationModel;
}
