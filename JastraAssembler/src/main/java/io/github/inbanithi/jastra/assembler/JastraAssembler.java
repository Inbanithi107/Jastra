package io.github.inbanithi.jastra.assembler;

import io.github.inbanithi.jastra.assembler.core.Constant;
import io.github.inbanithi.jastra.assembler.core.ConstantType;
import io.github.inbanithi.jastra.assembler.core.Value;
import io.github.inbanithi.jastra.assembler.file.JastraFile;
import io.github.inbanithi.jastra.assembler.function.FunctionBuilder;
import io.github.inbanithi.jastra.assembler.function.JastraFunction;
import io.github.inbanithi.jastra.assembler.writer.Assembler;

import java.io.IOException;

public class JastraAssembler {

    public static void main() throws IOException {

        JastraFile file = new JastraFile("hello");
        file.addConstant(new Constant(0, ConstantType.STRING, "main"));
        JastraFunction function = new FunctionBuilder()
                .name("main")
                .argCount(0)
                .constantNameIndex(0)
                .store(0, Value.ofString("Inbanithi"))
                .load(0)
                .print()
                .returnVoid();
        file.addFunction(function);
        Assembler assembler = new Assembler();
        assembler.assemble(file);
        System.out.println("Program Finished");

    }

}
