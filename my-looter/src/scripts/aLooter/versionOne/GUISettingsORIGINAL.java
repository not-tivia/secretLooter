package scripts.aLooter.versionOne;

import com.allatori.annotations.DoNotRename;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import lombok.Getter;
import lombok.Setter;

public class GUISettingsORIGINAL {




    @DoNotRename @Setter @Getter
    public Property<ObservableList<String>>  possibleItemsListView;
    @DoNotRename @Setter @Getter
    public Property<ObservableList<String>>  itemsToLootListView;
    @DoNotRename @Setter @Getter
    public Property<ObservableList<String>> blacklistedItemsListView;

    //public Property<ObservableList<String>> possibleItemsListView;
   // public Property<ObservableList<String>> itemsToLootListView;


    @DoNotRename @Setter @Getter
    public BooleanProperty lootAnyItemOverCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public IntegerProperty lootAnyItemOverSpinner = new SimpleIntegerProperty(1000);
    @DoNotRename @Setter @Getter
    public BooleanProperty minimumStackCheckBox= new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public Property minimumStackSpinner  = new SimpleIntegerProperty();
    @DoNotRename @Setter @Getter
    public BooleanProperty eatFoodForLootCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty dropItemsWorthLessCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty walkAroundAreaCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public Property walkAroundAreaSpinner  = new SimpleIntegerProperty();
    @DoNotRename @Setter @Getter
    public BooleanProperty ignoreJunkItemsCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty selectedLocationChoiceBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public Property customRadiusSpinner  = new SimpleIntegerProperty();
    @DoNotRename @Setter @Getter
    public Property customWaitTileRadiusSpinner  = new SimpleIntegerProperty();

    @DoNotRename @Setter @Getter
    public StringProperty customExplvMapArea;

    @DoNotRename @Setter @Getter
    public BooleanProperty emergencyEscapeCollectionAreaCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty attackMonstersthatAttackUsCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty attackMonstersFirstCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public StringProperty monsterNameTextField;
    @DoNotRename @Setter @Getter
    public BooleanProperty escapeFromPkersMethodChoiceBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public Property maxWildernessLevelSpinner = new SimpleIntegerProperty();
    @DoNotRename @Setter @Getter
    public BooleanProperty attemptToSafesSpotCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty escapeFromPkersMethodCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty alwaysKeepInstantEscapeTabOpenCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty onlyEscapeIfInteractedCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty alwaysDisableLeftClickAttackCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty maxWildernessLevelCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public ListProperty inventoryListView;
    @DoNotRename @Setter @Getter
    public ListProperty equipmentListView;
    @DoNotRename @Setter @Getter
    public BooleanProperty requiredFoodCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty requiredFoodChoiceBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty healingTypeCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty healingTypeChoiceBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public Property hpToEatAtSpinner  = new SimpleIntegerProperty();
    @DoNotRename @Setter @Getter
    public Property amountOfFoodSpinner  = new SimpleIntegerProperty();
    @DoNotRename @Setter @Getter
    public BooleanProperty useEnergyPotionCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty useAntiPoisonCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty useAntifireCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public StringProperty itemNameTextField;
    @DoNotRename @Setter @Getter
    public BooleanProperty lootingMethodChoiceBox  = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public Property forceLootSpinner = new SimpleIntegerProperty();
    @DoNotRename @Setter @Getter
    public Property automaticallyBankAtSpinner  = new SimpleIntegerProperty();
    @DoNotRename @Setter @Getter
    public BooleanProperty teleportUnderAttackCheckbox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty attemptToTelegrabLootOver = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty alchItemName = new SimpleBooleanProperty(false);
    // itemnametextfield
    // Alch item name is a checkbox

    @DoNotRename @Setter @Getter
    public BooleanProperty endScriptIfTellegrabbingMovesUs = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty lootingMethodCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty forceRunForLootCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty activelyRoamAreaForLootCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty automaticallyBankAtCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty cachePlayerCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty simulateAFKCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty antibanReactionCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty bankWhenOutOfFoodCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty dropEmptyFoodContrainersCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty bankIfLoseRequiredItemsCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty endScriptIfWeDieCheckBox = new SimpleBooleanProperty(false);
    @DoNotRename @Setter @Getter
    public BooleanProperty endScriptIfWeAreMissingRequiredItemsCheckBox = new SimpleBooleanProperty(false);

    //public Property<ObservableList<String>> blacklistedItemsListView;
    @DoNotRename @Setter @Getter
    public StringProperty blacklistTextField;





}
