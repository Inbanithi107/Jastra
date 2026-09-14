package io.github.inbanithi.jastra.handler;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.specification.vm.Frame;

public class JumpHandler extends Handler {
    @Override
    public void execute(JastraVirtualMachine vm, Frame frame) {
        int offset = frame.fetchInt();
        frame.pc+=offset;
    }
}
