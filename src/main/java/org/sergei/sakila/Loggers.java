package org.sergei.sakila;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sergei Visotsky
 */
public final class Loggers {

    private static final Logger LOGGER = LoggerFactory.getLogger(Loggers.class);

    public static void sql(String SQL, ImmutableMap<String, Object> params) {
        LOGGER.debug("1st SQL: {} with this parameters: {}", SQL, params);
    }
}
