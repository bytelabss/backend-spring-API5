package fatec.bytelabss.api.sparkSession;

import org.apache.spark.sql.SparkSession;

public class SparkSessionSingleton {

	private static transient SparkSession instance = null;

    public static SparkSession getInstance() {
        if (instance == null) {
            instance = SparkSession
                    .builder()
                    .appName("DataViz")
                    .config("spark.master", "local")
                    .getOrCreate();
        }
        return instance;
    }
}
