package io.github.inbanithi.jastra.assembler.block;

import io.github.inbanithi.jastra.assembler.function.FunctionBuilder;

@FunctionalInterface
public interface CodeBlock {

    Object build(FunctionBuilder func);

}
