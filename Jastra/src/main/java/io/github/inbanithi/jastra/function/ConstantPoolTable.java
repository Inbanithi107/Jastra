package io.github.inbanithi.jastra.function;

import io.github.inbanithi.jastra.specification.core.Constant;

import java.util.HashMap;
import java.util.Map;

public class ConstantPoolTable {

    private final Map<Integer, Constant> table;

    public ConstantPoolTable() {
        this.table = new HashMap<>();
    }

    public void addConstant(int id, Constant constant){
        table.put(id, constant);
    }

    public Constant getConstant(int id){
        return table.getOrDefault(id, null);
    }

}
