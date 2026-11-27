package io.github.inbanithi.jastra.types;

import io.github.inbanithi.jastra.specification.core.Type;
import io.github.inbanithi.jastra.specification.core.Value;

import java.lang.reflect.Array;

public class JastraArray {

    private final Type type;

    private final Object elements;

    public JastraArray(Type type, int size) {
        this.type = type;
        this.elements = switch (type){
            case INT -> new int[size];
            case LONG -> new long[size];
            case FLOAT -> new float[size];
            case DOUBLE -> new double[size];
            case BOOLEAN -> new boolean[size];
            case CHARACTER -> new char[size];
            case STRING -> new String[size];
            default -> new RuntimeException("invalid type to create array");
        };
    }

    public Object getArray(){
        return elements;
    }

    public void setValue(int index, Value value){
        if(!value.type().equals(type)){
            throw new RuntimeException("Invalid element type "+value.type().name()+" for array type "+type.name());
        }
        switch (value.type()){
            case INT -> Array.setInt(elements, index, value.asInt());
            case LONG -> Array.setLong(elements, index, value.asLong());
            case FLOAT -> Array.setFloat(elements, index, value.asFloat());
            case DOUBLE -> Array.setDouble(elements, index, value.asDouble());
            case BOOLEAN -> Array.setBoolean(elements, index, (boolean) value.raw());
            case CHARACTER -> Array.setChar(elements, index, value.asChar());
            case STRING -> Array.set(elements, index, value.asString());
        }
    }

    public Value getValue(int index){
        return switch (type){
            case INT -> Value.ofInt(Array.getInt(elements, index));
            case LONG -> Value.ofLong(Array.getLong(elements, index));
            case FLOAT -> Value.ofFloat(Array.getFloat(elements, index));
            case DOUBLE -> Value.ofDouble(Array.getDouble(elements, index));
            case BOOLEAN -> Value.ofBoolean(Array.getBoolean(elements, index));
            case CHARACTER -> Value.ofChar(Array.getChar(elements, index));
            case STRING -> Value.ofString((String) Array.get(elements, index));
            default -> throw new RuntimeException("cannot get array value invalid type");
        };
    }
}
