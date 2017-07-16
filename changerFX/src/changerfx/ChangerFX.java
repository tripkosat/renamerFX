/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package changerfx;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author adys
 */
public class ChangerFX extends Application {

    private Stage window;

    private Button uppertolower;
    private Button lowertoupper;
    private Button capitalize;
    private Button undo;
    private Button convert;
    private Button folder;
    private Button toEnglishCharacters;
    private CheckBox changeFiles;
    private CheckBox changeDirectories;
    private RadioButton replaceaword;
    private RadioButton startword;
    private RadioButton endword;
    private RadioButton numberedend;
    private RadioButton numberedstart;
    private Label path;
    private TextField addtostart;
    private TextField addtoend;
    private TextField newword;
    private TextField oldword;
    private ListView<String> vlist;
    private ToggleGroup group;
    private String filename;
    private Resizer r;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        //new objects
        r = new Resizer();
        filename = new String();

        convert = new Button("Convert");
        folder = new Button("Select Folder");
        undo = new Button("Undo All");
        uppertolower = new Button("UPPER to lower");
        lowertoupper = new Button("lower to UPPER");
        capitalize = new Button("Capitalize");
        toEnglishCharacters = new Button("To Greeklish");
        path = new Label();

        vlist = new ListView<>();

        addtostart = new TextField();
        addtoend = new TextField();
        newword = new TextField();
        oldword = new TextField();

        replaceaword = new RadioButton("Replace a Word");
        startword = new RadioButton("Add a Word to the Start");
        endword = new RadioButton("Add a Word to the End");
        numberedend = new RadioButton("Add Sorted Numbers to the End");
        numberedstart = new RadioButton("Add Sorted Numbers to the Start");

        group = new ToggleGroup();
        changeFiles = new CheckBox("Files");
        changeDirectories = new CheckBox("Directories");

        window.setTitle("The Changer");
        vlist.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        vlist.setMinWidth(570);
        vlist.setMinHeight(780);

        //Buttons' Actions
        uppertolower.setOnAction(e -> {
            r.UpperToLower(filename, whichKind());
            refresh();
        });

        lowertoupper.setOnAction(e -> {
            r.LowerToUpper(filename, whichKind());
            refresh();
        });

        capitalize.setOnAction(e -> {
            r.Capitalize(filename, whichKind());
            refresh();
        });

        toEnglishCharacters.setOnAction(e -> {
            r.greekToGreeklish(filename, whichKind());
            refresh();
        });
        //Disable all buttons
        setDisableVisualElements(true);

        changeFiles.setSelected(true);
        changeDirectories.setSelected(true);

        //toogle group
        replaceaword.setToggleGroup(group);
        startword.setToggleGroup(group);
        endword.setToggleGroup(group);
        numberedend.setToggleGroup(group);
        numberedstart.setToggleGroup(group);
        newword.setPromptText("the new word");
        oldword.setPromptText("word for replacement");

        folder.setOnAction(e -> SelectFolderActionPerformed());
        convert.setOnAction(e -> convert());
        undo.setOnAction(e -> {
            r.undoAll(filename, whichKind());
            refresh();
        });

        //Layouts
        VBox rightLayout = new VBox();
        VBox leftLayout = new VBox();
        HBox finalLayout = new HBox();
        rightLayout.setMinWidth(200);

        finalLayout.setSpacing(10);
        rightLayout.setSpacing(10);
        finalLayout.setPadding(new Insets(10, 10, 10, 10));
        rightLayout.getChildren().addAll(uppertolower, lowertoupper, capitalize, toEnglishCharacters,
                replaceaword, oldword, newword, startword, addtostart,
                endword, addtoend, numberedstart, numberedend, changeFiles, changeDirectories, folder,
                path, convert, new Label(), new Label(), undo);
        leftLayout.getChildren().add(vlist);

        finalLayout.getChildren().addAll(rightLayout, leftLayout);

        Scene scene = new Scene(finalLayout, 800, 800);
        window.setScene(scene);
        window.show();
    }

    private int whichKind() {
        if (changeDirectories.isSelected() && changeFiles.isSelected()) {
            return 3;
        } else if (changeFiles.isSelected()) {
            return 1;
        } else if (changeDirectories.isSelected()) {
            return 2;
        }
        return 0;
    }

    private void convert() {
        if (replaceaword.isSelected()) {
            r.removeAWord(filename, oldword.getText(), newword.getText(), whichKind());
        } else if (startword.isSelected()) {
            r.addAWord(filename, addtostart.getText(), true, whichKind());
        } else if (endword.isSelected()) {
            r.addAWord(filename, addtoend.getText(), false, whichKind());
        } else if (numberedstart.isSelected()) {
            r.addANumber(filename, true, whichKind());
        } else if (numberedend.isSelected()) {
            r.addANumber(filename, false, whichKind());
        }
        refresh();
    }

    private void SelectFolderActionPerformed() {
        DirectoryChooser fileChooser = new DirectoryChooser();
        File tmp = fileChooser.showDialog(null);
        if (tmp == null) {
        } else {
            path.setText(tmp.getAbsolutePath());

            setDisableVisualElements(false);

            filename = path.getText();
            refresh();
            System.out.println(filename);
            r.firstBackup(filename);
        }
    }

    private void setDisableVisualElements(boolean x) {
        convert.setDisable(x);
        uppertolower.setDisable(x);
        lowertoupper.setDisable(x);
        capitalize.setDisable(x);
        undo.setDisable(x);
        replaceaword.setDisable(x);
        startword.setDisable(x);
        endword.setDisable(x);
        newword.setDisable(x);
        oldword.setDisable(x);
        addtoend.setDisable(x);
        addtostart.setDisable(x);
        changeFiles.setDisable(x);
        changeDirectories.setDisable(x);
        toEnglishCharacters.setDisable(x);
        numberedend.setDisable(x);
        numberedstart.setDisable(x);
    }

    private void refresh() {
        vlist.getItems().clear();
        if (!path.getText().equals("")) {
            r.arrayOfFileNames(path.getText()).stream().forEach((x) -> {
                vlist.getItems().add(x);
            });
        }
    }

}
