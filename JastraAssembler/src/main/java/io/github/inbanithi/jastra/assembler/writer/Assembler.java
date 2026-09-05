package io.github.inbanithi.jastra.assembler.writer;

import io.github.inbanithi.jastra.assembler.core.Constant;
import io.github.inbanithi.jastra.assembler.core.ConstantType;
import io.github.inbanithi.jastra.assembler.core.OpCode;
import io.github.inbanithi.jastra.assembler.file.JastraFile;
import io.github.inbanithi.jastra.assembler.function.FunctionLayout;
import io.github.inbanithi.jastra.assembler.function.JastraFunction;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;

public class Assembler {

    private DataOutputStream out;

    private static final byte[] MAGIC = {'J', 'V', 'M', 'J'};
    private static final int VERSION_MAJOR = 1;
    private static final int VERSION_MINOR = 0;
    private static final int VERSION_PATCH = 0;

    public void assemble(JastraFile file) throws IOException {
        String fileName = file.getName()+".bin";
        out = new DataOutputStream(new FileOutputStream(Paths.get(fileName).toFile()));
        addHeader();
        writeConstantPool(file);
        writeFunctionsTable(file);
        writeEntry();
        writeCode(file);
        out.flush();
        out.close();
    }

    private void addHeader() throws IOException {
        out.write(MAGIC);
        out.write(VERSION_MAJOR);
        out.write(VERSION_MINOR);
        out.write(VERSION_PATCH);
    }

    private void writeConstantPool(JastraFile file) throws IOException {
        List<Constant> constants = file.getConstants();
        out.writeShort(constants.size());
        for (Constant constant : constants){
            out.writeShort(constant.id());
            out.writeByte(constant.type().getTag());
            switch (constant.type()){
                case INTEGER -> out.writeInt((Integer) constant.value());
                case FLOAT -> out.writeFloat((Float) constant.value());
                case STRING -> {
                    byte[] bytes = ((String) constant.value()).getBytes(StandardCharsets.UTF_8);
                    out.writeShort(bytes.length);
                    out.write(bytes);
                }
            }
        }
    }

    private void writeFunctionsTable(JastraFile file) throws IOException {
        List<JastraFunction> functions = file.getFunctions();
        List<FunctionLayout> layouts = file.getFunctionsLayout();
        out.writeShort(functions.size());
        for(int i=0;i<functions.size();i++){
            JastraFunction function = functions.get(i);
            FunctionLayout layout = layouts.get(i);
            out.writeShort(i);
            out.writeShort(function.getArgCount());
            out.writeShort(function.getConstantNameIndex());
            out.writeInt(layout.codeOffset());
            out.writeInt(layout.codeLength());
        }
    }

    private void writeEntry() throws IOException {
        out.write(0xB0);
        out.write(OpCode.CALL);
        out.write(0);
        out.write(0);
        out.write(0xB7);
    }

    private void writeCode(JastraFile file) throws IOException {
        List<FunctionLayout> layouts = file.getFunctionsLayout();
        for(FunctionLayout layout : layouts){
            out.write(layout.code());
        }
    }

}
