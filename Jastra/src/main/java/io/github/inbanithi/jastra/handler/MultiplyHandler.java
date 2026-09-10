package io.github.inbanithi.jastra.handler;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.specification.core.Type;
import io.github.inbanithi.jastra.specification.core.Value;
import io.github.inbanithi.jastra.specification.vm.Frame;

public class MultiplyHandler extends Handler {
    @Override
    public void execute(JastraVirtualMachine vm, Frame frame) {
        Value right = frame.operandStack.pop();
        Value left = frame.operandStack.pop();

        Type resultType = promoteNumeric(left.type(), right.type());
        Value result = switch (resultType) {
            case INT -> Value.ofInt(left.asInt() * right.asInt());
            case LONG -> Value.ofLong(left.asLong() * right.asLong());
            case FLOAT -> Value.ofFloat(left.asFloat() * right.asFloat());
            case DOUBLE -> Value.ofDouble(left.asDouble() * right.asDouble());
            default -> throw new RuntimeException("Invalid types for multiply");
        };
        frame.operandStack.push(result);
    }

    private Type promoteNumeric(Type a, Type b) {
        if (a == Type.DOUBLE || b == Type.DOUBLE)
            return Type.DOUBLE;

        if (a == Type.FLOAT || b == Type.FLOAT)
            return Type.FLOAT;

        if (a == Type.LONG || b == Type.LONG)
            return Type.LONG;

        return Type.INT;
    }
}
