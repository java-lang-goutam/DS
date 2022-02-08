package stack.getmin;

class Node {
    int ele;

    Node next;

    Node(int ele) {
        this.ele = ele;
    }
}

class SStack {

    Node head;
    Node minHead;

    public void push(int ele) {

        Node newEle = new Node(ele);

        if (head == null) {
            head = newEle;
            displayStack();
            return;
        }

        newEle.next = head;
        head = newEle;
        displayStack();
    }

    public int pop() {
        if (head == null) {
            throw new RuntimeException("Nothing to pop");
        }
        Node first = head;
        head = head.next;
        first.next = null;
        displayStack();
        return first.ele;
    }

    public int getMin() {
        return -1;
    }

    public int popMin() {
        return -1;
    }

    public void displayStack() {
        Node node = head;
        System.out.println("Stack :");
        while (node != null) {
            System.out.println(node.ele);
            node = node.next;
        }
        System.out.println();
    }


}

public class SpecialStack2 {
    public static void main(String[] args) {

        SStack stack = new SStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        stack.pop();
        stack.push(5);

    }
}
