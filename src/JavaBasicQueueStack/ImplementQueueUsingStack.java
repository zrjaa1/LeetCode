package JavaBasicQueueStack;

import java.util.Stack;

// *LeetCode* #232
//Implement the following operations of a queue using stacks.
//
//push(x) -- Push element x to the back of queue.
//pop() -- Removes the element from in front of queue.
//peek() -- Get the front element.
//empty() -- Return whether the queue is empty.
//Example:
//
//MyQueue queue = new MyQueue();
//
//queue.push(1);
//queue.push(2);  
//queue.peek();  // returns 1
//queue.pop();   // returns 1
//queue.empty(); // returns false
//Notes:
//
//You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
//Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
//You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).

// *Thoughts*
// use 2 queues, one for incoming data, one for poping
public class ImplementQueueUsingStack {
	Stack<Integer> stackIn;
    Stack<Integer> stackOut;
    
    /** Initialize your data structure here. */
    public ImplementQueueUsingStack() {
        stackIn = new Stack<Integer>();
        stackOut = new Stack<Integer>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        stackIn.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        transfer();
        return stackOut.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        transfer();
        return stackOut.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stackIn.empty() && stackOut.empty();
    }
    
    private void transfer() {
        if (stackOut.empty()) {
            while(!stackIn.empty()) {
                stackOut.push(stackIn.pop());
            }   
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
