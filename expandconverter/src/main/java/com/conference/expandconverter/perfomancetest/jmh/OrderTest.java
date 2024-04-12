package com.conference.expandconverter.perfomancetest.jmh;

import com.conference.expandconverter.converters.v1.*;
import com.conference.expandconverter.converters.v2.*;
import com.conference.expandconverter.model.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@BenchmarkMode(Mode.All)
@Fork(value = 2, warmups = 1)
public class OrderTest {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        final Set<String> EXPANDS = Set.of("person.address", "positions.item.category",
                "positions.item.producer.country", "deliveryType");

        final List<Order> orders = generateData();

        final OrderConverter orderConverter = v1Converter();

        final OrderConverterBetter orderConverterBetter = v2Converter();

    }

    private static OrderConverter v1Converter() {
        AddressConverter addressConverter = new AddressConverter();
        PersonConverter personConverter = new PersonConverter(addressConverter);
        personConverter.init();
        CategoryConverter categoryConverter = new CategoryConverter();
        CountryConverter countryConverter = new CountryConverter();
        ProducerConverter producerConverter = new ProducerConverter(countryConverter);
        producerConverter.init();
        ItemConverter itemConverter = new ItemConverter(categoryConverter, producerConverter);
        itemConverter.init();
        OrderPositionConverter orderPositionConverter = new OrderPositionConverter(itemConverter);
        orderPositionConverter.init();
        DeliveryTypeConverter deliveryTypeConverter = new DeliveryTypeConverter();
        OrderConverter orderConverter = new OrderConverter(personConverter, orderPositionConverter, deliveryTypeConverter);
        orderConverter.init();
        return orderConverter;
    }

    private static OrderConverterBetter v2Converter() {
        AddressConverterBetter addressConverter = new AddressConverterBetter();
        PersonConverterBetter personConverter = new PersonConverterBetter(addressConverter);
        personConverter.init();
        CategoryConverterBetter categoryConverter = new CategoryConverterBetter();
        CountryConverterBetter countryConverter = new CountryConverterBetter();
        ProducerConverterBetter producerConverterBetter = new ProducerConverterBetter(countryConverter);
        producerConverterBetter.init();
        ItemConverterBetter itemConverter = new ItemConverterBetter(categoryConverter, producerConverterBetter);
        itemConverter.init();
        OrderPositionConverterBetter orderPositionConverter = new OrderPositionConverterBetter(itemConverter);
        orderPositionConverter.init();
        DeliveryTypeConverterBetter deliveryTypeConverter = new DeliveryTypeConverterBetter();
        OrderConverterBetter orderConverter = new OrderConverterBetter(personConverter, orderPositionConverter, deliveryTypeConverter);
        orderConverter.init();
        return orderConverter;
    }

    private static List<Order> generateData() {
        int N = 3000;
        List<Order> orders = new ArrayList<>(N);
        Order order = generateOrder();
        for (int i = 0; i < N; ++i) {
            orders.add(order);
        }
        return orders;
    }

    private static Order generateOrder() {
        Category category = new Category(1L, "category");
        Country country = new Country(1L, "country");
        Producer producer = new Producer(1L, "producer", country);
        Item item = new Item(1L, "item", category, producer);
        OrderPosition position = new OrderPosition(1L, item, null, 1L);
        Address address = new Address(1l, "address");
        Person person = new Person(1L, "f", "l", address);
        DeliveryType deliveryType = new DeliveryType(1L, "deliveryType");
        Order order = new Order(1l, person, List.of(position, position, position, position), deliveryType);
        return order;
    }

    @Benchmark
    public void testV1(BenchmarkState state) {
        state.orders.stream()
                .map(order -> state.orderConverter.convert(order, state.EXPANDS)).toList();
    }

    @Benchmark
    public void testV2(BenchmarkState state) {
        state.orders.stream()
                .map(order -> state.orderConverterBetter.convert(order, state.EXPANDS)).toList();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(OrderTest.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

}
