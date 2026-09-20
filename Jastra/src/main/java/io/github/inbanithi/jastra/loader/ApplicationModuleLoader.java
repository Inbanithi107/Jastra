package io.github.inbanithi.jastra.loader;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import io.github.inbanithi.jastra.specification.core.Constant;
import io.github.inbanithi.jastra.specification.core.ConstantType;
import io.github.inbanithi.jastra.specification.core.Module;
import io.github.inbanithi.jastra.specification.function.ConstantPoolTable;
import io.github.inbanithi.jastra.specification.function.FunctionTable;
import io.github.inbanithi.jastra.specification.function.JastraFunction;

public class ApplicationModuleLoader implements ModuleLoader {

    private String extension = ".jc";

    private byte[] MAGIC = {'J', 'V', 'M', 'J'};

    private byte[] VERSION = {1, 0, 0};

    private DataInputStream in;

    private ByteArrayInputStream bais;

    @Override
    public Module load(String name, Path path) {
        Path file = path.resolve(name+extension).normalize();
        //System.out.println(file);
        byte[] code;
        try {
            code = Files.readAllBytes(file);
        }catch (IOException e) {
            throw new RuntimeException("File cant be read");
        }
        bais = new ByteArrayInputStream(code);
        in = new DataInputStream(bais);
        verifyHeader();
        boolean isStandAlone;
        try {
            isStandAlone = in.readBoolean();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        ConstantPoolTable constants = buildConstantPool();
        FunctionTable functions = buildFunctionTable();
        int entry = -1;
        int codeSectionOffset;
        if(isStandAlone){
            entry = code.length - bais.available()+1;
            codeSectionOffset = entry+4;
            //System.out.println(entry+"-"+code[entry]+"-----"+codeSectionOffset+"-"+code[codeSectionOffset]);
        }
        else{
            codeSectionOffset = code.length - bais.available();
        }
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Module(name, code, functions, constants, entry, codeSectionOffset);
    }

    private void verifyHeader(){
        try{
            byte[] magic = new byte[MAGIC.length];
            in.read(magic);
            for(int i=0;i<MAGIC.length;i++){
                if(magic[i]!=MAGIC[i]){
                    throw new RuntimeException("Invalid File");
                }
            }
            byte[] version = new byte[VERSION.length];
            in.read(version);
            for(int i=0;i<VERSION.length;i++){
                if(version[i]!=VERSION[i]){
                    throw new RuntimeException("Unsupported Version");
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private ConstantPoolTable buildConstantPool(){
        ConstantPoolTable table = new ConstantPoolTable();
        try {

            Short size = in.readShort();
            for(int i=0;i<size;i++){
                int id = in.readShort();
                byte tag = in.readByte();
                ConstantType type = ConstantType.from(tag);
                Object value = switch (type) {
                    case INTEGER -> in.readInt();
                    case FLOAT -> in.readFloat();
                    case STRING -> {
                        int length = in.readShort();
                        byte[] arr = new byte[length];
                        in.read(arr);
                        yield new String(arr, StandardCharsets.UTF_8);
                    }
                };
                Constant constant = new Constant(id, type, value);
                //System.out.println(id+"----"+type.name()+"----"+value);
                table.addConstant(id, constant);
            }
            return table;
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private FunctionTable buildFunctionTable(){
        FunctionTable table = new FunctionTable();
        try {
            int size = in.readShort();
            for(int i=0;i<size;i++){
                int id = in.readShort();
                int length = in.readByte();
                byte[] arr = new byte[length];
                in.read(arr);
                String name = new String(arr, StandardCharsets.UTF_8);
                int argCount = in.readShort();
                int codeOffset = in.readInt();
                int codeLength = in.readInt();
                JastraFunction function = new JastraFunction(id, name, argCount, codeOffset, codeLength, null);
                //System.out.println(id+"----"+name+"----"+argCount+"----"+codeOffset+"----"+codeLength);
                table.addFunction(id, function);
            }
            return table;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
