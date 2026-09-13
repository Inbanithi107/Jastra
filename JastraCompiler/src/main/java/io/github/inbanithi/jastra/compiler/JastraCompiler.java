package io.github.inbanithi.jastra.compiler;

import io.github.inabnithi.jastra.compiler.JastraLexer;
import io.github.inabnithi.jastra.compiler.JastraParser;
import io.github.inbanithi.jastra.assembler.file.JastraFile;
import io.github.inbanithi.jastra.assembler.writer.Assembler;
import io.github.inbanithi.jastra.compiler.visitor.ModuleBuilder;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class JastraCompiler {

    public static void main() {
        try (FileReader reader = new FileReader(Paths.get("Hello.jastra").toFile())){
            String source = reader.readAllAsString();
            CharStream input = CharStreams.fromString(source);
            JastraLexer lexer = new JastraLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            JastraParser parser = new JastraParser(tokens);
            ParseTree tree = parser.program();
            ModuleBuilder builder = new ModuleBuilder("Hello");
            builder.visit(tree);
            JastraFile file = builder.getFile();
            Assembler assembler = new Assembler();
            assembler.assemble(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
