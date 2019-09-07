public class Main {

    public static void main(String[] args) {


        Solution solution = new Solution();

        int[] nums1 = {3, 3, 3};
        int insertVal1 = 0;
        Node head1 = getList(nums1);
        System.out.print("q1:\t\t");
        printList(head1);
        Node ans1 = solution.insert(head1, insertVal1);
        System.out.print("ans1:\t");
        printList(ans1);

        int[] nums2 = {1, 3, 4};
        int insertVal12 = 2;
        Node head2 = getList(nums2);
        System.out.print("q2:\t\t");
        printList(head2);
        Node ans2 = solution.insert(head2, insertVal12);
        System.out.print("ans2:\t");
        printList(ans2);
    }

    private static Node getList(int[] nums){
        int N = nums.length;

        Node[] nodes = new Node[N];
        for(int i = 0; i < N; i++){
            nodes[i] = new Node();
            nodes[i].val = nums[i];
        }

        for(int i = 0; i < N; i++){
            nodes[i].next = nodes[(i + 1) % N];
        }

        return nodes[0];
    }

    private static void printList(Node node){
        Node root = node;
        while(true){
            System.out.print(node.val + "\t");
            node = node.next;
            if(node == root){
                System.out.print("\n");
                return;
            }
        }
    }

}



// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val,Node _next) {
        val = _val;
        next = _next;
    }
}

class Solution {
    public Node insert(Node head, int insertVal) {
        if(head == null){
            head = new Node(insertVal, head);
            return head;
        }

        if(head.next == head){
            head.next = new Node(insertVal, head);
            return head;
        }

        Node node1 = head;
        Node node2 = node1.next;
        while(true){
            // System.out.print(node1.val + "    " + node2.val + "\n");
            if((node1.val <= insertVal && insertVal <= node2.val) ||
                    (node1.val > node2.val && (insertVal <= node2.val || insertVal >= node1.val))){
                node1.next = new Node(insertVal, node2);
                return head;
            }

            node1 = node1.next;
            node2 = node2.next;
            if(node1 == head){
                break;
            }
        }

        node1.next = new Node(insertVal, node2);
        return head;
    }
}