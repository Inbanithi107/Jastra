package io.github.inbanithi.jastra.assembler;

import io.github.inbanithi.jastra.assembler.file.JastraFile;
import io.github.inbanithi.jastra.assembler.function.FunctionBuilder;
import io.github.inbanithi.jastra.assembler.writer.Assembler;
import io.github.inbanithi.jastra.specification.core.Constant;
import io.github.inbanithi.jastra.specification.core.ConstantType;
import io.github.inbanithi.jastra.specification.core.Value;
import io.github.inbanithi.jastra.specification.function.JastraFunction;

import java.io.IOException;

public class JastraAssembler {

    public static void main() throws IOException {

        JastraFile file = new JastraFile("hello");
        file.addConstant(new Constant(0, ConstantType.STRING, "main"));
        file.addConstant(new Constant(1, ConstantType.FLOAT, 10.5f));
        file.addConstant(new Constant(2, ConstantType.INTEGER, 25));
        JastraFunction function = new FunctionBuilder()
                .name("main")
                .argCount(0)
                .id(0)
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
