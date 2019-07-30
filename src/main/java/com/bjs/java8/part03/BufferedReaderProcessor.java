package com.bjs.java8.part03;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferedReaderProcessor {
    String processor(BufferedReader b) throws IOException;
}
