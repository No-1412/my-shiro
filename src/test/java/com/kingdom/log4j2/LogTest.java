package com.kingdom.log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author No.1412
 * @version 2018/5/15
 */
public class LogTest {

    private static transient Logger logger = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        String s = "威斯克";
        logger.info("第一个log4j2 info-> {}", s);
        logger.debug("第一个log4j2 debug-> {}", s);
        logger.warn("第一个log4j2 warn-> {}", s);
        logger.error("第一个log4j2 error-> {}", s);
        logger.trace("第一个log4j2 trace-> {}", s);
    }

}
