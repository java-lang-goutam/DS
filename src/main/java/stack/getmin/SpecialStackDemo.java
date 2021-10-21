package stack.getmin;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 * Design a Data Structure SpecialStack that supports all the stack operations
 * like push(), pop(), isEmpty(), isFull() and an additional operation getMin()
 * which should return minimum element from the SpecialStack. All these
 * operations of SpecialStack must be O(1). To implement SpecialStack, you
 * should only use standard Stack data structure and no other data structure
 * like arrays, list, . etc.
 *
 * https://www.geeksforgeeks.org/design-and-implement-special-stack-data-structure/
 */
class SpecialStack extends Stack<Integer> {

    final Stack<Integer> auxStack = new Stack<>();

    public int getMin() {
        return auxStack.peek();
    }

    public Integer pop() {
        auxStack.pop();
        return super.pop();
    }

    public Integer push(final Integer newElement) {

        if (auxStack.size() == 0) {
            auxStack.push(newElement);
            return super.push(newElement);
        }

        final int topElement = auxStack.peek();

        if (topElement < newElement) {
            auxStack.push(topElement);
        } else {
            auxStack.push(newElement);
        }
        return super.push(newElement);
    }

    public void printStack() {
        System.out.println(super.toString());
        System.out.println(auxStack.toString());
    }
}

public class SpecialStackDemo {
    public static void main(String[] args) {

        SpecialStack s = new SpecialStack();
        s.push(10);
        s.push(20);
        s.push(30);
        s.printStack();
        System.out.println(s.getMin());
        s.push(5);
        s.printStack();
        System.out.println(s.getMin());

    }
}
