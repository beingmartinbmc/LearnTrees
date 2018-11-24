package trees.implementation.redBlack;

import trees.implementation.TreeNode;
import trees.implementation.bst.BST;

import java.util.ArrayList;

public class RedBlackTree<E extends Comparable<E>> extends BST<E> {

    public RedBlackTree() {
    }

    public RedBlackTree(E[] elements) {
        super(elements);
    }

    @Override
    public TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override
    public boolean insert(E e) {
        boolean successful = super.insert(e);
        if (!successful)
            return false;
        else {
            ensureRBTree(e);
        }

        return true;
    }

    private void ensureRBTree(E e) {
        ArrayList<TreeNode<E>> path = path(e);
        int i = path.size() - 1;
        TreeNode<E> u = (path.get(i));
        TreeNode<E> v = (u == root) ? null : (path.get(i - 1));
        u.setRed();
        if (u == root)
            u.setBlack();
        else if (v != null & v.isRed())
            fixDoubleRed(u, v, path, i);
    }

    private void fixDoubleRed(TreeNode<E> u, TreeNode<E> v,
                              ArrayList<TreeNode<E>> path, int i) {
        TreeNode<E> w = (path.get(i - 2));
        TreeNode<E> parentOfw = (w == root) ? null : path.get(i - 3);

        TreeNode<E> x = (w.left == v) ? (w.right) :(w.left);

        if (x == null || x.isBlack()) {
            if (w.left == v && v.left == u) {
                restructureRecolor(u, v, w, w, parentOfw);

                w.left = v.right; // v.right is y3 in Figure 48.6
                v.right = w;
            }
            else if (w.left == v && v.right == u) {
                restructureRecolor(v, u, w, w, parentOfw);
                v.right = u.left;
                w.left = u.right;
                u.left = v;
                u.right = w;
            }
            else if (w.right == v && v.right == u) {
                restructureRecolor(w, v, u, w, parentOfw);
                w.right = v.left;
                v.left = w;
            }
            else {
                restructureRecolor(w, u, v, w, parentOfw);
                w.right = u.left;
                v.left = u.right;
                u.left = w;
                u.right = v;
            }
        }
        else {
            w.setRed();
            u.setRed();
            ((w.left)).setBlack();
            ((w.right)).setBlack();

            if (w == root) {
                w.setBlack();
            }
            else if (parentOfw != null && (parentOfw).isRed()) {
                u = w;
                v = parentOfw;
                fixDoubleRed(u, v, path, i - 2);
            }
        }
    }

    private void restructureRecolor(TreeNode<E> a, TreeNode<E> b,
                                    TreeNode<E> c, TreeNode<E> w, TreeNode<E> parentOfw) {
        if (parentOfw == null)
            root = b;
        else if (parentOfw.left == w)
            parentOfw.left = b;
        else
            parentOfw.right = b;

        b.setBlack();
        a.setRed();
        c.setRed();
    }

    @Override
    public boolean delete(E e) {
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else
                break;
        }

        if (current == null)
            return false;

        java.util.ArrayList<TreeNode<E>> path;
        if (current.left != null && current.right != null) {
            TreeNode<E> rightMost = current.left;
            while (rightMost.right != null) {
                rightMost = rightMost.right;
            }
            path = path(rightMost.element);
            current.element = rightMost.element;
        }
        else
            path = path(e);
        deleteLastNodeInPath(path);

