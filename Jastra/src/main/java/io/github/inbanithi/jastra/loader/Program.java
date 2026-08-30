package io.github.inbanithi.jastra.loader;

import io.github.inbanithi.jastra.function.FunctionTable;

public class Program {

    private FunctionTable functionTable;

    private final byte[] code;

    public Program(byte[] code){
        this.code = code;
        this.functionTable = new FunctionTable(code);
    }

}
