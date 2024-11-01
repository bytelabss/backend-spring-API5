package fatec.bytelabss.api.services;

import fatec.bytelabss.api.models.CustomQuery;
import fatec.bytelabss.api.models.CustomQuerySQL;
import fatec.bytelabss.api.repositories.CustomQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;
import jakarta.persistence.criteria.Expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomQueryService {

    @Autowired
    private CustomQueryRepository repository;

    @Autowired
    private EntityManager entityManager;

    public CustomQuery saveCustomQuery(CustomQuery customQuery) {
        return repository.save(customQuery);
    }

    public List<CustomQuery> getAllQueries() {
        return repository.findAll();
    }

    public CustomQuery getQueryById(Long id) {
        return repository.findById(id).get();
    }

    public void deleteCustomQuery(Long id) {
        repository.deleteById(id);
    }

    public List<Map<String, Object>> executeCustomQuery(CustomQuery customQuery) throws ClassNotFoundException {
        CustomQuerySQL query = customQuery.getQuery();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        Class<?> entityClass = Class.forName(query.getFrom());
        Root<?> root = criteriaQuery.from(entityClass);

        // Select fields
        List<Selection<?>> selections = new ArrayList<>();
        for (String field : query.getFields()) {
            selections.add(root.get(field));
        }
        criteriaQuery.multiselect(selections);

        // Where conditions
        List<Predicate> predicates = new ArrayList<>();
        for (CustomQuerySQL.Condition condition : query.getConditions()) {
            predicates.add(createPredicate(criteriaBuilder, root, condition));
        }
        if (!predicates.isEmpty()) {
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }

        // Group by
        List<Expression<?>> groupBy = new ArrayList<>();
        for (String groupByField : query.getGroupBy()) {
            groupBy.add(root.get(groupByField));
        }
        criteriaQuery.groupBy(groupBy);

        // Order by
        if (query.getOrderByField() != null) {
            if (query.getOrderByDirection().equals("ASC")) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(query.getOrderByField())));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(query.getOrderByField())));
            }
        }

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);

        // Limit
        if (query.getLimit() > 0) {
            typedQuery.setMaxResults(query.getLimit());
        }

        // JSON
        List<Object[]> results = typedQuery.getResultList();
        List<Map<String, Object>> formattedResults = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> resultMap = new HashMap<>();
            for (int i = 0; i < query.getFields().size(); i++) {
                resultMap.put(query.getFields().get(i), row[i]);
            }
            formattedResults.add(resultMap);
        }

        return formattedResults;
    }

    private Predicate createPredicate(CriteriaBuilder cb, Root<?> root, CustomQuerySQL.Condition condition) {
        String operator = condition.getOperator();
        String field = condition.getField();
        Object value = condition.getValue();

        switch (operator) {
            case "=":
                return cb.equal(root.get(field), value);
            case "<":
                return cb.lessThan(root.get(field), (Comparable) value);
            case "<=":
                return cb.lessThanOrEqualTo(root.get(field), (Comparable) value);
            case ">":
                return cb.greaterThan(root.get(field), (Comparable) value);
            case ">=":
                return cb.greaterThanOrEqualTo(root.get(field), (Comparable) value);
            case "LIKE":
                return cb.like(root.get(field), "%" + value + "%");
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }
}
