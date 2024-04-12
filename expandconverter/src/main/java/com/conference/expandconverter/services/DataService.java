package com.conference.expandconverter.services;

public interface DataService {

    void generateData(int persons,
                      int items,
                      int orders,
                      int order_positions,
                      int category,
                      int deliveryType,
                      int country,
                      int producer);

}
