package com.lasuperbe.server;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ServerApplicationTests {

    public static final Logger logger = LoggerFactory.getLogger(ServerApplicationTests.class);
    @Test
    void contextLoads() {
        logger.info("Eventure test begins");
        assertEquals(true,true);
    }

}
