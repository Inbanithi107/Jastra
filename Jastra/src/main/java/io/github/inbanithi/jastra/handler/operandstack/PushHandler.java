package io.github.inbanithi.jastra.handler.operandstack;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.handler.Handler;
import io.github.inbanithi.jastra.specification.core.Type;
import io.github.inbanithi.jastra.specification.core.Value;
import io.github.inbanithi.jastra.specification.vm.Frame;

import java.nio.charset.StandardCharsets;

public class PushHandler extends Handler {
    @Override
    public void execute(JastraVirtualMachine vm, Frame frame) {
        Type type = Type.from(frame.fetchByte()&0xFF);
        Value value =
                switch (type) {
                    case STRING -> {
                        int length = frame.fetchInt();
                        byte[] arr = new byte[length];
                        for(int i=0;i<length;i++){
                            arr[i] = frame.fetchByte();
                        }
                        yield Value.ofString(new String(arr, StandardCharsets.UTF_8));
                    }
                    case INT -> Value.ofInt(frame.fetchInt());
                    case LONG -> Value.ofLong(frame.fetchLong());
                    case FLOAT -> Value.ofFloat(frame.fetchFloat());
                    case DOUBLE -> Value.ofDouble(frame.fetchDouble());
                    case CHARACTER -> Value.ofChar(frame.fetchChar());
                    case BOOLEAN -> Value.ofBoolean(frame.fetchBoolean());
                    default -> null;
                };
        frame.operandStack.push(value);
    }
}
