package scripts.aLooter.versionOne;

import com.allatori.annotations.DoNotRename;
import com.google.gson.Gson;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.tribot.api.General;
import org.tribot.util.Util;
import scripts.aLooter.gui.AbstractGUIController;
import scripts.aLooter.gui.GUISettings;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.nio.file.Files.readString;
import static java.nio.file.Files.writeString;


@DoNotRename
public class ControllerORIGNAL extends AbstractGUIController {

    private final File directory = new File(Util.getWorkingDirectory() + File.separator + "Tivia" + File.separator + "AIO Looter" + File.separator + "saves");


    //create an ObservableList
    private  ObservableList<String> itemsToLoot = FXCollections.observableArrayList();
    private  ObservableList<String> possibleItems = FXCollections.observableArrayList();

    private  ObservableList<String> inventoryItems = FXCollections.observableArrayList();
    private  ObservableList<String> equipmentItems = FXCollections.observableArrayList();

    private ObservableList<String> blacklistedItems = FXCollections.observableArrayList();


    private ObservableList<String> selectedLocation = FXCollections.observableArrayList();
    private ObservableList<String> escapeFromPKersMethod = FXCollections.observableArrayList();
    private ObservableList<String> requiredFood = FXCollections.observableArrayList();
    private ObservableList<String> healingType = FXCollections.observableArrayList();
    private ObservableList<String> lootingMethod = FXCollections.observableArrayList();

    @FXML @DoNotRename
    private ListView<String> possibleItemsListView;

    @FXML @DoNotRename
    private ListView<String> itemsToLootListView;

    @FXML @DoNotRename
    private TextField addItemTextField;

    @FXML @DoNotRename
    private Button addItemButton;

    @FXML @DoNotRename
    private Button addSelectedItemButton;

    @FXML @DoNotRename
    private Button removeSelectedItemButton;

    @FXML @DoNotRename
    private CheckBox lootAnyItemOverCheckBox;

    @FXML @DoNotRename
    private Spinner<Integer> lootAnyItemOverSpinner;

    @FXML @DoNotRename
    private Button reloadGroundItemsButton;

    @FXML @DoNotRename
    private Button saveSettingsButton;

    @FXML @DoNotRename
    private Button loadSettingsButton;

    @FXML @DoNotRename
    private Button startScriptButton;

    @FXML @DoNotRename
    private CheckBox minimumStackCheckBox;

    @FXML @DoNotRename
    private Spinner<Integer> minimumStackSpinner;

    @FXML @DoNotRename
    private CheckBox eatFoodForLootCheckBox;

    @FXML @DoNotRename
    private CheckBox dropItemsWorthLessCheckBox;

    @FXML @DoNotRename
    private CheckBox walkAroundAreaCheckBox;

    @FXML @DoNotRename
    private Spinner<Integer> walkAroundAreaSpinner;

    @FXML @DoNotRename
    private CheckBox ignoreJunkItemsCheckBox;

    @FXML @DoNotRename
    private ChoiceBox<String> selectedLocationChoiceBox;

    @FXML @DoNotRename
    private Button setCollectionArea;

    @FXML @DoNotRename
    private Button setWaitingTileButton;

    @FXML @DoNotRename
    private Spinner<Integer> customRadiusSpinner;

    @FXML @DoNotRename
    private Spinner<Integer> customWaitTileRadiusSpinner;

    @FXML @DoNotRename
    private TextArea customExplvMapArea;

    @FXML @DoNotRename
    private CheckBox emergencyEscapeCollectionAreaCheckBox;

    @FXML @DoNotRename
    private CheckBox attackMonstersthatAttackUsCheckBox;

    @FXML @DoNotRename
    private CheckBox attackMonstersFirstCheckBox;

    @FXML @DoNotRename
    private TextField monsterNameTextField;

    @FXML @DoNotRename
    private ChoiceBox<String> escapeFromPkersMethodChoiceBox;

    @FXML @DoNotRename
    private Spinner<Integer> maxWildernessLevelSpinner;

    @FXML @DoNotRename
    private CheckBox attemptToSafesSpotCheckBox;

    @FXML @DoNotRename
    private CheckBox escapeFromPkersMethodCheckBox;

    @FXML @DoNotRename
    private CheckBox alwaysKeepInstantEscapeTabOpenCheckBox;

