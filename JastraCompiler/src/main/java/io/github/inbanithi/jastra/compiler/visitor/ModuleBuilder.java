package io.github.inbanithi.jastra.compiler.visitor;

import io.github.inabnithi.jastra.compiler.JastraBaseVisitor;
import io.github.inabnithi.jastra.compiler.JastraParser;
import io.github.inbanithi.jastra.assembler.file.JastraFile;
import io.github.inbanithi.jastra.assembler.function.FunctionBuilder;
import io.github.inbanithi.jastra.specification.core.Constant;
import io.github.inbanithi.jastra.specification.core.ConstantType;
import io.github.inbanithi.jastra.specification.core.Value;
import io.github.inbanithi.jastra.specification.function.JastraFunction;

import java.util.HashMap;
import java.util.Map;

public class ModuleBuilder extends JastraBaseVisitor<Object> {

    private JastraFile file;

    private FunctionBuilder func;

    private Map<String,Integer> functions;

    private Map<String, Integer> constants = new HashMap<>();

    private int constantIndexer;

    public ModuleBuilder(String name, Map<String,Integer> map) {
        file = new JastraFile(name);
        functions = map;
    }

    public JastraFile getFile(){
        return file;
    }

    @Override
    public Object visitConstants(JastraParser.ConstantsContext ctx) {
        String name = ctx.ID().getText();
        int id = constantIndexer++;
        if(ctx.INTEGER()!=null){
            file.addConstant(new Constant(id, ConstantType.INTEGER, Integer.parseInt(ctx.INTEGER().getText())));
        }
        else if(ctx.FLOAT()!=null){
            file.addConstant(new Constant(id, ConstantType.FLOAT, Float.parseFloat(ctx.FLOAT().getText())));
        }
        else if(ctx.STRING()!=null){
            file.addConstant(new Constant(id, ConstantType.STRING, ctx.STRING().getText().substring(1, ctx.STRING().getText().length()-1)));
        }
        constants.put(name, id);
        return null;
    }

    @Override
    public Object visitFunctionDeclration(JastraParser.FunctionDeclrationContext ctx) {
        String name = ctx.ID().getText();
        int argCount = Integer.parseInt(ctx.INTEGER().getText());
        func = new FunctionBuilder().id(functions.get(name)).name(name).argCount(argCount);
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
    public Object visitPopAndStoreinRegisterStatement(JastraParser.PopAndStoreinRegisterStatementContext ctx) {
        int register = Integer.parseInt(ctx.REGISTER().getText().substring(1));
        func.popAndStore(register);
        return null;
    }

    @Override
    public Object visitLoadStatement(JastraParser.LoadStatementContext ctx) {
        int register = Integer.parseInt(ctx.REGISTER().getText().substring(1));
        func.load(register);
        return null;
    }

    @Override
    public Object visitLoadFromConstantStatement(JastraParser.LoadFromConstantStatementContext ctx) {
        int id = constants.get(ctx.ID().getText());
        func.loadFromConst(id);
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
    public Object visitCompareStatement(JastraParser.CompareStatementContext ctx) {
        if(ctx.CMP_EQ()!=null){
            func.cmpEq();
        }
        else if(ctx.CMP_NE()!=null){
            func.cmpNeq();
        }
        else if(ctx.CMP_LT()!=null){
            func.cmpLt();
        }
        else if(ctx.CMP_LE()!=null){
            func.cmpLeq();
        }
        else if(ctx.CMP_GT()!=null){
            func.cmpGt();
        }
        else if(ctx.CMP_GE()!=null){
            func.cmpGeq();
        }
        return null;
    }

    @Override
    public Object visitConditionalStatement(JastraParser.ConditionalStatementContext ctx) {
        String condition = ctx.IF().getText();
        if(ctx.ELSE()!=null){
            func.ifElse(
                    (c)-> {
                        for(JastraParser.StatementContext statement : ctx.ifStatements){
                            visit(statement);
                        }
                        return null;
                    },
                    (v)-> {
                        for(JastraParser.StatementContext statement : ctx.elseStatements){
                            visit(statement);
                        }
                        return null;
                    },
                    condition
            );
        }
        return null;
    }

    @Override
    public Object visitPrintStatement(JastraParser.PrintStatementContext ctx) {
        func.print();
        return null;
    }

    @Override
    public Object visitCallStatement(JastraParser.CallStatementContext ctx) {
        String name = ctx.ID().getText();
        int argCount = Integer.parseInt(ctx.INTEGER().getText());
        func.call(functions.get(name), argCount);
        return null;
    }

    @Override
    public Object visitReturnStatement(JastraParser.ReturnStatementContext ctx) {
        int count = Integer.parseInt(ctx.INTEGER().getText());
        JastraFunction function = func.returnValues(count);
        file.addFunction(function);
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
