import java.util.ArrayList;
import java.util.List;

/**
 * Find all ancestors of LCA. 给出两个节点，打印出两个节点的所有公共祖先节点.
 */
public class Main {

    public static void main(String[] args) {
        Integer[] nums = {3,5,1,6,2,0,8,null,null,7,4};

        Solution solution = new Solution();
        solution.nodes = new TreeNode[nums.length];
        TreeNode root = solution.getTree(0, nums, nums.length);

        TreeNode p = solution.nodes[1];
        TreeNode q = solution.nodes[10];
        List<TreeNode> ans = solution.lowestCommonAncestors(root, p, q);
        for (TreeNode node: ans){
            System.out.print(node.val + "    ");
        }
//        System.out.println(ans.val);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    private List<TreeNode> ans;
    public TreeNode[] nodes;

    public TreeNode getTree(int idx, Integer[] nums, int N){
        if(idx >= N || nums[idx] == null){
            return null;
        }

        TreeNode node = new TreeNode(nums[idx]);
        nodes[idx] = node;
        node.left = getTree(idx * 2 + 1, nums, N);
        node.right = getTree(idx * 2 + 2, nums, N);

        return node;
    }

    public List<TreeNode> lowestCommonAncestors(TreeNode root, TreeNode p, TreeNode q) {
        ans = new ArrayList<>();
        postOrder(root, p, q);
        return ans;
    }

    private int postOrder(TreeNode node, TreeNode p, TreeNode q){
        if(node == null) return 0;

        int left = postOrder(node.left, p, q);
        int right = postOrder(node.right, p, q);
        int current = 0;
        if(node == p || node == q){
            current = 1;
        }
        if(left + right + current == 2){
            ans.add(node);
        }
//        System.out.println(left + right + current);
        return left + right + current;
    }
}

