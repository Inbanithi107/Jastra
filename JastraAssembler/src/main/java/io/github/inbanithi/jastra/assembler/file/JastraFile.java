package io.github.inbanithi.jastra.assembler.file;

import io.github.inbanithi.jastra.assembler.core.ByteCodeGenerator;
import io.github.inbanithi.jastra.assembler.function.FunctionLayout;
import io.github.inbanithi.jastra.specification.core.Constant;
import io.github.inbanithi.jastra.specification.function.JastraFunction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JastraFile {

    private String name;

    private List<JastraFunction> functions;

    private List<Constant> constants;

    public int offset = 0;

    private boolean layoutCalculated = false;

    public JastraFile(String name) throws FileNotFoundException {
        this.name = name;
        this.functions = new ArrayList<>();
        this.constants = new ArrayList<>();
    }

    public void addFunction(JastraFunction function){
        functions.add(function);
    }

    public List<JastraFunction> getFunctionsLayout() throws IOException {
        if(layoutCalculated){
            return functions;
        }
        List<JastraFunction> layouts = new ArrayList<>();
        offset=0;
        for (JastraFunction function : functions){
            byte[] code = ByteCodeGenerator.assembleFunction(function);
            JastraFunction layout = new JastraFunction(
                    function.getId(),
                    function.getName(),
                    function.getArgCount(),
                    offset,
                    code.length,
                    code
            );
            layouts.add(layout);
            offset+=code.length;
        }
        functions = layouts;
        layoutCalculated = true;
        return functions;
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
