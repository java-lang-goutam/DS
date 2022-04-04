package linkedlist;

public class SwapNodes {

    public ListNode swapNodes(final ListNode head, final int k) {
        final int size = size(head);
        if (size < 2) return head;
        int fNode = Math.min(k - 1, size - k);
        int sNode = Math.max(k - 1, size - k);

        if (fNode == 0) {
            ListNode headNext = head.next, temp = head;
            while (temp.next.next != null) temp = temp.next;
            ListNode newHead = temp.next;
            if (temp != head) temp.next = head;
            head.next = null;
            if (newHead != headNext) newHead.next = headNext;
            else newHead.next = head;
            return newHead;
        }

        int i = 1;
        ListNode firstPrevious = head;
        while (i != fNode) {
            firstPrevious = firstPrevious.next;
            i++;
        }

        ListNode secondPrevious = firstPrevious;
        while (i != sNode) {
            secondPrevious = secondPrevious.next;
            i++;
        }

        ListNode first = firstPrevious.next;
        ListNode second = secondPrevious.next;
        ListNode firstNext = first.next;
        ListNode secondNext = second.next;

        firstPrevious.next = second;

        if (firstNext == second) {
            second.next = first;
        } else {
            second.next = firstNext;
            secondPrevious.next = first;
        }
        first.next = secondNext;
        return head;
    }

    private int size(ListNode head) {
        int size = 0;
        while (head != null) {
            head = head.next;
            size++;
        }
        return size;
    }

    public static void main(String[] args) {
        final int[] arr = {1,2};
        final ListNode head = ListUtil.toList(arr);
        ListUtil.print(new SwapNodes().swapNodes(head, 1));
    }
}
