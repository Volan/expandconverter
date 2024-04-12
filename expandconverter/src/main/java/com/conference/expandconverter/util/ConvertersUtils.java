package com.conference.expandconverter.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.BiPredicate;

public class ConvertersUtils {

    public static final BiPredicate<Method, String> GETTER_FILTER = (method, fieldName) ->
            ((method.getName().startsWith("get") && method.getName().length() == fieldName.length() + 3)
                    ||(method.getName().startsWith("is") && method.getName().length() == fieldName.length() + 2))
                    && method.getName().toLowerCase().endsWith(fieldName.toLowerCase())
                    && method.getParameterCount() == 0;

    public static final BiPredicate<Method, String> SETTER_FILTER = (method, fieldName) ->
            method.getName().startsWith("set") && method.getName().length() == fieldName.length() + 3
                    && method.getName().toLowerCase().endsWith(fieldName.toLowerCase())
                    && method.getParameterCount() == 1;

    /**
     * Вызвать getter на методе
     *
     * @param fieldName имя свойства
     * @param object объект, у которого будет вызван getter
     * @throws IllegalStateException ошибка при вызове getter
     * @return значение поля fieldName в object
     */
    public static Object runGetter(String fieldName, Object object) {
        try {
            Method method = Arrays.stream(object.getClass().getDeclaredMethods())
                    .filter(testElem -> GETTER_FILTER.test(testElem, fieldName))
                    .findFirst()
                    .get();
            return method.invoke(object);
        } catch (Exception e) {
            throw new IllegalStateException(String.format("При получения поля произошла ошибка доступа к методу %s",
                    fieldName), e.getCause());
        }
    }

    /**
     * Вызвать setter
     *
     * @param fieldName имя свойства
     * @param object объект, у которого будет вызван setter
     * @param value  значение, которое будет передано в setter
     * @throws IllegalStateException ошибка при вызове setter
     */
    public static void runSetter(String fieldName, Object object, Object value) {
        try {
            Method method = Arrays.stream(object.getClass().getDeclaredMethods())
                    .filter(testElem -> SETTER_FILTER.test(testElem, fieldName))
                    .findFirst()
                    .get();
            method.invoke(object, value);
        } catch (Exception e) {
            throw new IllegalStateException(String.format("При присвоении поля произошла ошибка доступа к сеттеру %s",
                    fieldName), e.getCause());
        }
    }

    /**
     * Вызвать getter на методе
     *
     * @param method метод, который вызваем
     * @param object объект, у которого будет вызван getter
     * @throws IllegalStateException ошибка при вызове getter
     * @return значение поля fieldName в object
     */
    public static Object runGetter(Method method, Object object) {
        try {
            return method.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(String.format("При получения поля произошла ошибка доступа к методу %s",
                    method.getName()), e.getCause());
        }
    }

    /**
     * Вызвать setter
     *
     * @param method метод, который вызваем
     * @param object объект, у которого будет вызван setter
     * @param value  значение, которое будет передано в setter
     * @throws IllegalStateException ошибка при вызове setter
     */
    public static void runSetter(Method method, Object object, Object value) {
        try {
            method.invoke(object, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(String.format("При присвоении поля произошла ошибка доступа к сеттеру %s",
                    method.getName()), e.getCause());
        }
    }

}