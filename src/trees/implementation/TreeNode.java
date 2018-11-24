package trees.implementation;

@Author(name = "Ankit Sharma", date = "12 Oct 2018")
public class TreeNode<E extends Comparable<E>> {
    public E element;
    public TreeNode<E> left;
    public TreeNode<E> right;
    public int height = 0;
    private boolean red = true;

    public TreeNode(E e){
        element = e;
    }

    public boolean isRed() {
        return red;
    }

    public boolean isBlack() {
        return !red;
    }

    public void setBlack() {
        red = false;
    }

    public void setRed() {
        red = true;
    }

    int blackHeight;
}