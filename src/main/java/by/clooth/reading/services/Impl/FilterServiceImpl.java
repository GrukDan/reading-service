package by.clooth.reading.services.Impl;

import by.clooth.reading.domain.constants.FieldNames;
import by.clooth.reading.domain.constants.FilterParameters;
import by.clooth.reading.services.FilterService;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.Predicate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import static by.clooth.reading.domain.constants.FieldNames.PRODUCTS_NAME;
import static by.clooth.reading.domain.constants.FieldNames.ROOT_NAME;
import static by.clooth.reading.domain.constants.QueryConstants.*;
import static com.jayway.jsonpath.Criteria.where;

@Service
public class FilterServiceImpl implements FilterService {

    @Override
    public Filter generateFilterByParameters(EnumMap<FilterParameters, String> parameters) {
        return Filter.filter(generatePredicatesByParameters(parameters));
    }

    private List<Predicate> generatePredicatesByParameters(EnumMap<FilterParameters, String> parameters) {
        List<Predicate> predicates = generatePredicatesForPrices(parameters);
        predicates.addAll(parameters.entrySet()
                .stream()
                .map(entry -> where(entry.getKey().getFilterParameter()).is(entry.getValue()))
                .collect(Collectors.toList()));
        return predicates;
    }

    private List<Predicate> generatePredicatesForPrices(EnumMap<FilterParameters, String> parameters) {
        List<Predicate> predicates = new ArrayList<>();
        if (parameters.containsKey(FilterParameters.MIN_PRICE)) {
            predicates.add(where(FieldNames.ProductFieldsName.PRICE.getFiledName()).gt(parameters.get(FilterParameters.MIN_PRICE)));
            parameters.remove(FilterParameters.MIN_PRICE);
        }
        if (parameters.containsKey(FilterParameters.MAX_PRICE)) {
            predicates.add(where(FieldNames.ProductFieldsName.PRICE.getFiledName()).lt(parameters.get(FilterParameters.MAX_PRICE)));
            parameters.remove(FilterParameters.MAX_PRICE);
        }
        return predicates;
    }

    @Override
    public String allProductsQuery() {
        return new StringBuilder()
                .append(ROOT_ELEMENT)
                .append(DOT)
                .append(ROOT_NAME)
                .append(DOT)
                .append(PRODUCTS_NAME)
                .append(ANY_ELEM_IN_ARRAY)
                .toString();
    }

    @Override
    public String allFieldOptionsQuery(String field) {
        return new StringBuilder()
                .append(ROOT_ELEMENT)
                .append(DOT)
                .append(ROOT_NAME)
                .append(DOT)
                .append(PRODUCTS_NAME)
                .append(EACH_ELEM_IN_ARRAY)
                .append(DOT)
                .append(field)
                .toString();
    }
}