        size--;
        return true;
    }

    public void deleteLastNodeInPath(ArrayList<TreeNode<E>> path) {
        int i = path.size() - 1;
        TreeNode<E> u = (path.get(i));
        TreeNode<E> parentOfu = (u == root) ? null : (path.get(i - 1));
        TreeNode<E> grandparentOfu = (parentOfu == null ||
                parentOfu == root) ? null : (path.get(i - 2));
        TreeNode<E> childOfu = (u.left == null) ? (u.right) :(u.left);

        // Delete node u. Connect childOfu with parentOfu
        connectNewParent(parentOfu, u, childOfu);

        // Recolor the nodes and fix double black if needed
        if (childOfu == root || u.isRed())
            return; // Done if childOfu is root or if u is red 
        else if (childOfu != null && childOfu.isRed())
            childOfu.setBlack(); // Set it black, done
        else // u is black, childOfu is null or black
            // Fix double black on parentOfu
            fixDoubleBlack(grandparentOfu, parentOfu, childOfu, path, i);
    }

    private void fixDoubleBlack(
            TreeNode<E> grandparent, TreeNode<E> parent,
            TreeNode<E> db, ArrayList<TreeNode<E>> path, int i) {
        // Obtain y, y1, and y2
        TreeNode<E> y = (parent.right == db) ? (parent.left) : (parent.right);
        TreeNode<E> y1 = (y.left);
        TreeNode<E> y2 = (y.right);

        if (y.isBlack() && y1 != null && y1.isRed()) {
            if (parent.right == db) {
                // Case 1.1: y is a left black sibling and y1 is red
                connectNewParent(grandparent, parent, y);
                recolor(parent, y, y1); // Adjust colors

                // Adjust child links
                parent.left = y.right;
                y.right = parent;
            }
            else {
                // Case 1.3: y is a right black sibling and y1 is red        
                connectNewParent(grandparent, parent, y1);
                recolor(parent, y1, y); // Adjust colors

                // Adjust child links
                parent.right = y1.left;
                y.left = y1.right;
                y1.left = parent;
                y1.right = y;
            }
        }
        else if (y.isBlack() && y2 != null && y2.isRed()) {
            if (parent.right == db) {
                // Case 1.2: y is a left black sibling and y2 is red
                connectNewParent(grandparent, parent, y2);
                recolor(parent, y2, y); // Adjust colors

                // Adjust child links
                y.right = y2.left;
                parent.left = y2.right;
                y2.left = y;
                y2.right = parent;
            }
            else {
                // Case 1.4: y is a right black sibling and y2 is red        
                connectNewParent(grandparent, parent, y);
                recolor(parent, y, y2); // Adjust colors

                // Adjust child links
                y.left = parent;
                parent.right = y1;
            }
        }
        else if (y.isBlack()) {
            // Case 2: y is black and y's children are black or null
            y.setRed(); // Change y to red
            if (parent.isRed())
                parent.setBlack(); // Done
            else if (parent != root) {
                // Propagate double black to the parent node
                // Fix new appearance of double black recursively
                db = parent;
                parent = grandparent;
                grandparent =
                        (i >= 3) ? (TreeNode<E>)(path.get(i - 3)) : null;
                fixDoubleBlack(grandparent, parent, db, path, i - 1);
            }
        }
        else { // y.isRed()
            if (parent.right == db) {
                parent.left = y2;
                y.right = parent;
            }
            else {
                parent.right = y.left;
                y.left = parent;
            }

            parent.setRed(); // Color parent red
            y.setBlack(); // Color y black
            connectNewParent(grandparent, parent, y); // y is new parent
            fixDoubleBlack(y, parent, db, path, i - 1);
        }
    }

    public boolean getRed(TreeNode<E> e) {
        TreeNode<E> aNode = e;
        boolean b = true;
        if(aNode.isBlack())
        {
            b = false;
        }
        return b;
    }

    private void recolor(TreeNode<E> parent,
                         TreeNode<E> newParent, TreeNode<E> c) {
        if (parent.isRed())
            newParent.setRed();
        else
            newParent.setBlack();

        parent.setBlack();
        c.setBlack();
    }

    private void connectNewParent(TreeNode<E> grandparent,
                                  TreeNode<E> parent, TreeNode<E> newParent) {
        if (parent == root) {
            root = newParent;
            if (root != null)
                newParent.setBlack();
        }
        else if (grandparent.left == parent)
            grandparent.left = newParent;
        else
            grandparent.right = newParent;

    }
}