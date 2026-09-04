package io.github.inbanithi.jastra.assembler.core;

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

}
