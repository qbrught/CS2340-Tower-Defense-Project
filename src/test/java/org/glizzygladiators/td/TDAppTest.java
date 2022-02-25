package org.glizzygladiators.td;
  
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TDAppTest {

    private static final Logger logger = LogManager.getLogger(TDApp.class);

    @Test
    public void testLucky() {
        logger.info("Hello!");
        assertEquals(7, 2+5);
    }

}
