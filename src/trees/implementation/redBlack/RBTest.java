package trees.implementation.redBlack;

public class RBTest {
    public static void main(String ...s){
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(1);tree.insert(2);
        tree.insert(3);tree.insert(4);
        printTree(tree);
        tree.delete(2);
        printTree(tree);
    }

    private static void printTree(RedBlackTree tree){
        System.out.println("\nInorder is:");
        tree.inorder();
    }
}
