package io.github.inbanithi.jastra;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;

import java.io.IOException;

public class Jastra {

    public static void main(String[] args) throws IOException {
        String filename = args[0]+".jc";
        JastraVirtualMachine jvm = new JastraVirtualMachine(filename);
        jvm.execute(null);
    }

}
