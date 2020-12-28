package by.clooth.reading.controllers;

import by.clooth.reading.domain.constants.FilterParameters;
import by.clooth.reading.services.ReadSortingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/reading/")
@RequiredArgsConstructor
public class ProductsController {

    private final ReadSortingService readSortingService;

    @GetMapping("filter-parameters")
    public EnumMap<FilterParameters, Set<String>> getFilterParameters() throws IOException {
        return readSortingService.getFilterParametersWithOptions();
    }

    @PostMapping("getProducts")
    public List<Map<String, Object>> getProductsByParameters(@RequestBody EnumMap<FilterParameters, String> parameters) throws IOException {
        return readSortingService.getByParameters(parameters);
    }
}
