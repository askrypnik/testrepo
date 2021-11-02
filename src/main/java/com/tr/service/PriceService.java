package com.tr.service;

import com.tr.OptionsPricer;
import com.tr.Price;
import com.tr.Context;

import java.util.HashMap;
import java.util.Map;

public class PriceService extends Service implements MarketDataListener {

    private double tolerance = 1e-7;

    private Map<String, Map<Client, Price>> cache = new HashMap<>();

    public PriceService() { }

    @Override
    public void subscribe(Client client, String ticker)  {
        super.subscribe(client, ticker);
        new MarketDataService() {

            @Override
            public void subscibe(String ticker, MarketDataListener listener) {
                Context context = new Context(ticker);
                context.setRd(0.012);
                context.setRf(0.013);
                context.setVolatility(0.2);
                context.setSpotPrice(new Price(101.0, context));
                listener.onMarketdataUpdate(context);
                try {
                    Thread.currentThread().wait(1000);
                } catch (Exception e) {}
                context.setSpotPrice(new Price(102.0, context));
                listener.onMarketdataUpdate(context);
                try {
                    Thread.currentThread().wait(1000);
                } catch (Exception e) {}
                context.setSpotPrice(new Price(102.0, context));
                listener.onMarketdataUpdate(context);
                try {
                    Thread.currentThread().wait(1000);
                } catch (Exception e) {}
                context.setSpotPrice(new Price(101.0, context));
                listener.onMarketdataUpdate(context);
                try {
                    Thread.currentThread().wait(1000);
                } catch (Exception e) {}
            }

        }.subscibe(ticker, this);

    }

    @Override
    public void onMarketdataUpdate(Context context) {
        Price price = OptionsPricer.getPrice(context);
        subscriptions.get(context.getTicker()).forEach((client, cachedPrice) -> {
            if( Math.abs(cachedPrice.getPrice() - price.getPrice()) > tolerance) {
                client.sendUpdate(context.getTicker(), price.getPrice());
                cachedPrice.updatePrice(price.getPrice());
            }
        });
    }
}
