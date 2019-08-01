package com.bjs.java8.part03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo1_ProcessorFile {

    public static void main(String[] args) throws IOException {
        // 处理一行
        processorFile((br) -> br.readLine());
        // 处理两行
        processorFile((br) -> br.readLine() + br.readLine());

    }

    public static String processorFile(BufferedReaderProcessor p) throws IOException {
        try (
                BufferedReader br = new BufferedReader(new FileReader("data.txt"))
        ) {
            return p.processor(br);
        }
    }

    public static String processorFile() throws IOException {
        try (
                BufferedReader br = new BufferedReader(new FileReader("data.txt"));
        ) {
            return br.readLine();
        }
    }
}
