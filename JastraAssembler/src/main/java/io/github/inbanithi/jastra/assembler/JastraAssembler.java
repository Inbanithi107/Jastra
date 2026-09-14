package io.github.inbanithi.jastra.assembler;

import io.github.inbanithi.jastra.assembler.file.JastraFile;
import io.github.inbanithi.jastra.assembler.function.FunctionBuilder;
import io.github.inbanithi.jastra.assembler.writer.Assembler;
import io.github.inbanithi.jastra.specification.core.Constant;
import io.github.inbanithi.jastra.specification.core.ConstantType;
import io.github.inbanithi.jastra.specification.core.Value;
import io.github.inbanithi.jastra.specification.function.JastraFunction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

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
                .store(0, Value.ofInt(0))
                .load(0)
                .ifThen(
                        (c)-> c.loadFromConst(0).print().returnVoid(),
                        "JNZ"
                )
                .load(0)
                .print()
                .returnVoid();
        file.addFunction(function);
        Assembler assembler = new Assembler();
        assembler.assemble(file);
        System.out.println("Program Finished");
        byte[] arr = Files.readAllBytes(Path.of("hello.bin"));
        System.out.println(Arrays.toString(arr));
    }

}
