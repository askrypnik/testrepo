package com.tr;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class AOption {

    enum OptionType {
        Call, Put
    }

    /**
     *
     * @param t
     * @param strikePrice
     * @param expiryDate
     */
    protected AOption(OptionType t, Price strikePrice, LocalDate expiryDate) {
        oType = t;
        this.strikePrice = strikePrice;
        this.expiryDate = expiryDate;
        if(strikePrice == null || expiryDate == null) {
            throw new OptionsPricerException("Null supplied");
        }
    }
    private Price strikePrice;
    private LocalDate expiryDate;
    private OptionType oType;

    public Price getStrikePrice() { return strikePrice; }
    public LocalDate getExpiryDate() { return expiryDate; }

    public int getTimeToMaturity(LocalDate nowTime) {
        return (int) ChronoUnit.DAYS.between(nowTime, expiryDate); // not dealing with long days
    }

    protected double getD1(Context context) {
        long numberOfDaysToMaturity = getTimeToMaturity(context.getToday());
        double d1 = Math.log(context.getSpotPrice().getPrice() / getStrikePrice().getPrice()) +
                (context.getRd() - context.getRf() + Math.pow(context.getVolatility(), 2)/2.) * numberOfDaysToMaturity;
        d1 /= context.getVolatility() * Math.sqrt(numberOfDaysToMaturity);
        return d1;
    }

    protected double getD2(Context context, double d1) {
        return d1 - context.getVolatility() * Math.sqrt(getTimeToMaturity(context.getToday()));
    }

    public abstract Price getPrice(Context context);
}
