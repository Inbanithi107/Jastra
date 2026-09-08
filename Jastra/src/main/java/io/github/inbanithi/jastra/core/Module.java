package io.github.inbanithi.jastra.core;

import io.github.inbanithi.jastra.function.ConstantPoolTable;
import io.github.inbanithi.jastra.function.FunctionTable;

public class Module {

    private final String name;

    private final ConstantPoolTable constantPoolTable;

    private final FunctionTable functionTable;

    private final byte[] code;

    public Module(String name, byte[] code, FunctionTable functionTable, ConstantPoolTable constantPoolTable){
        this.name = name;
        this.functionTable = functionTable;
        this.constantPoolTable = constantPoolTable;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public byte[] getCode() {
        return code;
    }
}
