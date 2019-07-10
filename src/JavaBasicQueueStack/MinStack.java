package JavaBasicQueueStack;

import java.util.Stack;

// *Leetcode* #155
/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.\
*/

// *Thoughts*
//one stack, customize a class, use value to represent normal value, use min to represent current min value.
//A trick in the push, we cannot use a global min value to record, in stead, we should always compare the cur value and peek min value each time.

class IntMinPair {
    Integer value;
    Integer min;
    
    IntMinPair(int val, int min) {
        this.value = val;
        this.min = min;
    }
}

class MinStack {
    private Stack<IntMinPair> stack;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<IntMinPair>();
    }
    
    public void push(int x){
        int min;
        
        if (stack.empty() || x < stack.peek().min)
            min = x;
        else 
            min = stack.peek().min;
        
        IntMinPair intput = new IntMinPair(x, min);
        stack.push(intput);
    }
    
    public void pop() {
        if (!stack.empty())
            stack.pop();
    }
    
    public int top() {
        return stack.peek().value;
    }
    
    public int getMin() {
        return stack.peek().min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */