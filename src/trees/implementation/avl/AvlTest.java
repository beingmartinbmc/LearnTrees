package trees.implementation.avl;

public class AvlTest {
    public static void main(String ...s){
        AVL<Integer> tree = new AVL<>();
        tree.insert(1);tree.insert(2);
        tree.insert(3);tree.insert(4);
        printTree(tree);
        tree.delete(2);
        printTree(tree);
    }

    private static void printTree(AVL tree){
        System.out.println("\nInorder is:");
        tree.inorder();
        System.out.println("\nPreorder is:");
        tree.preorder();
        System.out.println("\nPostorder is:");
        tree.postorder();
    }
}
