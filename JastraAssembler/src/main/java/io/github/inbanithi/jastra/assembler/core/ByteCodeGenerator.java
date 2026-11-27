package io.github.inbanithi.jastra.assembler.core;

import io.github.inbanithi.jastra.specification.function.JastraFunction;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class ByteCodeGenerator {

    public static byte[] assembleFunction(JastraFunction function) throws IOException {
        List<Instruction> instructions = function.getInstructions();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        for(Instruction instruction : instructions){
            instruction.writeTo(out);
        }
        out.flush();
        return stream.toByteArray();
    }

}
