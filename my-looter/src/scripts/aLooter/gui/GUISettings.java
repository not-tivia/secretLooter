package scripts.aLooter.gui;

import com.allatori.annotations.DoNotRename;
import javafx.beans.property.*;
import lombok.Getter;
import lombok.Setter;

public class GUISettings {
    @DoNotRename @Setter @Getter
    public BooleanProperty lootItemToggleButton = new SimpleBooleanProperty(false);

    @DoNotRename @Setter @Getter
    public Property LootItemSpinner  = new SimpleIntegerProperty();

    @DoNotRename @Setter @Getter
    public Property BankOverSpinner  = new SimpleIntegerProperty();

    @DoNotRename @Setter @Getter
    public StringProperty lootItemTextField = new SimpleStringProperty();

    @DoNotRename @Setter @Getter
    public BooleanProperty ActiveRoamToggleButton = new SimpleBooleanProperty(false);

    @DoNotRename @Setter @Getter
    public BooleanProperty ReturnToIdleToggleButton = new SimpleBooleanProperty(false);

    @DoNotRename @Setter @Getter
    public BooleanProperty CustomRoamTimeCheckBox = new SimpleBooleanProperty(false);

    @DoNotRename @Setter @Getter
    public Property CustomRoamTimeSpinner  = new SimpleIntegerProperty();

    @DoNotRename @Setter @Getter
    public BooleanProperty EmergencyTeleportCheckBox = new SimpleBooleanProperty(false);

    @DoNotRename @Setter @Getter
    public BooleanProperty CustomIdleAreaTimerCheckBox = new SimpleBooleanProperty(false);

    @DoNotRename @Setter @Getter
    public Property CustomIdleAreaTimerSpinner  = new SimpleIntegerProperty();

    @DoNotRename @Setter @Getter
    public BooleanProperty LootItemCheckBox = new SimpleBooleanProperty(false);

    @DoNotRename @Setter @Getter
    public BooleanProperty SpamClickCheckBox = new SimpleBooleanProperty(false);

    @DoNotRename @Setter @Getter
    public BooleanProperty BankOverValueCheckBox = new SimpleBooleanProperty(false);

    @DoNotRename @Setter @Getter
    public StringProperty locationChoiceBox = new SimpleStringProperty();

    @DoNotRename @Setter @Getter
    public Property CustomLootingRadiusSpinner  = new SimpleIntegerProperty();

    @DoNotRename @Setter @Getter
    public Property CustomIdleRadiusSpinner  = new SimpleIntegerProperty();

    @DoNotRename @Setter @Getter
    public BooleanProperty RequireExactCheckBox = new SimpleBooleanProperty(false);

    @DoNotRename @Setter @Getter
    public BooleanProperty AttackNPCCheckBox = new SimpleBooleanProperty(false);

    @DoNotRename @Setter @Getter
    public BooleanProperty TeleportCheckBox = new SimpleBooleanProperty(false);

    @DoNotRename @Setter @Getter
    public BooleanProperty UseFoodCheckBox = new SimpleBooleanProperty(false);

    @DoNotRename @Setter @Getter
    public BooleanProperty WorldHopCheckBox = new SimpleBooleanProperty(false);

    @DoNotRename @Setter @Getter
    public BooleanProperty StopOnDeathCheckBox = new SimpleBooleanProperty(false);

    @DoNotRename @Setter @Getter
    public BooleanProperty ItemIsObjectCheckBox = new SimpleBooleanProperty(false);







}