    @FXML @DoNotRename
    private CheckBox onlyEscapeIfInteractedCheckBox;

    @FXML @DoNotRename
    private CheckBox alwaysDisableLeftClickAttackCheckBox;

    @FXML @DoNotRename
    private CheckBox maxWildernessLevelCheckBox;

    @FXML @DoNotRename
    private ListView<String> inventoryListView;

    @FXML @DoNotRename
    private ListView<String> equipmentListView;

    @FXML @DoNotRename
    private Button loadInventoryButton;

    @FXML @DoNotRename
    private Button loadEquipmentButton;

    @FXML @DoNotRename
    private TextField itemToBringTextField;

    @FXML @DoNotRename
    private Button addInventoryItemButton;

    @FXML @DoNotRename
    private Button equipmentToAddButton;

    @FXML @DoNotRename
    private TextField equipmentToBringTextField;

    @FXML @DoNotRename
    private Button deleteInventoryItemButton;

    @FXML @DoNotRename
    private Button deleteEquipmentItemButton;

    @FXML @DoNotRename
    private CheckBox requiredFoodCheckBox;

    @FXML @DoNotRename
    private ChoiceBox<String> requiredFoodChoiceBox;

    @FXML @DoNotRename
    private CheckBox healingTypeCheckBox;

    @FXML @DoNotRename
    private ChoiceBox<String> healingTypeChoiceBox;

    @FXML @DoNotRename
    private Spinner<Integer> hpToEatAtSpinner;

    @FXML @DoNotRename
    private Spinner<Integer> amountOfFoodSpinner;

    @FXML @DoNotRename
    private CheckBox useEnergyPotionCheckBox;

    @FXML @DoNotRename
    private CheckBox useAntiPoisonCheckBox;

    @FXML @DoNotRename
    private CheckBox useAntifireCheckBox;

    @FXML @DoNotRename
    private ChoiceBox<String> useEnergyPotionChoiceBox;

    @FXML @DoNotRename
    private ChoiceBox<String> useAntipoisonChoiceBox;

    @FXML @DoNotRename
    private ChoiceBox<String> useAntifireChoiceBox;

    @FXML @DoNotRename
    private TextField itemNameTextField;

    @FXML @DoNotRename
    private ChoiceBox<String> lootingMethodChoiceBox;

    @FXML @DoNotRename
    private Spinner<Integer> forceLootSpinner;

    @FXML @DoNotRename
    private Spinner<Integer> automaticallyBankAtSpinner;

    @FXML @DoNotRename
    private CheckBox teleportUnderAttackCheckbox;

    @FXML @DoNotRename
    private CheckBox attemptToTelegrabLootOver;

    @FXML @DoNotRename
    private CheckBox alchItemName;

    @FXML @DoNotRename
    private CheckBox endScriptIfTellegrabbingMovesUs;

    @FXML @DoNotRename
    private CheckBox lootingMethodCheckBox;

    @FXML @DoNotRename
    private CheckBox forceRunForLootCheckBox;

    @FXML @DoNotRename
    private CheckBox activelyRoamAreaForLootCheckBox;

    @FXML @DoNotRename
    private CheckBox automaticallyBankAtCheckBox;

    @FXML @DoNotRename
    private CheckBox cachePlayerCheckBox;

    @FXML @DoNotRename
    private CheckBox simulateAFKCheckBox;

    @FXML @DoNotRename
    private CheckBox antibanReactionCheckBox;

    @FXML @DoNotRename
    private CheckBox bankWhenOutOfFoodCheckBox;

    @FXML @DoNotRename
    private CheckBox dropEmptyFoodContrainersCheckBox;

    @FXML @DoNotRename
    private CheckBox bankIfLoseRequiredItemsCheckBox;

    @FXML @DoNotRename
    private CheckBox endScriptIfWeDieCheckBox;

    @FXML @DoNotRename
    private CheckBox endScriptIfWeAreMissingRequiredItemsCheckBox;

    @FXML @DoNotRename
    private ListView<String> blacklistedItemsListView;

    @FXML @DoNotRename
    private Button addBlacklistButton;

    @FXML @DoNotRename
    private Button removeBlacklistButton;

    @FXML @DoNotRename
    private TextField blacklistTextField;




