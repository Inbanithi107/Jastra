package io.github.inbanithi.jastra.function;


import java.util.HashMap;
import java.util.Map;
import io.github.inbanithi.jastra.specification.function.JastraFunction;

public class FunctionTable {

    private Map<Integer, JastraFunction> table = new HashMap<>();

    public JastraFunction getFunction(long id){
        if(table.containsKey(id)){
            return table.get(id);
        }
        throw new RuntimeException("Function not found");
    }

    public void addFunction(int id, JastraFunction function){
        table.put(id, function);
    }

}
