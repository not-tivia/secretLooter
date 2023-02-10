package scripts.aLooter.gui;

import com.allatori.annotations.DoNotRename;
import com.google.gson.Gson;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.json.Property;
import org.tribot.api.General;
import org.tribot.script.sdk.util.ScriptSettings;
import org.tribot.util.Util;
import scripts.aLooter.gui.AbstractGUIController;
import scripts.aLooter.gui.GUISettings;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.nio.file.Files.readString;
import static java.nio.file.Files.writeString;

public class Controller extends AbstractGUIController {

    private final File directory = new File(Util.getWorkingDirectory() + File.separator + "Tivia" + File.separator + "AIO Looter" + File.separator + "saves");

    private static ScriptSettings.ScriptSettingsBuilder builder() {
        return null;
    }

    private ObservableList<String> availableChoices = FXCollections.observableArrayList("apples", "oranges");


    private GUISettings settings = new GUISettings();

    @FXML
    @DoNotRename
    private MenuItem MenuLoad;

    @FXML @DoNotRename
    private MenuItem MenuSave;

    @FXML @DoNotRename
    private MenuItem MenuQuit;

    @FXML @DoNotRename
    private JFXToggleButton LootItemToggleButton;

    @FXML @DoNotRename
    private Spinner<Integer> LootItemSpinner;

    @FXML @DoNotRename
    private TextField LootItemTextField;

    @FXML @DoNotRename
    private JFXToggleButton ActiveRoamToggleButton;

    @FXML @DoNotRename
    private JFXToggleButton ReturnToIdleToggleButton;

    @FXML @DoNotRename
    private JFXCheckBox CustomRoamTimeCheckBox;

    @FXML @DoNotRename
    private Spinner<Integer> CustomRoamTimeSpinner;

    @FXML @DoNotRename
    private JFXCheckBox EmergencyTeleportCheckBox;

    @FXML @DoNotRename
    private JFXCheckBox CustomIdleAreaTimerCheckBox;

    @FXML @DoNotRename
    private Spinner<Integer> CustomIdleAreaTimerSpinner;

    @FXML @DoNotRename
    private Button StartScriptButton;

    @FXML @DoNotRename
    private JFXCheckBox LootItemCheckBox;

    @FXML @DoNotRename
    private JFXCheckBox SpamClickCheckBox;

    @FXML @DoNotRename
    private JFXCheckBox BankOverValueCheckBox;

    @FXML @DoNotRename
    private ChoiceBox<String> locationChoiceBox;

    @FXML @DoNotRename
    private Spinner<Integer> CustomLootingRadiusSpinner;

    @FXML @DoNotRename
    private Spinner<Integer> CustomIdleRadiusSpinner;

    @FXML @DoNotRename
    private Button SetIdleAreaButton;

    @FXML @DoNotRename
    private Button SetLootingAreaButton;

    @FXML @DoNotRename
    private CheckBox RequireExactCheckBox;

    @FXML @DoNotRename
    private CheckBox AttackNPCCheckBox;

    @FXML @DoNotRename
    private CheckBox TeleportCheckBox;

    @FXML @DoNotRename
    private CheckBox UseFoodCheckBox;

    @FXML @DoNotRename
    private CheckBox WorldHopCheckBox;

    @FXML @DoNotRename
    private CheckBox StopOnDeathCheckBox;

    @FXML @DoNotRename
    private CheckBox ItemIsObjectCheckBox;

    @FXML @DoNotRename
    private Spinner<Integer> BankOverSpinner;

    @FXML @DoNotRename
    void setIdleAreaPressed() {
        openItemPressed();
    }

    @FXML @DoNotRename
    void setLootingAreaPressed() {
        saveItemPressed();
    }

    @FXML @DoNotRename
    void startScriptButtonPressed() {
        /*
        if (lootAnyItemOverCheckBox.isSelected()){
            //  settings.lootAnyItemOverCheckBox.setValue(true);
        }
        if (lootAnyItemOverSpinner.getValue()!=null){
            //  settings.lootAnyItemOverSpinner.toString();
        }

         */
        if (LootItemToggleButton.isSelected()){
            General.println("TOGGLE BUTTON IS SELECTED");
        }
        this.getGUI().close();
    }



