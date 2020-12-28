package by.clooth.reading.domain.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FilterParameters {
    CATEGORY("category"),
    COLOR("color"),
    TYPE("type"),
    GENDER("gender"),
    MIN_PRICE("min_price"),
    MAX_PRICE("max_price");

    private final String filterParameter;
}
