package io.github.inbanithi.jastra.compiler.visitor;

import io.github.inabnithi.jastra.compiler.JastraBaseVisitor;
import io.github.inabnithi.jastra.compiler.JastraParser;

import java.util.HashMap;
import java.util.Map;

public class MetaBuilder extends JastraBaseVisitor<Object> {

    public Map<String,Integer> functions = new HashMap<>();

    private int functionIndexer;

    @Override
    public Object visitFunctionDeclration(JastraParser.FunctionDeclrationContext ctx) {
        String name = ctx.ID().getText();
        functions.put(name, functionIndexer++);
        return null;
    }
}
