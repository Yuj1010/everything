//Sort a linked list in O(n log n) time using constant space complexity.


public static ListNode sort(ListNode head){
        if (head == null || head.next == null)
            return head;
        ListNode mid = getMiddle(head); //获取中间结点
        //断开
        ListNode midNext = mid.next;
        mid.next = null;
        //排序，合并
        return mergeTwoLists(sort(head), sort(midNext));
    }
 
 
    /**
     * 获取链表的中间结点,偶数时取中间第一个
     * @param head
     * @return
     */
    public static ListNode getMiddle(ListNode head){
        if (head == null || head.next == null)  //空或只有一个
            return head;
        ListNode fast, slow;    //快慢指针
        fast = slow = head;
        //快2步，慢一步
        while (fast.next != null && fast.next.next != null) {
            //偶数时取第一个
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
 
 
    /**
     * 实现合并两个已经排序的链表
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //特殊情况
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode first = l1.next, second = l2.next;
        ListNode res, newHead;   //结果
        if (l1.val < l2.val){
            newHead = res = l1;
            second = l2;
        }else {
            newHead = res = l2;
            first = l1;
        }
        while (first != null || second != null){
            if (first == null){ //第一条链表没了
                res.next = second;
                return newHead;
            }
            else if (second == null) {    //第二条链表空了
                res.next = first;
                return newHead;
            } else if (first.val < second.val){ //第一个值小
                res.next = first;
                first = first.next;
                res = res.next;
            } else {
                res.next = second;
                second = second.next;
                res = res.next;
            }
 
        }
 
        return newHead;
    }