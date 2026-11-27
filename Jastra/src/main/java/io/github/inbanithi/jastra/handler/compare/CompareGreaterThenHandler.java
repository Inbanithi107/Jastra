package io.github.inbanithi.jastra.handler.compare;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.handler.Handler;
import io.github.inbanithi.jastra.specification.core.Type;
import io.github.inbanithi.jastra.specification.core.Value;
import io.github.inbanithi.jastra.specification.vm.Frame;

public class CompareGreaterThenHandler extends Handler {
    @Override
    public void execute(JastraVirtualMachine vm, Frame frame) {
        Value right = frame.operandStack.pop();
        Value left = frame.operandStack.pop();
        if(left.type()!=right.type()){
            throw new RuntimeException("Invalid elements to compare");
        }
        Type type = left.type();
        boolean value = switch (type) {
            case INT, CHARACTER -> left.asInt() > right.asInt();
            case LONG -> left.asLong() > right.asLong();
            case FLOAT -> left.asFloat() > right.asFloat();
            case DOUBLE -> left.asDouble() > right.asDouble();
            default -> throw new RuntimeException("Null values cant be compared");
        };
        frame.operandStack.push(Value.ofBoolean(value));
    }
}
