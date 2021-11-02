package com.tr;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.time.LocalDate;

public class PutOption extends AOption {

    public PutOption(Price strikePrice, LocalDate expiryDate) {
        super(OptionType.Put, strikePrice, expiryDate);
    }

    @Override
    public Price getPrice(Context context) {
        NormalDistribution nd = new NormalDistribution();
        double d1 = getD1(context);
        double d2 = getD2(context, d1);

        double p1 = -1. * context.getRd() * getTimeToMaturity(context.getToday());
        double n1 = getStrikePrice().getPrice() * Math.pow(Math.E, p1) * nd.cumulativeProbability(-1. * d2);

        double p2 = -1. * context.getRf() * getTimeToMaturity(context.getToday());
        double n2 = context.getSpotPrice().getPrice() * Math.pow(Math.E, p1) * nd.cumulativeProbability(-1. * d1);

        return new Price(n1 - n2, context);
    }
}
