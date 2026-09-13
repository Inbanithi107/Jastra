package io.github.inbanithi.jastra.compiler.visitor;

import io.github.inabnithi.jastra.compiler.JastraBaseVisitor;
import io.github.inabnithi.jastra.compiler.JastraParser;
import io.github.inbanithi.jastra.assembler.file.JastraFile;
import io.github.inbanithi.jastra.assembler.function.FunctionBuilder;
import io.github.inbanithi.jastra.specification.core.Value;
import io.github.inbanithi.jastra.specification.function.JastraFunction;

public class ModuleBuilder extends JastraBaseVisitor<Object> {

    private JastraFile file;

    private FunctionBuilder func;

    private int functionIndexer = 0;

    public ModuleBuilder(String name) {
        file = new JastraFile(name);
    }

    public JastraFile getFile(){
        return file;
    }

    @Override
    public Object visitFunctionDeclration(JastraParser.FunctionDeclrationContext ctx) {
        String name = ctx.ID().getText();
        int argCount = Integer.parseInt(ctx.INTEGER().getText());
        func = new FunctionBuilder().id(functionIndexer++).name(name).argCount(argCount);
        visitChildren(ctx);
        return null;
    }

    @Override
    public Object visitStoreStatement(JastraParser.StoreStatementContext ctx) {
        int register = Integer.parseInt(ctx.REGISTER().getText().substring(1));
        Value value = (Value) visit(ctx.value());
        func = func.store(register, value);
        return null;
    }

    @Override
    public Object visitLoadStatement(JastraParser.LoadStatementContext ctx) {
        int register = Integer.parseInt(ctx.REGISTER().getText().substring(1));
        func.load(register);
        return null;
    }

    @Override
    public Object visitArithmeticStatement(JastraParser.ArithmeticStatementContext ctx) {
        if(ctx.ADD()!=null){
            func.add();
        }else if (ctx.SUBTRACT()!=null){
            func.subtract();
        } else if (ctx.MULTIPLY()!=null) {
            func.multiply();
        }else if(ctx.DIVIDE()!=null){
            func.divide();
        }
        return null;
    }

    @Override
    public Object visitPrintStatement(JastraParser.PrintStatementContext ctx) {
        func.print();
        return null;
    }

    @Override
    public Object visitReturnVoidStatement(JastraParser.ReturnVoidStatementContext ctx) {
        JastraFunction function = func.returnVoid();
        file.addFunction(function);
        return null;
    }

    @Override
    public Object visitIntgerLiteral(JastraParser.IntgerLiteralContext ctx) {
        return Value.ofInt(Integer.parseInt(ctx.INTEGER().getText()));
    }

    @Override
    public Object visitLongLiteral(JastraParser.LongLiteralContext ctx) {
        return Value.ofLong(Long.parseLong(ctx.LONG().getText()));
    }

    @Override
    public Object visitFloatLiteral(JastraParser.FloatLiteralContext ctx) {
        return Value.ofFloat(Float.parseFloat(ctx.FLOAT().getText()));
    }

    @Override
    public Object visitDoubleLiteral(JastraParser.DoubleLiteralContext ctx) {
        return Value.ofDouble(Double.parseDouble(ctx.DOUBLE().getText()));
    }

    @Override
    public Object visitCharLiteral(JastraParser.CharLiteralContext ctx) {
        return Value.ofChar(ctx.CHARACTER().getText().charAt(0));
    }

    @Override
    public Object visitStringLiteral(JastraParser.StringLiteralContext ctx) {
        String str = ctx.STRING().getText();
        return Value.ofString(str.substring(1, str.length()-1));
    }

    @Override
    public Object visitBooleanLiteral(JastraParser.BooleanLiteralContext ctx) {
        if(ctx.BOOLEAN().getText().equals("true")){
            return Value.ofBoolean(true);
        }else{
            return Value.ofBoolean(false);
        }
    }
}
