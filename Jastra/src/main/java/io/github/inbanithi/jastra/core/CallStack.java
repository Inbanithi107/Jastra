package io.github.inbanithi.jastra.core;

import java.util.Stack;

public class CallStack {

    private Stack<Object> stack;

    public CallStack(){
        this.stack = new Stack<>();
    }

    public void push(Object val){
        stack.push(val);
    }

    public Object pop(){
        return stack.pop();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

}
