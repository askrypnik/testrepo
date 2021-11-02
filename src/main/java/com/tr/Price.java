package com.tr;

import java.math.BigDecimal;

/*
    Serialized ??
 */
public class Price {

    private Context context;
    private double price; // @ToDo: move to BigDecimal

    public Price(double price, Context context) {
        this.price = price;
    }

    public Context getContext() { return context; }

    public double getPrice() { return price; }

    public void updatePrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Price{" +
                "price=" + price +
                '}';
    }
}
