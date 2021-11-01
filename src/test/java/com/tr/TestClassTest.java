package com.tr;

import org.junit.jupiter.api.Test;

class TestClassTest {

    @Test
    void sum() {
        TestClass tc = new TestClass();
        assert 5 == tc.sum(2, 3);
    }
}