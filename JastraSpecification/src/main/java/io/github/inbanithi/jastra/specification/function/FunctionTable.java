package io.github.inbanithi.jastra.specification.function;


import java.util.HashMap;
import java.util.Map;

public class FunctionTable {

    private Map<Integer, JastraFunction> table = new HashMap<>();

    public JastraFunction getFunction(int id){
        if(table.containsKey(id)){
            return table.get(id);
        }
        throw new RuntimeException("Function not found");
    }

    public void addFunction(int id, JastraFunction function){
        table.put(id, function);
    }

}
