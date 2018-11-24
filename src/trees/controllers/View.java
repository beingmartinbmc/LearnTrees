package trees.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import trees.gui.avl.AvlVisualiser;
import trees.gui.bst.BstVisualiser;
import trees.gui.redBlack.RBVisualiser;

public class View {
    @FXML
    private Button avl;
    @FXML
    private Button rb;
    @FXML
    private Button bst;

    @FXML
    private void bstAction(){
        bst.setOnAction(e-> {
            Stage menuStage = new Stage();
            BstVisualiser menu = new BstVisualiser();
            menu.start(menuStage);
            menuStage.show();
        });
    }
    @FXML
    private void avlAction(){
        avl.setOnAction(e-> {
            Stage menuStage = new Stage();
            AvlVisualiser menu = new AvlVisualiser();
            menu.start(menuStage);
            menuStage.show();
        });
    }
    @FXML
    private void rbAction(){
        rb.setOnAction(e-> {
            Stage menuStage = new Stage();
            RBVisualiser menu = new RBVisualiser();
            menu.start(menuStage);
            menuStage.show();
        });
    }

}

