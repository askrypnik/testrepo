package com.tr.service;

public interface MarketDataService {

    void subscibe(String ticker, MarketDataListener listener);

}
