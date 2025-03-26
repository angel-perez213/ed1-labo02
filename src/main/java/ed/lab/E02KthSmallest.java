package ed.lab;

public class E02KthSmallest {

    private int count = 0;
    private int result = -1;

    public int kthSmallest(TreeNode<Integer> root, int k) {
        inOrder(root, k);
        return result;
    }

    private void inOrder(TreeNode<Integer> node, int k) {
        if (node == null) {
            return;
        }

        inOrder(node.left, k);

        count++;
        if (count == k) {
            result = node.value;
            return;
        }

        inOrder(node.right, k);
    }
}
