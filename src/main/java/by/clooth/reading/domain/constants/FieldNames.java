package by.clooth.reading.domain.constants;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class FieldNames {

    public static final String ROOT_NAME = "store";
    public static final String PRODUCTS_NAME = "products";

    @Getter
    @RequiredArgsConstructor
    public enum ProductFieldsName {

        NAME("name"),
        PRICE("price"),
        CURRENCY("currency"),
        COLOR("color"),
        ARTICLE("article"),
        DESCRIPTION("description"),
        IMAGE("image"),
        PRODUCT("product"),
        TYPE("type"),
        GENDER("gender"),
        CATEGORY("category"),
        BRAND("brand");

        private final String filedName;

        public List<String> getFieldNames() {
            return Arrays
                    .stream(ProductFieldsName.values())
                    .map(ProductFieldsName::getFiledName)
                    .collect(Collectors.toList());
        }
    }
}