    /*
         Creating a filechosoer when button is pressed;
          setting extension type to .json
        If the file name is not an empty string open it for saving.
          */
    private void saveItemPressed() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialFileName("last.json");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("json Files", "*.json"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        fileChooser.setInitialDirectory(directory);
        Window primaryStage = null;
        File selectedFile = fileChooser.showSaveDialog(primaryStage);
        if (selectedFile != null) {
            General.println(selectedFile.getName() + "!!!");
            if (!directory.exists())
                directory.mkdirs();
            try {
                if (ActiveRoamToggleButton.isSelected()) {
                    settings.ActiveRoamToggleButton.setValue(true);
                }

                String s = new Gson().toJson(settings);
                writeString(new File(directory, selectedFile.getName()).toPath(), s);
                // writeString(new File(directory, "last.json").toPath(), s);
                General.println("Settings saved successfully to: " + directory);
            } catch (IOException e) {
                General.println("Error attempting to save settings.");
                e.printStackTrace();
            }
        }
    }



    private void openItemPressed() {

        /*
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("json Files", "*.json"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        fileChooser.setInitialDirectory(directory);
        Window primaryStage = null;
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            General.println(selectedFile.getName());
            General.println("Directory name: " +directory);
            General.println("Filename: " + selectedFile);

        }

    }

         */

        /*
        try {
            //String s = readString(new File(directory, selectedFile.getName()).toPath());
            // Read the settings


            String s = readString(new File(directory, "last.json").toPath());
            settings = new Gson().fromJson(s, GUISettings.class);
            General.println("Settings loaded successfully from: " + directory);



        } catch (Exception e) {
            General.println("Error attempting to load settings.");
            e.printStackTrace();
        }
         */

    }


