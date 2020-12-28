package by.clooth.reading.services;

import by.clooth.reading.domain.constants.FilterParameters;

import java.io.IOException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ReadSortingService {

    /**
     * @param parameters Мап с именем поля для фильтрация и значением, по которому фильтруется
     * @return отфильтрованные jsons
     * @throws IOException
     */
    List<Map<String, Object>> getByParameters(EnumMap<FilterParameters,String> parameters) throws IOException;

    /**
     * @return Параметры для фильтрации со всеми возможными значениями
     * @throws IOException
     */
    EnumMap<FilterParameters, Set<String>> getFilterParametersWithOptions() throws IOException;
}
