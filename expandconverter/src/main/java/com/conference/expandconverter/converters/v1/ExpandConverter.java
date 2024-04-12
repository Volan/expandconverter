package com.conference.expandconverter.converters.v1;

import java.util.Set;

public interface ExpandConverter<S, T> {

    String SEPARATOR = ".";

    static String generatePath(String... entities) {
        return String.join(SEPARATOR, entities);
    }

    /**
     * Метод для перекладывания полей сущности S в сущность T
     * @param source исходная сущность
     * @param expandNames имена для раскрытия через SEPARATOR
     * @return целевая сущность
     */
    T convert(S source, Set<String> expandNames);

}
