package by.clooth.reading.domain.constants;

import lombok.Getter;

@Getter
public final class QueryConstants {
    public static final String ROOT_ELEMENT = "$";
    public static final String CURRENT_NODE = "@";
    public static final String WILDCARD = "*";
    public static final String DEEP_SCAN = "..";
    public static final String ANY_ELEM_IN_ARRAY = "[?]";
    public static final String DOT = ".";
    public static final String EACH_ELEM_IN_ARRAY = "[*]";
}
