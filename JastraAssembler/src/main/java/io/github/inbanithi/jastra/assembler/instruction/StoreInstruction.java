package io.github.inbanithi.jastra.assembler.instruction;

import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.core.Type;
import io.github.inbanithi.jastra.specification.core.Value;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class StoreInstruction extends Instruction {

    private final int destination;

    private final Value value;

    public StoreInstruction(int destination, Value value) {
        super(OpCode.STORE);
        this.destination = destination;
        this.value = value;
    }

    @Override
    public int getSize() {
        int size = 1;
        size += 1;
        size += 1;

        switch (value.type()) {
            case NULL -> {
            }

            case INT -> size += 4;

            case LONG -> size += 8;

            case FLOAT -> size += 4;

            case DOUBLE -> size += 8;

            case BOOLEAN -> size += 1;

            case CHARACTER -> size += 2;

            case STRING -> {
                byte[] bytes =
                        ((String) value.raw()).getBytes(StandardCharsets.UTF_8);
                size += 4;
                size += bytes.length;
            }
        }

        return size;
    }

    @Override
    public void writeTo(DataOutputStream out) throws IOException {
        out.writeByte(opCode);
        out.writeByte(destination);
        switch (value.type()) {
            case NULL -> {
                out.writeByte(Type.NULL.getCode());
            }

            case INT -> {
                out.writeByte(Type.INT.getCode());
                out.writeInt((Integer) value.raw());
            }

            case LONG -> {
                out.writeByte(Type.LONG.getCode());
                out.writeLong((Long) value.raw());
            }

            case FLOAT -> {
                out.writeByte(Type.FLOAT.getCode());
                out.writeFloat((Float) value.raw());
            }

            case DOUBLE -> {
                out.writeByte(Type.DOUBLE.getCode());
                out.writeDouble((Double) value.raw());
            }

            case BOOLEAN -> {
                out.writeByte(Type.BOOLEAN.getCode());
                out.writeBoolean((Boolean) value.raw());
            }

            case CHARACTER -> {
                out.writeByte(Type.CHARACTER.getCode());
                out.writeChar((Character) value.raw());
            }

            case STRING -> {
                byte[] bytes =
                        ((String) value.raw()).getBytes(StandardCharsets.UTF_8);

                out.writeByte(Type.STRING.getCode());
                out.writeInt(bytes.length);
                out.write(bytes);
            }
        }
    }
}
