package fatec.bytelabss.dataViz.sparkExample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/example")
public class SparkExampleController {
    SparkExampleService service = new SparkExampleService();

    @GetMapping
    public void RunConvertsXlsxToCsv() throws IOException {
        service.convertsXlsxToCsv();
    }
}
