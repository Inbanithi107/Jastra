package io.github.inbanithi.jastra.loader;

import io.github.inbanithi.jastra.handler.AddHandler;
import io.github.inbanithi.jastra.handler.CallHandler;
import io.github.inbanithi.jastra.handler.DivisionHandler;
import io.github.inbanithi.jastra.handler.EntryHandler;
import io.github.inbanithi.jastra.handler.HaltHandler;
import io.github.inbanithi.jastra.handler.Handler;
import io.github.inbanithi.jastra.handler.JumpHandler;
import io.github.inbanithi.jastra.handler.JumpIfZeroHandler;
import io.github.inbanithi.jastra.handler.LoadFromConstHandler;
import io.github.inbanithi.jastra.handler.LoadHandler;
import io.github.inbanithi.jastra.handler.MultiplyHandler;
import io.github.inbanithi.jastra.handler.PopStoreRegisterHandler;
import io.github.inbanithi.jastra.handler.PrintHandler;
import io.github.inbanithi.jastra.handler.ReturnHandler;
import io.github.inbanithi.jastra.handler.ReturnVoidHandler;
import io.github.inbanithi.jastra.handler.StoreHandler;
import io.github.inbanithi.jastra.handler.SubtractHandler;
import io.github.inbanithi.jastra.specification.core.ControlInstruction;
import io.github.inbanithi.jastra.specification.core.OpCode;

public class HandlerLoader {

    public static Handler[] getHandlers(){
        Handler[] handlers = new Handler[255];
        handlers[ControlInstruction.ENTRY] = new EntryHandler();
        handlers[OpCode.CALL] = new CallHandler();
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
        handlers[OpCode.RETURN_VOID] = new ReturnVoidHandler();
        handlers[OpCode.RETURN] = new ReturnHandler();
        return handlers;
    }

}
