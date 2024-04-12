package com.conference.expandconverter.services;

import com.conference.expandconverter.model.*;
import com.conference.expandconverter.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {

    private final String PERSON = "person";

    private final String ADDRESS = "address";

    private final String ITEM = "item";

    private final String CATEGORY = "category";

    private final String DELIVERY_TYPE = "deliveryType";

    private final String COUNTRY = "country";

    private final String PRODUCER = "producer";

    private final PersonRepository personRepository;

    private final AddressRepository addressRepository;

    private final ItemRepository itemRepository;

    private final OrderRepository orderRepository;

    private final OrderPositionRepository orderPositionRepository;

    private final CategoryRepository categoryRepository;

    private final DeliveryTypeRepository deliveryTypeRepository;

    private final CountryRepository countryRepository;

    private final ProducerRepository producerRepository;

    @Override
    public void generateData(int persons,
                             int items,
                             int orders,
                             int order_positions,
                             int category,
                             int deliveryType,
                             int country,
                             int producer) {
        ArrayList<Address> addressesArray = new ArrayList<>(persons);
        for (int i = 0; i< persons; ++i) {
            addressesArray.add(addressRepository.save(new Address(ADDRESS + i)));
        }
        ArrayList<Person> personsArray = new ArrayList<>(persons);
        for (int i = 0; i< persons; ++i) {
            personsArray.add(personRepository.save(new Person(PERSON + i, PERSON + i, addressesArray.get(i))));
        }
        ArrayList<Category> categoryArray = new ArrayList<>(category);
        for (int i = 0; i< category; ++i) {
            categoryArray.add(categoryRepository.save(new Category(CATEGORY + i)));
        }
        ArrayList<Country> countryArray = new ArrayList<>(country);
        for (int i = 0; i< country; ++i) {
            countryArray.add(countryRepository.save(new Country(COUNTRY + i)));
        }
        ArrayList<Producer> producerArray = new ArrayList<>(producer);
        for (int i = 0; i< country; ++i) {
            producerArray.add(producerRepository.save(new Producer(PRODUCER + i, countryArray.get(getRandom(country)))));
        }
        ArrayList<Item> itemsArray = new ArrayList<>(items);
        for (int i = 0; i< items; ++i) {
            itemsArray.add(itemRepository.save(new Item(ITEM + i, categoryArray.get(getRandom(category)), producerArray.get(getRandom(producer)))));
        }
        ArrayList<DeliveryType> deliveryTypeArray = new ArrayList<>(deliveryType);
        for (int i = 0; i< category; ++i) {
            deliveryTypeArray.add(deliveryTypeRepository.save(new DeliveryType(DELIVERY_TYPE + i)));
        }
        ArrayList<Order> ordersArray = new ArrayList<>(orders);
        for (int i = 0; i< orders; ++i) {
            ordersArray.add(orderRepository.save(new Order(personsArray.get(getRandom(persons)), deliveryTypeArray.get(getRandom(deliveryType)))));
            for (int j = 0; j < order_positions; ++j) {
                orderPositionRepository.save(new OrderPosition(itemsArray.get(getRandom(items)), ordersArray.get(i).getId(), Long.valueOf(getRandom(100))));
            }
        }

    }

    private int getRandom(int max) {
        return (int)(Math.random() * max);
    }

}
