package io.github.inbanithi.jastra.assembler.file;

import io.github.inbanithi.jastra.assembler.function.FunctionBuilder;
import io.github.inbanithi.jastra.assembler.function.JastraFunction;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;

public class JastraFile {

    private String name;

    private List<JastraFunction> functions;

    public JastraFile(String name) throws FileNotFoundException {
        this.name = name;
    }

    public void addFunction(JastraFunction function){
        functions.add(function);
    }

}
