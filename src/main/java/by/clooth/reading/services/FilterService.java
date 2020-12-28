package by.clooth.reading.services;

import by.clooth.reading.domain.constants.FilterParameters;
import com.jayway.jsonpath.Filter;

import java.util.EnumMap;

public interface FilterService {

    /**
     * @param parameters Мап с именем поля для фильтрация и значением, по которому фильтруется
     * @return Фильтр с предикатами по всем переданным для фильтрации параметрам
     */
    Filter generateFilterByParameters(EnumMap<FilterParameters,String> parameters);

    /**
     * @return Запрос в jayway на получение всех товаров
     */
    String allProductsQuery();

    /**
     * @param field имя поля
     * @return Запрос на получение всех значений переданного поля
     */
    String allFieldOptionsQuery(String field);
}
