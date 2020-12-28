package by.clooth.reading.services.Impl;

import by.clooth.reading.domain.constants.FilterParameters;
import by.clooth.reading.services.FilterService;
import by.clooth.reading.services.ReadSortingService;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReadSortingServiceImpl implements ReadSortingService {

    private final String filePath = "src/main/resources/data_example.json";
    private final FilterService filterService;

    private File getFile(String filePath) throws FileNotFoundException {
        return ResourceUtils.getFile(filePath);
    }

    private DocumentContext getDocumentContext(String filePath) throws IOException {
        return JsonPath.parse(getFile(filePath));
    }

    private List<String> getAllFieldOptions(String field) throws IOException {
        return getDocumentContext(filePath).read(filterService.allFieldOptionsQuery(field));
    }

    @Override
    public List<Map<String, Object>> getByParameters(EnumMap<FilterParameters,String> parameters) throws IOException {
        return getDocumentContext(filePath).read(
                filterService.allProductsQuery(),
                filterService.generateFilterByParameters(parameters)
        );
    }

    @Override
    public EnumMap<FilterParameters,Set<String>> getFilterParametersWithOptions() throws IOException {
        EnumMap<FilterParameters,Set<String>> parametersWithOptions = new EnumMap<>(FilterParameters.class);
        for(FilterParameters parameter:getFilterParameters()){
            parametersWithOptions.putAll(
                    collectParameterOptions(parameter,getAllFieldOptions(parameter.getFilterParameter()))
            );
        }
        return parametersWithOptions;
    }

    private EnumMap<FilterParameters,Set<String>> collectParameterOptions(FilterParameters parameter,List<String> options){
        EnumMap<FilterParameters,Set<String>> parameterOptions = new EnumMap<>(FilterParameters.class);
        parameterOptions.put(parameter,new HashSet<>(options));
        return parameterOptions;
    }

    private List<FilterParameters> getFilterParameters(){
        List<FilterParameters> filterParameters = new ArrayList<>();
        filterParameters.add(FilterParameters.CATEGORY);
        filterParameters.add(FilterParameters.COLOR);
        filterParameters.add(FilterParameters.TYPE);
        filterParameters.add(FilterParameters.GENDER);
        filterParameters.add(FilterParameters.MIN_PRICE);
        filterParameters.add(FilterParameters.MAX_PRICE);
        return filterParameters;
    }
}