    private void initSpinner() {
        // First tab

        LootItemSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000));
        BankOverSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000));
        CustomRoamTimeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000));
        CustomIdleAreaTimerSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000));
        // Second tab
        CustomIdleRadiusSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000));
        CustomLootingRadiusSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000));

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //We initalize our spinners right away to stop any fxml issues
        initSpinner();



        /*
        First we check if our save directory exists;
         if it does then we load the last save by default
         */
        if (directory.exists()) {
          //  General.println("Opening our last save from: " + directory + " on script start");
            try {
                // We can read the default settings here

                String s = readString(new File(directory, "last.json").toPath());
                settings = new Gson().fromJson(s, GUISettings.class);

                General.println("loaded");
                ScriptSettings.builder().build().

            } catch (Exception e) {
                e.printStackTrace();
                General.println("failed");
            }
        }




        // Bidrectional bindings to make sure our settings class and our GUI always share the same values
        LootItemToggleButton.selectedProperty().bindBidirectional(settings.lootItemToggleButton);


        LootItemSpinner.getValueFactory().valueProperty().bindBidirectional(settings.LootItemSpinner);
        LootItemSpinner.getValueFactory().setValue(1000);

        LootItemTextField.textProperty().bindBidirectional(settings.lootItemTextField);


        BankOverSpinner.getValueFactory().valueProperty().bindBidirectional(settings.BankOverSpinner);
        BankOverSpinner.getValueFactory().setValue(75000);

        ActiveRoamToggleButton.selectedProperty().bindBidirectional(settings.ActiveRoamToggleButton);
        ReturnToIdleToggleButton.selectedProperty().bindBidirectional(settings.ReturnToIdleToggleButton);

        CustomRoamTimeCheckBox.selectedProperty().bindBidirectional(settings.CustomRoamTimeCheckBox);
        CustomRoamTimeSpinner.getValueFactory().valueProperty().bindBidirectional(settings.CustomRoamTimeSpinner);
        CustomRoamTimeSpinner.getValueFactory().setValue(35000);

        EmergencyTeleportCheckBox.selectedProperty().bindBidirectional(settings.EmergencyTeleportCheckBox);
        CustomIdleAreaTimerCheckBox.selectedProperty().bindBidirectional(settings.CustomIdleAreaTimerCheckBox);

        CustomIdleAreaTimerSpinner.getValueFactory().valueProperty().bindBidirectional(settings.CustomIdleRadiusSpinner);
        CustomIdleAreaTimerSpinner.getValueFactory().setValue(6500);

        LootItemCheckBox.selectedProperty().bindBidirectional(settings.LootItemCheckBox);
        SpamClickCheckBox.selectedProperty().bindBidirectional(settings.SpamClickCheckBox);
        BankOverValueCheckBox.selectedProperty().bindBidirectional(settings.BankOverValueCheckBox);


        // Second tab

        locationChoiceBox.setItems(FXCollections.observableArrayList(availableChoices));
        locationChoiceBox.valueProperty().bindBidirectional(settings.locationChoiceBox);

        CustomLootingRadiusSpinner.getValueFactory().valueProperty().bindBidirectional(settings.CustomLootingRadiusSpinner);
        CustomLootingRadiusSpinner.getValueFactory().setValue(3);

        CustomIdleRadiusSpinner.getValueFactory().valueProperty().bindBidirectional(settings.CustomIdleRadiusSpinner);
        CustomIdleRadiusSpinner.getValueFactory().setValue(5);


        // Third tab

        RequireExactCheckBox.selectedProperty().bindBidirectional(settings.RequireExactCheckBox);
        AttackNPCCheckBox.selectedProperty().bindBidirectional(settings.AttackNPCCheckBox);
        UseFoodCheckBox.selectedProperty().bindBidirectional(settings.UseFoodCheckBox);
        ItemIsObjectCheckBox.selectedProperty().bindBidirectional(settings.ItemIsObjectCheckBox);
        WorldHopCheckBox.selectedProperty().bindBidirectional(settings.WorldHopCheckBox);
        TeleportCheckBox.selectedProperty().bindBidirectional(settings.TeleportCheckBox);
        StopOnDeathCheckBox.selectedProperty().bindBidirectional(settings.StopOnDeathCheckBox);



    }

    /*
    @FXML @DoNotRename
    void saveSettingsButtonPressed() {

        if (!directory.exists())
            directory.mkdirs();
        try {
            possibleItems.add("lol");
            itemsToLoot.add("lol");
            if (lootAnyItemOverCheckBox.isSelected()){
                settings.lootAnyItemOverCheckBox.setValue(true);
            }
            if (lootAnyItemOverSpinner.getValue()!=null){
                settings.lootAnyItemOverSpinner.toString();
            }
            //LOOT ANY ITEM OVER SPINNER

            if (minimumStackCheckBox.isSelected()){
                settings.minimumStackCheckBox.set(true);
            }

            if (eatFoodForLootCheckBox.isSelected()){
                settings.eatFoodForLootCheckBox.setValue(true);
            }

            if (eatFoodForLootCheckBox.isSelected()){
                settings.eatFoodForLootCheckBox.setValue(true);
            }

            if (dropItemsWorthLessCheckBox.isSelected()){
                settings.dropItemsWorthLessCheckBox.setValue(true);
            }

            if (walkAroundAreaCheckBox.isSelected()){
                settings.walkAroundAreaCheckBox.setValue(true);
            }
            //ADD WALK AROUND AREA SPINNER



            // Write the settings
            String s = new Gson().toJson(settings);
            writeString(new File(directory, "last.json").toPath(), s);
            General.println("Settings saved successfully to: " + directory);
        } catch (IOException e) {
            General.println("Error attempting to save settings.");
            e.printStackTrace();
        }
    }

     */


    /*
    Old Initalize stuff

       // CustomLootingRadiusSpinner.getValueFactory().valueProperty().bindBidirectional(settings.CustomLootingRadiusSpinner);
       // CustomLootingRadiusSpinner.getValueFactory().setValue(3);
        //foodchoice.setItems(FXCollections.observableArrayList(foodChoiceArray));
        /*
        LootItemCheckBox.selectedProperty().bindBidirectional(settings.LootItemCheckBox);
        SpamClickCheckBox.selectedProperty().bindBidirectional(settings.SpamClickCheckBox);
        BankOverValueCheckBox.selectedProperty().bindBidirectional(settings.BankOverValueCheckBox);
        locationChoiceBox.valueProperty().bindBidirectional(settings.locationChoiceBox);

        CustomLootingRadiusSpinner.getValueFactory().valueProperty().bindBidirectional(settings.CustomLootingRadiusSpinner);
        CustomLootingRadiusSpinner.getValueFactory().setValue(10);

        CustomIdleRadiusSpinner.getValueFactory().valueProperty().bindBidirectional(settings.CustomIdleRadiusSpinner);
        CustomIdleRadiusSpinner.getValueFactory().setValue(10);


        RequireExactCheckBox.selectedProperty().bindBidirectional(settings.RequireExactCheckBox);
        AttackNPCCheckBox.selectedProperty().bindBidirectional(settings.AttackNPCCheckBox);
        TeleportCheckBox.selectedProperty().bindBidirectional(settings.TeleportCheckBox);
        UseFoodCheckBox.selectedProperty().bindBidirectional(settings.UseFoodCheckBox);
        WorldHopCheckBox.selectedProperty().bindBidirectional(settings.WorldHopCheckBox);
        StopOnDeathCheckBox.selectedProperty().bindBidirectional(settings.StopOnDeathCheckBox);
        ItemIsObjectCheckBox.selectedProperty().bindBidirectional(settings.ItemIsObjectCheckBox);

         */

