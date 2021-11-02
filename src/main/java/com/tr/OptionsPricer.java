package com.tr;

import java.time.LocalDate;

public class OptionsPricer {

    /**
     * Need to think about bettey way to load the instruments. those 2 are just dummy to price for now
     */
    public static String Ticker_A = "ticker_A";
    private static AOption optionA = new CallOption(new Price(103., null), LocalDate.now().plusDays(365));

    public static String Ticker_B = "ticker_B";
    private static AOption optionB = new PutOption(new Price(99., null), LocalDate.now().plusDays(365));

    public static Price getPrice(Context context) {
        if(context == null) {
            throw new OptionsPricerException("Context cannot be null");
        }
        if(Ticker_A.equals(context.getTicker())) {
            return optionA.getPrice(context);
        } else if(Ticker_B.equals(context.getTicker())) {
            return optionB.getPrice(context);
        }
        throw new OptionsPricerException("Unrecognized ticker");
    }
}
