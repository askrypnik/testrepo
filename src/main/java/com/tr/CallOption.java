package com.tr;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.time.LocalDate;

public class CallOption extends AOption {

    public CallOption(Price strikePrice, LocalDate expiryDate) {
        super(OptionType.Call, strikePrice, expiryDate);
    }

    @Override
    public Price getPrice(Context context) {
        NormalDistribution nd = new NormalDistribution();
        double d1 = getD1(context);
        double d2 = getD2(context, d1);

        double p1 = -1. * context.getRf() * getTimeToMaturity(context.getToday());
        double n1 = context.getSpotPrice().getPrice() * Math.pow(Math.E, p1) * nd.cumulativeProbability(d1);

        double p2 = -1. * context.getRd() * getTimeToMaturity(context.getToday());
        double n2 = getStrikePrice().getPrice() * Math.pow(Math.E, p2) * nd.cumulativeProbability(d2);
        return new Price(n1 - n2, context);
    }
}
