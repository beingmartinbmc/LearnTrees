package trees.gui.redBlack;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import trees.gui.bst.BstVisualiser;
import trees.implementation.redBlack.RedBlackTree;

@trees.implementation.Author(name = "Ankit Sharma", date = "20 Nov 2018")
public class RBVisualiser extends BstVisualiser {
    @Override
    public void start(Stage primaryStage){
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        BorderPane pane = new BorderPane();
        RBPane view = new RBPane(tree);
        setPane(pane, view, tree);
        setStage(pane, primaryStage, "RedBlackTree Visualisation");
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"This is a RedBlackTree Visualiser created by Ankit Sharma. This demonstrates the operations of insertion and deletion.\n\n" +
                "Insert button inserts a node, delete button deletes a node", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }
}
