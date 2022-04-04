package linkedlist;

public class ListUtil {
    public static ListNode toList(final int[] arr) {
        final ListNode head = new ListNode();
        ListNode temp = head;
        for (final int ele : arr) {
            temp.next = new ListNode(ele);
            temp = temp.next;
        }
        return head.next;
    }

    public static void print(ListNode list) {
        while (list != null) {
            System.out.print(list.val + " -> ");
            list = list.next;
        }
        System.out.print("null");
    }
}
