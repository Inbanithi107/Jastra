package io.github.inbanithi.jastra.core;

import java.util.Stack;

public class OperandStack {

    private final Stack<Long> stack;

    public OperandStack() {
        this.stack = new Stack<>();
    }

    public void push(long val){
        stack.push(val);
    }

    public long pop(){
        return stack.pop();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

}
