package io.github.inbanithi.jastra.handler;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.specification.core.Type;
import io.github.inbanithi.jastra.specification.core.Value;
import io.github.inbanithi.jastra.specification.vm.Frame;

import java.nio.charset.StandardCharsets;

public class StoreHandler extends Handler {

    @Override
    public void execute(JastraVirtualMachine vm, Frame frame) {
        int destination = frame.fetchByte();
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
            case FLOAT -> Value.ofFloat(frame.fetchFloat());
            default -> null;
        };
        frame.registers[destination] = value;
    }
}
