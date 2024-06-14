package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);
        byte varByte = 2;
        short varShort = 123;
        int varInt = 10000;
        long varLong = 43214561L;
        float varFloat = 1.111F;
        double varDouble = 3.14;
        char varChar = 'f';
        boolean varBoolean = false;
        LOG.debug("Byte - {}, short - {}, int - {}, long - {},"
                        + "float - {}, double - {}, char - {}, boolean - {}",
                varByte,
                varShort,
                varInt,
                varLong,
                varFloat,
                varDouble,
                varChar,
                varBoolean);
    }
}