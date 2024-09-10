package fatec.bytelabss.dataViz.sparkExample;

import fatec.bytelabss.dataViz.sparkExample.sparkSession.SparkSessionSingleton;
import fatec.bytelabss.dataViz.sparkExample.utils.SheetNameExtractor;
import fatec.bytelabss.dataViz.sparkExample.utils.StringFormatter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.io.IOException;
import java.util.LinkedList;

@NoArgsConstructor
@Data
public class SparkExampleService {
    private String inputFile = "/home/larissa/Documents/exemplo_processo_seletivo.xlsx";
    private String outputFileDir = "/home/larissa/Documents/output/";
    private SparkSession spark = SparkSessionSingleton.getInstance();
    private StringFormatter stringFormatter = StringFormatter.getInstance();

    public SparkExampleService(String inputFile, String outputFileDir) {
        this.inputFile = inputFile;
        this.outputFileDir = outputFileDir;
    }

    public LinkedList<String> getSheetNames() throws IOException {
        SheetNameExtractor sheetNameExtractor = new SheetNameExtractor(inputFile);
        return sheetNameExtractor.getSheetNames();
    }

    public void convertsXlsxToCsv() throws IOException {
        LinkedList<String> sheetNames = this.getSheetNames();

        for (String name : sheetNames) {
            Dataset<Row> df = spark.read()
                    .format("com.crealytics.spark.excel")
                    .option("header", "true")
                    .option("inferSchema", "true")
                    .option("dataAddress", "'" + name + "'!A1")
                    .load(this.inputFile);

            System.out.println("\nColumns on sheet: " + name);
            for (Tuple2<String, String> column : df.dtypes()) {
                System.out.println("Column: " + column._1 + ", Type: " + column._2);
            }

            String normalizedName = stringFormatter.toSnakeCase(name);

            df.coalesce(1).write()
                    .format("csv")
                    .option("header", "true")
                    .mode("overwrite")
                    .save(this.outputFileDir + normalizedName + ".csv");
        }

        spark.stop();
    }

}
