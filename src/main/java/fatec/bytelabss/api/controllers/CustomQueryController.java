package fatec.bytelabss.api.controllers;

import fatec.bytelabss.api.dtos.CustomQueryDto;
import fatec.bytelabss.api.models.CustomQuery;
import fatec.bytelabss.api.services.CustomQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/custom-queries")
@CrossOrigin(origins="*")
public class CustomQueryController {

    private static final Logger logger = LoggerFactory.getLogger(CustomQueryController.class);

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
    
    @GetMapping("user/{userId}")
    public List<CustomQueryDto> getAllQueriesByUserId(@PathVariable Long userId) {
        List<CustomQueryDto> queries = customQueryService.getAllQueriesByUserId(userId);
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
            logger.error(e.getMessage());
            return null;
        }
    }
}
