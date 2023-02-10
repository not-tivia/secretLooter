package scripts.aLooter.data;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.sdk.types.Area;

import java.util.ArrayList;

public class Vars {



    public boolean teleblockAttempted = false;
    /*
    Location tab

     */
    private String selectedLocation;
    private RSTile customLocation;
    private int customLocationRadius;
    //public static RSArea requiredArea =new RSArea(new RSTile(3185, 3470, 0), new RSTile(3144, 3512, 0));



    /*
    Looting Tab
     */
    public ArrayList possibleItems;
    public ArrayList itemsToLoot;

    private String lootingPriority;
    private boolean findnewTile;
    private int findnewTileTime;


    //Looting tab
    public static boolean lootAnyItemOverCheckBox;
    public static int lootAnyItemOverSpinner = 0;

    public static boolean minimumStackCheckBox = false;
    public static int minimumStack = 2;

    public static boolean walkAroundAreaCheckBox = false;
    public static int walkAroundAreaSpinner = 15;

    public static boolean eatFoodForLootCheckBox;
    public static boolean ignoreJunkItemsCheckBox;

    public static boolean dropItemsWorthLessCheckBox = false;


    //Location tab
    public static int customCollectionRadius = 0;
    //public static RSArea collectionArea;
    public static Area collectionArea;
    public static Area requiredArea;
    public static int customIdleRadius = 0;
    public static Area idleArea;
    /*
    This was an rsarea
     */
    public static RSArea customExplvMapArea;
    public static String customExplvMapAreaString;
    public static boolean emergencyEscapeCollectionAreaCheckBox;

    //Combat tab
    public static boolean attackMonstersthatAttackUsCheckBox;
    public static String monsterNameTextField;
    public static boolean attemptToSafesSpotCheckBox;


    //Combat - PVP Settings
    public static boolean escapeFromPkersMethodCheckBox;
    public static boolean        alwaysKeepInstantEscapeTabOpenCheckBox;
    public static boolean onlyEscapeIfInteractedCheckBox;
    public static boolean        alwaysDisableLeftClickAttackCheckBox;
    public static boolean maxWildernessLevelCheckBox;

    // Inventory gear
    //Food settings
    public static String hpToEatAt;
    public static String amountOfFood;
    public static String foodName;

    //Potion Settings
    public static String useEnergyPotionCheckBox;
    public static String useAntiPoisonCheckBox;
    public static String useAntifireCheckBox;

    //Magic Extra
    public static boolean teleportUnderAttackCheckbox;
    public static boolean attemptToTelegrabLootOver;
    public static boolean alchItemName;
    public static boolean endScriptIfTellegrabbingMovesUs;
    // Looting options
    public static boolean lootingMethodCheckBox;
    public static boolean forceRunForLootCheckBox;
    public static boolean activelyRoamAreaForLootCheckBox;
    public static boolean automaticallyBankAtCheckBox;
    public static boolean cachePlayerCheckBox;
    public static boolean simulateAFKCheckBox;
    public static boolean antibanReactionCheckBox;

    public static boolean bankWhenOutOfFoodCheckBox;
    public static boolean dropEmptyFoodContrainersCheckBox;
    public static boolean bankIfLoseRequiredItemsCheckBox;
    public static boolean endScriptIfWeDieCheckBox;
    public static boolean endScriptIfWeAreMissingRequiredItemsCheckBox;

    public static RSArea areaIdle;

    private RSTile areaIdleTilesDrawn[] = null;
    private RSTile areaCollectionTilesDrawn[] = null;

    public static int wildernessLevel;















}
