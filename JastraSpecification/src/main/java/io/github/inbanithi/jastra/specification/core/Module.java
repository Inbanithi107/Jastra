package io.github.inbanithi.jastra.specification.core;


import io.github.inbanithi.jastra.specification.function.ConstantPoolTable;
import io.github.inbanithi.jastra.specification.function.FunctionTable;

public class Module {

    public final String name;

    public final ConstantPoolTable constantPoolTable;

    public final FunctionTable functionTable;

    private final byte[] code;

    public final int entry;

    public final int codeSectionOffset;

    public Module(String name, byte[] code, FunctionTable functionTable, ConstantPoolTable constantPoolTable, int entry, int offset){
        this.name = name;
        this.functionTable = functionTable;
        this.constantPoolTable = constantPoolTable;
        this.code = code;
        this.entry = entry;
        this.codeSectionOffset = offset;
    }

    public String getName() {
        return name;
    }

    public byte[] getCode() {
        return code;
    }
}
