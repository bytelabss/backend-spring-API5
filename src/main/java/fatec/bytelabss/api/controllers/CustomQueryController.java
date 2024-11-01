package fatec.bytelabss.api.controllers;

import fatec.bytelabss.api.models.CustomQuery;
import fatec.bytelabss.api.services.CustomQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/custom-queries")
public class CustomQueryController {

    @Autowired
    private CustomQueryService customQueryService;

    @PostMapping
    public CustomQuery saveQuery(@RequestBody CustomQuery customQuery) {
        CustomQuery savedQuery = customQueryService.saveCustomQuery(customQuery);
        return savedQuery;
    }

    @GetMapping
    public List<CustomQuery> getAllQueries() {
        List<CustomQuery> queries = customQueryService.getAllQueries();
        return queries;
    }

    @GetMapping("/{id}")
    public CustomQuery getQueryById(@PathVariable Long id) {
        CustomQuery query = customQueryService.getQueryById(id);
        return query;
    }

    @PutMapping("/{id}")
    public CustomQuery updateQuery(@PathVariable Long id, @RequestBody CustomQuery updatedQuery) {
        updatedQuery.setId(id);
        CustomQuery savedQuery = customQueryService.saveCustomQuery(updatedQuery);
        return savedQuery;
    }

    @DeleteMapping("/{id}")
    public Void deleteQuery(@PathVariable Long id) {
        customQueryService.deleteCustomQuery(id);
        return null;
    }

    @GetMapping("/{id}/results")
    public List<Map<String,Object>> executeQuery(@PathVariable Long id) {
        CustomQuery customQuery = customQueryService.getQueryById(id);
        try {
            List<Map<String,Object>> results = customQueryService.executeCustomQuery(customQuery);
            return results;
        } catch (Exception e) {
            return null;
        }
    }
}
