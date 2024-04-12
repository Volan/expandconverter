package com.conference.expandconverter.converters.v1;

import com.conference.expandconverter.util.ConvertersUtils;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class AbstractExpandConverter<S, T> implements ExpandConverter<S,T> {

    public static final String SPLITTER = "\\" + SEPARATOR;

    /**
     * карта:  имя вложенного поля(совпадает у домена и dto) -> бин конвертер для этого поля
     */
    private Map<String, AbstractExpandConverter<Object, Object>> expandConverterMap = Collections.emptyMap();

    public void setExpandConverterMap(Map expandConverterMap) {
        this.expandConverterMap = expandConverterMap;
    }

    @Override
    public T convert(S source, Set<String> expandNames) {
        T dto = convert(source);  // создать дто и определить плоские поля
        if (Objects.isNull(expandNames) || expandNames.isEmpty()) {
            return dto; // если нет полей для раскрытия отдать плоскую сущность
        }
        // формируем карту для полей
        Map<String, Set<String>> nestedMap = new HashMap<>();
        for (String expand : expandNames) {
            String[] keyAndSubPath = getFirstPathAndSubPath(expand);
            String value = keyAndSubPath.length > 1 ? keyAndSubPath[1] : "";
            nestedMap.compute(keyAndSubPath[0], (key, oldValues) -> {
                if (Objects.isNull(oldValues)) {
                    Set<String> values = new HashSet<>();
                    values.add(value);
                    return values;
                } else {
                    oldValues.add(value);
                    return oldValues;
                }
            });
        }
        // раскрываем вложенные сущности
        for (Map.Entry<String, Set<String>> pair : nestedMap.entrySet()) {
            String currentFieldName = pair.getKey();
            // проверяем настроен ли конвертер на раскрытие вложенного поля
            if (!expandConverterMap.containsKey(currentFieldName)) {
                continue;
            }
            // Получаем значение поля по названию
            var value = Optional.ofNullable(ConvertersUtils.runGetter(currentFieldName, source));
            if (value.isPresent()) {
                var converter = getConverter(currentFieldName); //конвертер для значения
                var convertedDto = getConvertedDto(value.get(), converter, pair.getValue()); // результат конвертирования
                ConvertersUtils.runSetter(currentFieldName, dto, convertedDto); // задать значение в текущем dto
            }
        }
        return dto;
    }


    /**
     * Переложить невложенные поля
     *
     * @param source источник данных
     * @return dto
     */
    protected abstract T convert(S source);

    /**
     * Получить разбиение на компоненты
     * вернет первый элемент и остаток пути
     */
    private static String[] getFirstPathAndSubPath(String path) {
        return path.split(SPLITTER, 2);
    }

    /**
     * забрать конвертер по имени поля из предоставленной мапы конвертеров
     *
     * @param fieldName имя поля
     * @return конвертер
     */
    private ExpandConverter<Object, Object> getConverter(String fieldName) {
        var converter = expandConverterMap.get(fieldName);
        if (Objects.isNull(converter)) {
            throw new IllegalStateException(String.format("Not found expand converter for field %s", fieldName));
        }
        return converter;
    }

    /**
     * получить преобразованное конвертером значение, это может быть и коллекция
     * При конвертации hibernate сущностей придут PersistentList, PersistentSet
     *
     * @param source    объект для конвертирования
     * @param converter конвертер
     * @param expands      оставшиеся пути для раскрытия, будет использоваться конверторами элементов коллекции для дальнейшего
     *                  раскрытия
     * @return сконвертированный объект
     */
    private Object getConvertedDto(Object source, ExpandConverter<Object, Object> converter, Set<String> expands) {
        if (source instanceof List list) {
            return convertCollectionExpand(list, converter, Collectors.toList(), expands);
        } else if (source instanceof Set set) {
            return convertCollectionExpand(set, converter, Collectors.toSet(), expands);
        } else {
            return converter.convert(source, expands);
        }
    }

    private static <E, T extends Collection<E>, D, R extends Collection<D>> R convertCollectionExpand(T collection, ExpandConverter<E, D> converter, Collector<D, ?, R> collector, Set<String> expands) {
        return Objects.isNull(collection) ? null : collection.stream()
                .map(c -> converter.convert(c, expands))
                .collect(collector);
    }

}
