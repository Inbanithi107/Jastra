package io.github.inbanithi.jastra.function;

import io.github.inbanithi.jastra.loader.Function;

import java.util.HashMap;
import java.util.Map;

public class FunctionTable {

    private Map<Long, Function> table = new HashMap<>();

    public FunctionTable(byte[] functionInstructions){
        processInstruction(functionInstructions);
    }

    private void processInstruction(byte[] instructions){

    }

    public Function getFunction(long id){
        if(table.containsKey(id)){
            return table.get(id);
        }
        throw new RuntimeException("Function not found");
    }

}
