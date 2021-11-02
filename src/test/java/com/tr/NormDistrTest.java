package com.tr;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.junit.jupiter.api.Test;

public class NormDistrTest {

    @Test
    public void testNormDistr() {
        NormalDistribution nd = new NormalDistribution();

        assert nd.cumulativeProbability(0.5) > 0;
    }
}
