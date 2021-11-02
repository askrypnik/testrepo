package com.tr.client;

import com.tr.OptionsPricer;
import com.tr.service.Client;
import com.tr.service.PriceService;

public class ClientTest implements Client {

    PriceService service;
    ClientTest() {
        service = new PriceService();
        service.subscribe(this, OptionsPricer.Ticker_A);
        service.subscribe(this, OptionsPricer.Ticker_B);
    }

    @Override
    public void sendUpdate(String ticker, double price) {
        System.out.println("Received an update for " + ticker + ", price: " + price);
    }

    public static void main(String [] s) {
        System.out.println("Starting pricer client");
        new ClientTest();
    }

}
