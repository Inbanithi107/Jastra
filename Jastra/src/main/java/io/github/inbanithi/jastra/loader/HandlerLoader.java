package io.github.inbanithi.jastra.loader;

import io.github.inbanithi.jastra.handler.AddHandler;
import io.github.inbanithi.jastra.handler.CallExternalHandler;
import io.github.inbanithi.jastra.handler.CallHandler;
import io.github.inbanithi.jastra.handler.DivisionHandler;
import io.github.inbanithi.jastra.handler.EntryHandler;
import io.github.inbanithi.jastra.handler.HaltHandler;
import io.github.inbanithi.jastra.handler.Handler;
import io.github.inbanithi.jastra.handler.compare.CompareEqualHandler;
import io.github.inbanithi.jastra.handler.compare.CompareGreaterThenHandler;
import io.github.inbanithi.jastra.handler.compare.CompareGreaterThenOrEqualHandler;
import io.github.inbanithi.jastra.handler.compare.CompareLessThenHandler;
import io.github.inbanithi.jastra.handler.compare.CompareLessThenOrEqualHandler;
import io.github.inbanithi.jastra.handler.compare.CompareNotEqualHandler;
import io.github.inbanithi.jastra.handler.jump.JumpHandler;
import io.github.inbanithi.jastra.handler.jump.JumpIfNotZeroHandler;
import io.github.inbanithi.jastra.handler.jump.JumpIfZeroHandler;
import io.github.inbanithi.jastra.handler.LoadFromConstHandler;
import io.github.inbanithi.jastra.handler.LoadHandler;
import io.github.inbanithi.jastra.handler.MultiplyHandler;
import io.github.inbanithi.jastra.handler.PopStoreRegisterHandler;
import io.github.inbanithi.jastra.handler.PrintHandler;
import io.github.inbanithi.jastra.handler.ReturnHandler;
import io.github.inbanithi.jastra.handler.ReturnVoidHandler;
import io.github.inbanithi.jastra.handler.StoreHandler;
import io.github.inbanithi.jastra.handler.SubtractHandler;
import io.github.inbanithi.jastra.handler.operandstack.PopHandler;
import io.github.inbanithi.jastra.handler.operandstack.PushHandler;
import io.github.inbanithi.jastra.specification.core.ControlInstruction;
import io.github.inbanithi.jastra.specification.core.OpCode;

public class HandlerLoader {

    public static Handler[] getHandlers(){
        Handler[] handlers = new Handler[256];
        handlers[ControlInstruction.ENTRY] = new EntryHandler();
        handlers[OpCode.CALL] = new CallHandler();
        handlers[OpCode.CALL_EXTERNAL] = new CallExternalHandler();
        handlers[ControlInstruction.HALT] = new HaltHandler();
        handlers[OpCode.LOAD] = new LoadHandler();
        handlers[OpCode.LOAD_FROM_CONST] = new LoadFromConstHandler();
        handlers[OpCode.STORE] = new StoreHandler();
        handlers[OpCode.POP_STORE_REG] = new PopStoreRegisterHandler();
        handlers[OpCode.ADD] = new AddHandler();
        handlers[OpCode.SUB] = new SubtractHandler();
        handlers[OpCode.MUL] = new MultiplyHandler();
        handlers[OpCode.DIV] = new DivisionHandler();
        handlers[OpCode.PRINT] = new PrintHandler();
        handlers[OpCode.JMP] = new JumpHandler();
        handlers[OpCode.JIZ] = new JumpIfZeroHandler();
        handlers[OpCode.JNZ] = new JumpIfNotZeroHandler();
        handlers[OpCode.RETURN_VOID] = new ReturnVoidHandler();
        handlers[OpCode.RETURN] = new ReturnHandler();
        getCompareHandler(handlers);
        getOperandStackHandler(handlers);
        return handlers;
    }

    private static void getCompareHandler(Handler[] handlers){
        handlers[OpCode.Compare.CMP_EQ] = new CompareEqualHandler();
        handlers[OpCode.Compare.CMP_NE] = new CompareNotEqualHandler();
        handlers[OpCode.Compare.CMP_LT] = new CompareLessThenHandler();
        handlers[OpCode.Compare.CMP_LE] = new CompareLessThenOrEqualHandler();
        handlers[OpCode.Compare.CMP_GT] = new CompareGreaterThenHandler();
        handlers[OpCode.Compare.CMP_GE] = new CompareGreaterThenOrEqualHandler();
    }

    public static void getOperandStackHandler(Handler[] handlers){
        handlers[OpCode.OperandStack.PUSH] = new PushHandler();
        handlers[OpCode.OperandStack.POP] = new PopHandler();
    }

}
