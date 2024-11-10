package fatec.bytelabss.api.models;

import lombok.Data;

import java.util.List;

@Data
public class CustomQuerySQL {
    @Data
    public static class Condition {
        private String field;
        private String operator;
        private Object value;
    }

    private List<String> fields;
    private String from;
    private List<Condition> conditions;
    private int limit;
    private List<String> groupBy;
    private String orderByField;
    private String orderByDirection;
}
