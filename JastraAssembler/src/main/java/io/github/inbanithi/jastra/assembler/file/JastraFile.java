package io.github.inbanithi.jastra.assembler.file;

import io.github.inbanithi.jastra.assembler.core.ByteCodeGenerator;
import io.github.inbanithi.jastra.assembler.core.Constant;
import io.github.inbanithi.jastra.assembler.function.FunctionLayout;
import io.github.inbanithi.jastra.assembler.function.JastraFunction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JastraFile {

    private String name;

    private List<JastraFunction> functions;

    private List<Constant> constants;

    private List<FunctionLayout> layouts;

    public int offset = 0;

    public JastraFile(String name) throws FileNotFoundException {
        this.name = name;
        this.functions = new ArrayList<>();
        this.constants = new ArrayList<>();
        this.layouts = new ArrayList<>();
    }

    public void addFunction(JastraFunction function){
        functions.add(function);
    }

    public List<FunctionLayout> getFunctionsLayout() throws IOException {
        if(!layouts.isEmpty()){
            return layouts;
        }
        offset=0;
        for (JastraFunction function : functions){
            byte[] code = ByteCodeGenerator.assembleFunction(function);
            FunctionLayout layout = new FunctionLayout(
                    function.getId(),
                    function.getArgCount(),
                    offset,
                    code.length,
                    code
            );
            layouts.add(layout);
            offset+=code.length;
        }
        return layouts;
    }

    public String getName() {
        return name;
    }

    public List<JastraFunction> getFunctions() {
        return functions;
    }

    public List<Constant> getConstants() {
        return constants;
    }

    public void addConstant(Constant constant){
        constants.add(constant);
    }
}
