package fatec.bytelabss.dataViz.sparkExample.utils;

import java.text.Normalizer;

public class StringFormatter {
    private static transient StringFormatter instance = null;

    private StringFormatter() {}

    public static StringFormatter getInstance() {
        if (instance == null) {
            instance = new StringFormatter();
        }
        return instance;
    }

    public String toSnakeCase(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");

        return normalized
                .toLowerCase()
                .replaceAll("[\\s\\-]+", "_")
                .replaceAll("[^a-z0-9_]", "");
    }

}
