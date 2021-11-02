package com.tr.service;

import com.tr.OptionsPricerException;
import com.tr.Price;

import java.util.Map;
import java.util.HashMap;

abstract class Service {

    protected Map<String, Map<Client, Price>> subscriptions = new HashMap<>();

    /*
    Synchronized?
     */
    void subscribe(Client client, String ticker) {
        if(client == null) {
            throw new OptionsPricerException("Client must be supplied");
        }
        if(ticker == null || ticker.isEmpty()) {
            throw new OptionsPricerException("Ticker must not be empty");
        }
        subscriptions.putIfAbsent(ticker, new HashMap<>());
        subscriptions.get(ticker).putIfAbsent(client, new Price(0., null));
    }

}