/*

        BankOverSpinner.getValueFactory().valueProperty().bindBidirectional(settings.BankOverSpinner);
        ActiveRoamToggleButton.selectedProperty().bindBidirectional(settings.ActiveRoamToggleButton);
        ReturnToIdleToggleButton.selectedProperty().bindBidirectional(settings.ReturnToIdleToggleButton);
        CustomRoamTimeCheckBox.selectedProperty().bindBidirectional(settings.CustomRoamTimeCheckBox);
       // CustomRoamTimeSpinner.getValueFactory().valueProperty().bindBidirectional(settings.CustomRoamTimeSpinner);
        EmergencyTeleportCheckBox.selectedProperty().bindBidirectional(settings.EmergencyTeleportCheckBox);
        CustomIdleAreaTimerCheckBox.selectedProperty().bindBidirectional(settings.CustomIdleAreaTimerCheckBox);
        CustomIdleAreaTimerSpinner.getValueFactory().valueProperty().bindBidirectional(settings.CustomIdleRadiusSpinner);
        LootItemCheckBox.selectedProperty().bindBidirectional(settings.LootItemCheckBox);
        SpamClickCheckBox.selectedProperty().bindBidirectional(settings.SpamClickCheckBox);
        BankOverValueCheckBox.selectedProperty().bindBidirectional(settings.BankOverValueCheckBox);
        locationChoiceBox.valueProperty().bindBidirectional(settings.locationChoiceBox);
        CustomLootingRadiusSpinner.getValueFactory().valueProperty().bindBidirectional(settings.CustomLootingRadiusSpinner);
       CustomIdleRadiusSpinner.getValueFactory().valueProperty().bindBidirectional(settings.CustomIdleRadiusSpinner);
        RequireExactCheckBox.selectedProperty().bindBidirectional(settings.RequireExactCheckBox);
        AttackNPCCheckBox.selectedProperty().bindBidirectional(settings.AttackNPCCheckBox);
        TeleportCheckBox.selectedProperty().bindBidirectional(settings.TeleportCheckBox);
        UseFoodCheckBox.selectedProperty().bindBidirectional(settings.UseFoodCheckBox);
        WorldHopCheckBox.selectedProperty().bindBidirectional(settings.WorldHopCheckBox);
        StopOnDeathCheckBox.selectedProperty().bindBidirectional(settings.StopOnDeathCheckBox);
        ItemIsObjectCheckBox.selectedProperty().bindBidirectional(settings.ItemIsObjectCheckBox);





 */




}
