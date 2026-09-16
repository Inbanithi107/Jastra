package io.github.inbanithi.jastra.specification.core;

public final class Value {

    private final Type type;
    private final Object value;

    private Value(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    public static Value ofInt(int value) {
        return new Value(Type.INT, value);
    }

    public static Value ofLong(long value) {
        return new Value(Type.LONG, value);
    }

    public static Value ofFloat(float value) {
        return new Value(Type.FLOAT, value);
    }

    public static Value ofDouble(double value) {
        return new Value(Type.DOUBLE, value);
    }

    public static Value ofString(String value) {
        return new Value(Type.STRING, value);
    }

    public static Value ofBoolean(boolean value) {
        return new Value(Type.BOOLEAN, value);
    }

    public static Value ofChar(char value){
        return new Value(Type.CHARACTER, value);
    }

    public static Value nullValue() {
        return new Value(Type.NULL, null);
    }

    public Type type() {
        return type;
    }

    public Object raw() {
        return value;
    }

    public int asInt(){
        return switch (type) {
            case INT -> (Integer) value;
            case CHARACTER -> (Character) value;
            default -> throw  typeError("int");
        };
    }

    public char asChar() {
        return switch (type) {
            case INT, CHARACTER -> (char) value;
            default -> throw typeError("char");
        };
    }

    public long asLong(){
        return switch (type){
            case INT -> (Integer) value;
            case LONG -> (Long) value;
            case CHARACTER -> (Character) value;
            default -> throw typeError("long");
        };
    }

    public float asFloat(){
        return switch (type){
            case INT -> (Integer) value;
            case LONG -> (Long) value;
            case FLOAT -> (Float) value;
            case CHARACTER -> (Character) value;
            default -> throw typeError("float");
        };
    }

    public double asDouble(){
        return switch (type){
            case INT -> (Integer) value;
            case LONG -> (Long) value;
            case FLOAT -> (Float) value;
            case DOUBLE -> (Double) value;
            case CHARACTER -> (Character) value;
            default -> throw typeError("double");
        };
    }

    public String asString(){
        if(type!=Type.STRING){
            throw typeError("string");
        }
        return (String) value;
    }

    public String toStringValue(){
        return switch (type){
            case STRING -> (String) value;
            case CHARACTER -> String.valueOf((char) value);
            case INT -> String.valueOf((int) value);
            case LONG -> String.valueOf((long) value);
            case FLOAT -> String.valueOf((float) value);
            case DOUBLE -> String.valueOf((double) value);
            default -> throw typeError("number");
        };
    }

    public Value cast(Type target) {
        return switch (target) {
            case CHARACTER -> Value.ofChar((char) asInt());

            case INT -> Value.ofInt(asInt());

            case LONG -> Value.ofLong(asLong());

            case FLOAT -> Value.ofFloat(asFloat());

            case DOUBLE -> Value.ofDouble(asDouble());

            case STRING -> Value.ofString(toStringValue());

            default -> throw typeError("number");
        };
    }

    public RuntimeException typeError(String expected){
        return new RuntimeException("Cannot convert " + type + " to " + expected);
    }

}