    @FXML @DoNotRename
    void addItemToBlacklistPressed() {
        if (!blacklistTextField.getText().isEmpty()) {
            blacklistedItems.add(blacklistTextField.getText());
            /*
            Using directional binds you can make sure that both lists are always set to the same value

              blacklistedItems.add(blacklistTextField.getText());
            blacklistedItemsListView.setItems(blacklistedItems);
             */

        }
    }

    @FXML @DoNotRename
    void removeItemFromBlacklistPressed() {
        int index = blacklistedItemsListView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            blacklistedItemsListView.getItems().remove(index);
            blacklistedItems.remove(index);
            blacklistedItemsListView.setItems(blacklistedItems);
        }
    }

    @FXML @DoNotRename
    void addItemButtonPressed() {
        if (!addItemTextField.getText().isEmpty()){
            itemsToLoot.add(addItemTextField.getText());
            itemsToLootListView.setItems(itemsToLoot);
            //itemsToLootListView
        }
    }

    @FXML @DoNotRename
    void addSelectedItemPressed() {
        String str = possibleItemsListView.getSelectionModel().toString();
        if (!str.isEmpty()){
            itemsToLoot.add(str);
            itemsToLootListView.setItems(itemsToLoot);
        }

    }

    @FXML @DoNotRename
    void equipmentToAddButtonPressed() {
        if (!equipmentToBringTextField.getText().isEmpty()) {
            equipmentItems.add(equipmentToBringTextField.getText());
            equipmentListView.setItems(equipmentItems);
        }
    }

    @FXML @DoNotRename
    void loadEquipmentButtonPressed() {
            /*
            Needs to be in game
             */

    }


    @FXML @DoNotRename
    void deleteEquipmentItemButtonPressed() {
        int index = equipmentListView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            equipmentListView.getItems().remove(index);
            equipmentItems.remove(index);
            equipmentListView.setItems(equipmentItems);
        }
    }


    @FXML @DoNotRename
    void addInventoryItemButtonPressed() {
        if (!itemToBringTextField.getText().isEmpty()) {
            inventoryItems.add(itemToBringTextField.getText());
            inventoryListView.setItems(inventoryItems);
        }
    }

    @FXML @DoNotRename
    void deleteInventoryButtonItemPressed() {
        int index = inventoryListView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            inventoryListView.getItems().remove(index);
            inventoryItems.remove(index);
            inventoryListView.setItems(inventoryItems);
        }
    }


    @FXML @DoNotRename
    void loadInventoryButtonPressed() {
            /*
            Needs to be in game
             */
    }



    @FXML @DoNotRename
    void reloadGroundItemsButtonPressed() {
  /*
            Needs to be in game
             */
    }

    @FXML @DoNotRename
    void removeSelectedItemButtonPressed() {

        int index = itemsToLootListView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            blacklistedItemsListView.getItems().remove(index);
            blacklistedItems.remove(index);
            blacklistedItemsListView.setItems(blacklistedItems);
        }
    }



    @FXML @DoNotRename
    void setCollectionAreaButtonPressed() {
  /*
            Needs to be in game
             */
    }

    @FXML @DoNotRename
    void setWaitingTileButtonPressed() {
  /*
            Needs to be in game
             */
    }
    @FXML @DoNotRename
    void saveSettingsButtonPressed() {

        if (!directory.exists())
            directory.mkdirs();
        try {
            possibleItems.add("lol");
            itemsToLoot.add("lol");
            if (lootAnyItemOverCheckBox.isSelected()){
              //  settings.lootAnyItemOverCheckBox.setValue(true);
            }
            if (lootAnyItemOverSpinner.getValue()!=null){
              //  settings.lootAnyItemOverSpinner.toString();
            }
            //LOOT ANY ITEM OVER SPINNER

            if (minimumStackCheckBox.isSelected()){
              //  settings.minimumStackCheckBox.set(true);
            }

            if (eatFoodForLootCheckBox.isSelected()){
             //   settings.eatFoodForLootCheckBox.setValue(true);
            }

            if (eatFoodForLootCheckBox.isSelected()){
              //  settings.eatFoodForLootCheckBox.setValue(true);
            }

            if (dropItemsWorthLessCheckBox.isSelected()){
               // settings.dropItemsWorthLessCheckBox.setValue(true);
            }

            if (walkAroundAreaCheckBox.isSelected()){
              //  settings.walkAroundAreaCheckBox.setValue(true);
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

    @FXML @DoNotRename
    void loadSettingsButtonPressed() {

    }





    private GUISettings settings = new GUISettings();

    @FXML
    @DoNotRename
    public void menunewpressed() {

    }

    @FXML
    @DoNotRename
    public void menuopenpressed() {

        if (directory.exists()) {
            General.println("Opening our last save from: " + directory);
            try {
                // Read the settings
                String s = readString(new File(directory, "last.json").toPath());
                settings = new Gson().fromJson(s, GUISettings.class);

              //  possibleItemsListView.setItems((ObservableList<String>) settings.possibleItemsListView);
              //  itemsToLootListView.setItems((ObservableList<String>) settings.itemsToLootListView);



              //  blacklistedItemsListView.setItems((ObservableList<String>) settings.blacklistedItemsListView);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }




    @FXML
    @DoNotRename
    public void startscriptpressed() {


        this.getGUI().close();
    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {


        //ToDo

        //foodchoice.setItems(FXCollections.observableArrayList(foodChoiceArray));
        //foodchoice.disableProperty().bind(foodcheckbox.selectedProperty().not());
        //possibleItemsListView.itemsProperty().bindBidirectional((Property<ListView<String>>) settings.possibleItemsListView);

        //General.println(settings.minimumStackCheckBox);

        //possibleItemsListView.itemsProperty().bindBidirectional((Property<ObservableList<String>>) possibleItems);
        //itemsToLootListView.itemsProperty().bindBidirectional((Property<ObservableList<String>>) itemsToLoot);
       //blacklistedItemsListView.itemsProperty().bindBidirectional((Property<ObservableList<String>>) blacklistedItems);


        //blacklistedItemsListView.itemsProperty().bindBidirectional(settings.blacklistedItemsListView);

        /*
        lootAnyItemOverCheckBox.selectedProperty().bindBidirectional(settings.lootAnyItemOverCheckBox);
        lootAnyItemOverSpinner.getValueFactory().valueProperty().bindBidirectional(settings.lootAnyItemOverSpinner.asObject());
        minimumStackCheckBox.selectedProperty().bindBidirectional(settings.minimumStackCheckBox);
        minimumStackSpinner.getValueFactory().valueProperty().bindBidirectional(settings.minimumStackSpinner);
        eatFoodForLootCheckBox.selectedProperty().bindBidirectional(settings.eatFoodForLootCheckBox);
        dropItemsWorthLessCheckBox.selectedProperty().bindBidirectional(settings.dropItemsWorthLessCheckBox);
        walkAroundAreaCheckBox.selectedProperty().bindBidirectional(settings.walkAroundAreaCheckBox);
        walkAroundAreaSpinner.getValueFactory().valueProperty().bindBidirectional(settings.walkAroundAreaSpinner);
        ignoreJunkItemsCheckBox.selectedProperty().bindBidirectional(settings.ignoreJunkItemsCheckBox);

        //selectedLocationChoiceBox.selectedProperty().bindBidirectional(settings.selectedLocationChoiceBox);

        customRadiusSpinner.getValueFactory().valueProperty().bindBidirectional(settings.customRadiusSpinner);
        customWaitTileRadiusSpinner.getValueFactory().valueProperty().bindBidirectional(settings.customWaitTileRadiusSpinner);
        customExplvMapArea.textProperty().bind((ObservableValue<? extends String>) settings.customExplvMapArea);
        emergencyEscapeCollectionAreaCheckBox.selectedProperty().bindBidirectional(settings.emergencyEscapeCollectionAreaCheckBox);
        attackMonstersthatAttackUsCheckBox.selectedProperty().bindBidirectional(settings.attackMonstersthatAttackUsCheckBox);
        attackMonstersFirstCheckBox.selectedProperty().bindBidirectional(settings.attackMonstersFirstCheckBox);
        //monsterNameTextField
        //escapeFromPkersMethodChoiceBox
        maxWildernessLevelSpinner.getValueFactory().valueProperty().bindBidirectional(settings.maxWildernessLevelSpinner);
        attemptToSafesSpotCheckBox.selectedProperty().bindBidirectional(settings.attemptToSafesSpotCheckBox);
        escapeFromPkersMethodCheckBox.selectedProperty().bindBidirectional(settings.escapeFromPkersMethodCheckBox);
        //escapeFromPkersMethodChoiceBox.getValue().
        alwaysKeepInstantEscapeTabOpenCheckBox.selectedProperty().bindBidirectional(settings.alwaysKeepInstantEscapeTabOpenCheckBox);
        onlyEscapeIfInteractedCheckBox.selectedProperty().bindBidirectional(settings.onlyEscapeIfInteractedCheckBox);
        alwaysDisableLeftClickAttackCheckBox.selectedProperty().bindBidirectional(settings.alwaysDisableLeftClickAttackCheckBox);
        maxWildernessLevelCheckBox.selectedProperty().bindBidirectional(settings.maxWildernessLevelCheckBox);
        /*
        inventorylsitview
        equipment list view
         */
        /*
        requiredFoodCheckBox.selectedProperty().bindBidirectional(settings.requiredFoodCheckBox);

        //requiredFoodChoiceBox.setValue;
        healingTypeCheckBox.selectedProperty().bindBidirectional(settings.healingTypeCheckBox);
        //healkingtypechoicebox

        hpToEatAtSpinner.getValueFactory().valueProperty().bindBidirectional(settings.hpToEatAtSpinner);
        amountOfFoodSpinner.getValueFactory().valueProperty().bindBidirectional(settings.amountOfFoodSpinner);

        useEnergyPotionCheckBox.selectedProperty().bindBidirectional(settings.useEnergyPotionCheckBox);
        useAntiPoisonCheckBox.selectedProperty().bindBidirectional(settings.useAntiPoisonCheckBox);
        useAntifireCheckBox.selectedProperty().bindBidirectional(settings.useAntifireCheckBox);
        //itemnametextfield
        //lootingMethodChoiceBox.selectedProperty().bindBidirectional(settings.lootingMethodCheckBox);

        forceLootSpinner.getValueFactory().valueProperty().bind(settings.forceLootSpinner);
        automaticallyBankAtSpinner.getValueFactory().valueProperty().bindBidirectional(settings.automaticallyBankAtSpinner);
        teleportUnderAttackCheckbox.selectedProperty().bindBidirectional(settings.teleportUnderAttackCheckbox);
        attemptToTelegrabLootOver.selectedProperty().bindBidirectional(settings.attemptToTelegrabLootOver);
        alchItemName.selectedProperty().bindBidirectional(settings.alchItemName);
        //itemnbametextfield

        endScriptIfTellegrabbingMovesUs.selectedProperty().bindBidirectional(settings.endScriptIfTellegrabbingMovesUs);
        lootingMethodCheckBox.selectedProperty().bindBidirectional(settings.lootingMethodCheckBox);
        forceRunForLootCheckBox.selectedProperty().bindBidirectional(settings.forceRunForLootCheckBox);
        activelyRoamAreaForLootCheckBox.selectedProperty().bindBidirectional(settings.activelyRoamAreaForLootCheckBox);
        automaticallyBankAtCheckBox.selectedProperty().bindBidirectional(settings.automaticallyBankAtCheckBox);
        cachePlayerCheckBox.selectedProperty().bindBidirectional(settings.cachePlayerCheckBox);
        simulateAFKCheckBox.selectedProperty().bindBidirectional(settings.simulateAFKCheckBox);
        antibanReactionCheckBox.selectedProperty().bindBidirectional(settings.antibanReactionCheckBox);
        bankWhenOutOfFoodCheckBox.selectedProperty().bindBidirectional(settings.bankWhenOutOfFoodCheckBox);
        dropEmptyFoodContrainersCheckBox.selectedProperty().bindBidirectional(settings.dropEmptyFoodContrainersCheckBox);
        bankIfLoseRequiredItemsCheckBox.selectedProperty().bindBidirectional(settings.bankIfLoseRequiredItemsCheckBox);
        endScriptIfWeDieCheckBox.selectedProperty().bindBidirectional(settings.endScriptIfWeDieCheckBox);
        endScriptIfWeAreMissingRequiredItemsCheckBox.selectedProperty().bindBidirectional(settings.endScriptIfWeAreMissingRequiredItemsCheckBox);


         */
        //blaklisttextfield

    }

    /*
    Example reference code
     final Spinner<Integer> spinner = new Spinner<>(0, 1000000, 0, 1);
        spinner.getValueFactory().valueProperty().bindBidirectional(settings.lootAnyItemOverSpinner.asObject());
     */


}
