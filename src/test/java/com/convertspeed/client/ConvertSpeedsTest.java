package com.convertspeed.client;

import static com.convertspeed.client.SpeedUnit.CENTIMETERS_PERSECOND;
import static com.convertspeed.client.SpeedUnit.KILOMETERS_PERHOUR;
import static java.lang.Double.*;
import static java.lang.Double.MAX_VALUE;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Test;

public class ConvertSpeedsTest {

    JAXBClient jaxbClient = new JAXBClient();
    private ConvertSpeedsSoap convertSpeedsSoap;

    public ConvertSpeedsTest() {
        this.convertSpeedsSoap = this.jaxbClient.client;
    }

    @Test
    public void testConvertZeroSpeed() {
        double speed = convertSpeedsSoap.convertSpeed(0, KILOMETERS_PERHOUR, CENTIMETERS_PERSECOND);
        assertEquals(0, speed, 1e-15);
    }

    @Test
    public void testMaxValue() {
        double speed = convertSpeedsSoap.convertSpeed(MAX_VALUE, KILOMETERS_PERHOUR, KILOMETERS_PERHOUR);
        assertTrue(speed == MAX_VALUE);
    }

    @Test
    public void testInfinite() {
        double speed = convertSpeedsSoap.convertSpeed(MAX_VALUE, KILOMETERS_PERHOUR, CENTIMETERS_PERSECOND);
        assertTrue(isInfinite(speed));
    }

    @Test
    public void testNaN() {
        double speed = convertSpeedsSoap.convertSpeed(NaN, KILOMETERS_PERHOUR, CENTIMETERS_PERSECOND);
        assertTrue(isNaN(speed));
    }

    @Test
    public void testPositiveInfinity() {
        double speed = convertSpeedsSoap.convertSpeed(POSITIVE_INFINITY, KILOMETERS_PERHOUR, CENTIMETERS_PERSECOND);
        assertTrue(isInfinite(speed));
    }
}
