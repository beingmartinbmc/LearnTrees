package trees.implementation.bst;

import trees.implementation.Author;
import trees.implementation.TreeNode;

import java.util.ArrayList;

@Author(name = "Ankit Sharma", date = "12 Oct 2018")
public class Test {
    public static void main(String [] args){
        BST<String> bst = new BST<>();
        bst.insert("Ankit");
        bst.insert("Max");
        bst.insert("Tomar");
        bst.insert("Jani");
        bst.inorder();
        System.out.println();
        ArrayList<TreeNode<String>> path = bst.path("Jani");
        for(TreeNode<String> w : path)
            System.out.print(w.element+" ");
        System.out.println();
        System.out.println("Is my dog max in this Tree?" +bst.search("Max"));
        bst.delete("Max");
        bst.inorder();
    }
}
