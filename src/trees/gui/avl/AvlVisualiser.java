package trees.gui.avl;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import trees.gui.bst.BstVisualiser;
import trees.implementation.avl.AVL;

@trees.implementation.Author(name = "Ankit Sharma", date = "12 Oct 2018")
public class AvlVisualiser extends BstVisualiser {
    private static AVL<Integer> tree;
    private static AvlPane view;

    @Override
    public void start(Stage primaryStage){
        tree = new AVL<>();
        BorderPane pane = new BorderPane();
        view = new AvlPane(tree);
        setPane(pane, view, tree);
        setStage(pane, primaryStage, "AVL Visualisation");
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"This is a AVL Visualiser created by Ankit Sharma. This demonstrates the operations of insertion and deletion.\n\n" +
                "Insert button inserts a node, delete button deletes a node.", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }
}
