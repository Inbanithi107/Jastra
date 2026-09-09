package io.github.inbanithi.jastra;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Jastra {

    public static void main(String[] args) throws IOException {
        JastraVirtualMachine jvm = new JastraVirtualMachine("Simple");
        byte[] arr = Files.readAllBytes(Path.of("Simple.bin"));
        System.out.println(Arrays.toString(arr));
        jvm.execute(null);
    }

}
