package trees.gui.bst;

import trees.implementation.bst.BST;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.ArrayList;


@trees.implementation.Author(name = "Ankit Sharma", date = "12 Oct 2018")
public class BstVisualiser extends Application {
    private static ArrayList<Integer> nodes = new ArrayList<>();
    @Override
    public void start(Stage primaryStage){
        BST<Integer> tree = new BST<>();
        BorderPane pane = new BorderPane();
        BstPane view = new BstPane(tree);
        setPane(pane, view, tree);
        setStage(pane, primaryStage, "BST Visualisation");
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"This is a BST Visualiser created by Ankit Sharma. This demonstrates the operations of insertion and deletion.\n\n" +
                "Insert button inserts a node, delete button deletes a node.", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }

    public void setStage(BorderPane pane, Stage primaryStage, String title){
        Scene scene = new Scene(pane, 500,500);
        primaryStage.setTitle(title);
        primaryStage.getIcons().add(new Image("file:data/tree.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setPane(BorderPane pane, BstPane view, BST<Integer> tree){
        pane.setCenter(view);
        TextField textField = new TextField();
        textField.setPrefColumnCount(3);
        textField.setAlignment(Pos.BASELINE_RIGHT);
        Button insert = new Button("Insert");
        Button delete = new Button("Delete");
        addFunctionalities(textField, insert, delete, tree, view);
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter a value"), textField, insert, delete);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        pane.setBottom(hBox);
    }

    public void addFunctionalities(TextField textField, Button insert, Button delete, BST<Integer> tree, BstPane view){
        insert.setOnAction(e->{
            if(textField.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered anything!", ButtonType.OK);
                alert.getDialogPane().setMinHeight(80);
                alert.show();
            }
            else {
                int key = Integer.parseInt(textField.getText());
                nodes.add(key);
                if (tree.search(key)) {
                    view.displayTree();
                    view.setStatus(key + " is already present!");
                } else {
                    tree.insert(key);
                    view.displayTree();
                    view.setStatus(key + " is inserted!");
                }
                textField.clear();
            }
        });

        delete.setOnAction(e->{
            int key = Integer.parseInt(textField.getText());
            if(!tree.search(key)){
                view.displayTree();
                view.setStatus(key +" is not present!");
            }
            else{
                tree.delete(key);
                view.displayTree();
                view.setStatus(key+" is replaced by its predecessor and is deleted!");
            }
            textField.clear();
        });
    }
}
