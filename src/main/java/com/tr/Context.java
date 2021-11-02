package com.tr;

import java.time.LocalDate;

/**
 * Serialized??
 */
public class Context {

    private LocalDate today = LocalDate.now();
    private String ticker;
    private Price spotPrice;
    private double volatility;
    private double rd; // domestic risk free simple interest rate
    private double rf; // foreign risk free simple interest rate

    public Context(String ticker) {
        this.ticker = ticker;
    }

    public LocalDate getToday() {
        return today;
    }

    public String getTicker() {
        return ticker;
    }

    public Price getSpotPrice() {
        return spotPrice;
    }

    public void setSpotPrice(Price spotPrice) {
        this.spotPrice = spotPrice;
    }

    public double getVolatility() {
        return volatility;
    }

    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }

    public double getRd() {
        return rd;
    }

    public void setRd(double rd) {
        this.rd = rd;
    }

    public double getRf() {
        return rf;
    }

    public void setRf(double rf) {
        this.rf = rf;
    }

    @Override
    public String toString() {
        return "Context{" +
                "today=" + today +
                ", ticker='" + ticker + '\'' +
                ", spotPrice=" + spotPrice +
                ", volatility=" + volatility +
                ", rd=" + rd +
                ", rf=" + rf +
                '}';
    }
}
