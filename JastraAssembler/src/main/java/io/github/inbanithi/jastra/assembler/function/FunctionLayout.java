package io.github.inbanithi.jastra.assembler.function;

public record FunctionLayout(int id, int argCount, int codeOffset, int codeLength, byte[] code) {
}
