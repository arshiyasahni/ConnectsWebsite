package com.sep.backend.utils;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class IDGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        // Generate a random 5-digit number between 10,000 and 99,999
        return ThreadLocalRandom.current().nextInt(10_000, 100_000);
    }
}

