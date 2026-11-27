package io.github.inbanithi.jastra.assembler.file;

import io.github.inbanithi.jastra.assembler.core.ByteCodeGenerator;
import io.github.inbanithi.jastra.assembler.core.Resolvable;
import io.github.inbanithi.jastra.assembler.instruction.jump.LabelInstruction;
import io.github.inbanithi.jastra.specification.core.Constant;
import io.github.inbanithi.jastra.specification.function.JastraFunction;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JastraFile {

    private String name;

    private List<JastraFunction> functions;

    private List<Constant> constants;

    public int offset = 0;

    private boolean layoutCalculated = false;

    public boolean isStandAlone = true;

    public JastraFile(String name) {
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
            calculateOffset(function);
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

    private void calculateOffset(JastraFunction function){
        Map<String, Integer> labels = new HashMap<>();
        int offset=0;
        for(Instruction instruction : function.getInstructions()){
            if(instruction instanceof LabelInstruction labelInstruction){
                labels.put(labelInstruction.getLabel(), offset);
                continue;
            }
            instruction.setOffset(offset);
            offset+= instruction.getSize();
        }
        for(Instruction instruction : function.getInstructions()){
            if(instruction instanceof Resolvable resolvable){
                resolvable.resolve(labels);
            }
        }

    }
}
