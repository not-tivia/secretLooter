package scripts.aLooter;




import org.tribot.api.Clicking;
import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api.util.abc.ABCUtil;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Arguments;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.sdk.Bank;
import org.tribot.script.sdk.Interaction;
import org.tribot.script.sdk.MyPlayer;
import org.tribot.script.sdk.query.Query;
import org.tribot.script.sdk.types.GroundItem;
import org.tribot.script.sdk.types.WorldTile;
import org.tribot.script.sdk.walking.GlobalWalking;
import scripts.aLooter.gui.GUI;
import scripts.aLooter.helpers.CombatInteractions;
import scripts.api.dax_api.api_lib.DaxWalker;
import scripts.api.dax_api.api_lib.models.DaxCredentials;
import scripts.api.dax_api.api_lib.models.DaxCredentialsProvider;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Optional;

import static org.tribot.script.sdk.MyPlayer.isPoisoned;
import static scripts.aLooter.data.Vars.*;


@ScriptManifest(authors = {"Tivia"}, category = "ACustom", name = "aLooter", description = "aLooter", version = (1))


public class Main extends Script implements Arguments, Painting {


    private final long START_TIME = System.currentTimeMillis();
    private final boolean continueRunning = true;
    private final int itemPrice = 0;
    private final boolean teleblockAttempted = false;
    // this is an instanced class you can use throughout the whole script.
    private final ABCUtil abcInstance = new ABCUtil();
    /*
    Things for paint
    GP made (gp an hour)
    items picked up / most expensive item /biggest item stack
    State
    Time ran

    by Tivia


    Things to consider adding to GUI
    Attempt to safespot set tile button
    Add world hop if our desired loot isn't there
         Set different clicking behaviors based on situations
          -eat at bank
          - check if we have proepr gear
          -make sure we eat if killable
          - change clicking behavior ebcause we still hover over things
          -option to bury bones, either from combat or looted on accident
          -if we are clicking on an item, but one of equal or greater value spawns and is closer, change target
          - fix area paint for new sdk

     */
    String args;
    String status = "Starting";
    RSTile clickingTile = null;
    int eatAt = abcInstance.generateEatAtHP();
    private URL fxml;
    private GUI gui;
    private int IDLE_TIMER = General.random(5000, 7000);
    private State scriptState;
    private int run_at = 30;
    private long LAST_BREAK = System.currentTimeMillis();
    // vars
    private boolean shouldHover, shouldOpenMenu = false;

    private void dropTrash() {
        RSItem[] inventoryLoot = Inventory.getAll();
        //if (inventoryLoot.length > 0 && inventoryLoot[]!=null &&)
        // itemPrice = Pricing.lookupPrice(loot[0].getDefinition().getID()).orElse(-1);

    }

    private void teleEscape() {
        // Daxwalker walk to bank if we get attacked
    }

    private void teleGrab() {
        /*
        If we go running bank and end script
         if loot is over set value then telegrab

         */
    }

    private void highAlch() {

    }

    @Override
    public void passArguments(HashMap<String, String> hashMap) {
        if (hashMap.containsKey("custom_input")) {
            args = hashMap.get("custom_input");
        } else if (hashMap.containsKey("autostart")) {
            args = hashMap.get("autostart");
        }
    }

    private void daxStart() {
        DaxWalker.setCredentials(new DaxCredentialsProvider() {
            @Override
            public DaxCredentials getDaxCredentials() {
                return new DaxCredentials("sub_DPjXXzL5DeSiPf", "PUBLIC-KEY");
            }
        });

        Mouse.setSpeed(General.random(240, 360));
        Camera.setRotationMethod(Camera.ROTATION_METHOD.ONLY_MOUSE);


    }

    private void runCheck() {
        if (!Game.isRunOn() && Game.getRunEnergy() >= run_at) {
            if (Options.setRunOn(true)) {
                Timing.waitCondition(() -> Options.isRunEnabled(), General.random(600, 1100));
                run_at = abcInstance.generateRunActivation();
            }
        }
    }

    private boolean onStart() {
        daxStart();
        //perform username checks and stuff here
        return true;
    }

    private void PanicTele() {
        /*
        If doesnt have a teletab/tele then turn run on
         */
        if (emergencyEscapeCollectionAreaCheckBox) {

            if (collectionArea.containsMyPlayer()) {
                if (DaxWalker.walkToBank()) {
                    //Sometimes at ge wait tile area can be right next to bank and thats bad
                    Timing.waitCondition(() -> !Player.isMoving(), General.random(3000, 5000));
                    status = ("Walking to bank");
                }
            }
        }
    }

    @Override
    public void run() {
        if (args.isEmpty()) {
            try {
                fxml = new URL("https://raw.githubusercontent.com/not-tivia/TRiBot/master/simplelooter.fxml");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            gui = new GUI(fxml);
            gui.show();
            while (gui.isOpen()) {
                sleep(150);
            }
        }

        if (onStart()) {
            while (continueRunning) {
                loop();
            }
        }
    }



    private int loop() {
        scriptState = getState();
        General.sleep(50);


        switch (scriptState) {

            case GET_REQUIRED_ITEMS:
                General.println("get required");
                break;


            case DEPOSIT:

                /*
                Does not work if we are not alreaddy at bank
                 */
                if (Bank.isNearby()) {
                    if (Banking.isBankScreenOpen()) {
                        Banking.depositAll();
                        status = ("Depositing bank");

                        General.sleep(2000, 3000);
                        Banking.close();
                        General.sleep(2000, 3000);

                    } else {
                        status = ("Opening bank");

                        Banking.openBank();
                        General.sleep(3000, 5000);
                    }
                } else {
                    if (DaxWalker.walkToBank()) {
                        Timing.waitCondition(() -> !Player.isMoving(), General.random(3000, 5000));
                        status = ("Banking..");

                    }
                }

                break;

            case LOOTING:

                Optional<GroundItem> itemTest = Query.groundItems()
                        .nameNotEquals("Ashes", "Vial", "Weed", "Beer glass", "Longbow (u)", "Shortbow (u)", "Bones")
                        .nameNotContains("Bronze", "Iron", "Wooden", "Waterskin", "Steel", "Coif", "Logs")
                        .minPrice(lootAnyItemOverSpinner)
                        .sortedByDistance()
                        .inArea(collectionArea)
                        .findFirst();
                if (emergencyEscapeCollectionAreaCheckBox) {
                    if (!collectionArea.containsMyPlayer()) {
                        teleEscape();
                    }
                }
                /*
                if we are telegrabbing
                make sure that we have our required runes and the correct magic level

                 */


                if (itemTest.isPresent()) {
                    if (itemTest.get().isVisible()) {

                        if (telegrabbingLoot()) {

                        } else {

                        }

                        clickingTile = itemTest.get().getLegacyRSGroundItem().getPosition();
                        status = ("Clicking loot");
                            /*
                            If we have clicked on our ground item, then don't click again if we are moving
                             */
                        //if (!Projection.getTileBoundsPoly(clickingTile, 0).contains(Mouse.getPos())) {
                        if (!Player.isMoving()) {
                            if (Interaction.interactGroundItem(itemTest.get().getId(), "Take")) {
                                Timing.waitCondition(() -> (Game.getDestination() != null && Game.getDestination() == clickingTile), General.random(900, 1700));
                                LAST_BREAK = System.currentTimeMillis();
                                // }
                            }
                        }

                            /*
                            if (Game.getDestination() != null && Game.getDestination() == clickingTile) {
                                General.println("Already walking to loot, don't click again.");
                            } else {
                                if (Game.getDestination() == null) {
                                    if (!Projection.getTileBoundsPoly(clickingTile, 0).contains(Mouse.getPos())) {
                                        if (Clicking.click("Take", itemTest.get().getLegacyRSGroundItem())) {
                                            General.sleep(750, 1500);
                                            LAST_BREAK = System.currentTimeMillis();
                                        }
                                    }
                                }


                            }
                            old code was aving the game.gedestination break
                             */
                    } else {
                        if (Walking.walkTo(itemTest.get().getLegacyRSGroundItem().getPosition())) {
                            Timing.waitCondition(() -> !Player.isMoving(), General.random(3000, 5000));
                            status = ("Walking to loot");
                        }
                    }

                }


                /*
                //RSGroundItem[] noteditems = GroundItems.findNearest(Filters.GroundItems.nameContains("Burnt", "Leather", "Raw", "raw", "jug", "Jug", "Logs", "logs", "arrow", "Bones", "bones", "Lobster", "potion", "Potion", "Leather", "Cowhide"));
                //RSGroundItem[] loot = GroundItems.findNearest(Filters.GroundItems.nameContains(   "Cape","cape","(g)","(t)","Wizard", "wizard", "Red", "Adamant","Mithril", "zamorak", "Staff","rune", "runes", "Feather", "Coal", "ore", "bar", "d'hide", "partyhat", "Partyhat", "Halloween", "Gilded", "Coins"));
                RSGroundItem[] loot = GroundItems.findNearest((Filters.GroundItems.nameNotEquals("Ashes", "Vial", "Weed", "Beer glass", "Longbow (u)", "Shortbow (u)", "Bones").and(Filters.GroundItems.nameNotContains("Bronze", "Iron", "Wooden", "Waterskin", "Steel", "Coif", "Logs"))));
                if (loot.length > 0 && loot[0] != null) {
                    if (collectionArea.contains(loot[0])) {
                        if (loot[0].isOnScreen() && loot[0].isClickable()) {
                            if (loot.length > 0 && loot[0].getDefinition() != null) {
                                itemPrice = Pricing.lookupPrice(loot[0].getDefinition().getID()).orElse(-1);
                                if (itemPrice >= lootAnyItemOverSpinner || loot[0].getStack() >= minimumStack) {
                                    clickingTile = loot[0].getPosition();
                                    status = ("Clicking loot");
                                    if (Game.getDestination() != null && Game.getDestination() == clickingTile) {
                                        General.println("Already walking to loot, don't click again.");
                                    } else {
                                        if (Game.getDestination() == null) {
                                            if (!Projection.getTileBoundsPoly(clickingTile, 0).contains(Mouse.getPos())) {
                                                if (Clicking.click("Take", loot[0])) {
                                                    General.sleep(750, 1500);
                                                    LAST_BREAK = System.currentTimeMillis();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (Walking.walkTo(loot[0].getPosition())) {
                                Timing.waitCondition(() -> !Player.isMoving(), General.random(3000, 5000));
                                status = ("Walking to loot");
                            }
                        }
                    }
                }

                 */
                break;

            case WALK_TO_AREA:
                if (!idleArea.containsMyPlayer()) {
/*
                    if (customIdleRadius == 0) {
                        RSTile safeTile = idleArea.getRandomTile                                           ();
                        if (safeTile.isClickable() && safeTile.isOnScreen()) {
                            if (Walking.clickTileMS(safeTile, "Walk here")) {
                                Timing.waitCondition(() -> !Player.isMoving(), General.random(3000, 5000));
                                General.sleep(400, 700);
                            }
                        } else {
                            if (DaxWalker.walkTo(idleArea.getRandomTile())) {
                                //Sometimes at ge wait tile area can be right next to bank and thats bad
                                Timing.waitCondition(() -> !Player.isMoving(), General.random(3000, 5000));
                                status = ("Walking to idle area");
                            }
                        }
                    }
                    if (DaxWalker.walkTo(idleArea.getRandomTile())) {
                        //Sometimes at ge wait tile area can be right next to bank and thats bad
                        Timing.waitCondition(() -> !Player.isMoving(), General.random(3000, 5000));
                        status = ("Walking to idle area");
                    }


 */
                } else {
                    if (collectionArea.containsMyPlayer()) {

                    }
                }


                break;
            case WALKING_TO_GE:

/*
                if (!idleArea.contains(Player.getPosition())) {
                    DaxWalker.walkTo(idleArea.getRandomTile());
                    Timing.waitCondition(() -> !Player.isMoving(), General.random(3000, 5000));
                    status = ("Walking to ge area");
                }


 */

                break;

            case CLOSE_BANK:
                if (Banking.isBankScreenOpen()) {
                    status = ("Closing bank");
                    Banking.close();
                    General.sleep(2000, 3000);
                }
                break;

            case WAITING_FOR_LOOT:

                //Drop Junk items befoer doing this
                if (worldHoppingForLoot()) {

                } else {
                    if (alchingLoot()) {

                    } else {
                        if (willFightNPCS()) {

                        } else {
                            //waiting for loot
                        }
                    }
                }

                /*
                If we are roaming our area for loot
                 */
                if (walkAroundAreaCheckBox) {
                    if (walkAroundAreaSpinner < 1) {
                        walkAroundAreaSpinner = General.random(30, 90);
                    } else {
                        if (System.currentTimeMillis() - LAST_BREAK >= IDLE_TIMER) {
                            if (idleArea.containsMyPlayer()) {
                                status = ("Idle time has been exceeded but we already in our waiting area");
                                performTimedActions();
                            } else {
                                status = ("Waiting before walking back to idle area");
                                General.println("waiting for " + IDLE_TIMER + " and walking back to waiting area.");
                            }
                            /*
                            if (DaxWalker.walkTo(idleArea.getRandomTile())) {
                                Timing.waitCondition(() -> !Player.isMoving(), General.random(3000, 5000));
                                status = ("Waiting before walking back to idle area");
                                General.println("waiting for " + IDLE_TIMER + " and walking back to waiting area.");
                                LAST_BREAK = System.currentTimeMillis();
                                //IDLE_TIMER = General.random(15000, 30000);
                                IDLE_TIMER = walkAroundAreaSpinner * 1000 + General.random(2000, 10000);
                            }

                             */
                        } else {
                            status = ("Idle time has not been exceeded.. performing timed actions");
                            performTimedActions();
                        }

                    }
                } else {

                    /*
                    If we aer not roaming around our area
                     */
                    if (idleArea.containsMyPlayer()) {
                        status = ("Idle time has been exceeded but we already in our waiting area");
                        performTimedActions();
                    } else {
                        /*
                        If idle radius is 0 then we just walk to one specicic tile
                         */
                        if (customIdleRadius == 0) {
                            WorldTile safetile = idleArea.getRandomTile();
                             if (safetile.isVisible()){
                                 if (safetile.leftClickOnScreen()){
                                     Timing.waitCondition(() -> !Player.isMoving(), General.random(3000, 5000));
                                     status = ("Walking to idle area");
                                 }

                             }
                           // RSTile safeTile = idleArea.getRandomTile();

                            /*


                            if (safetile.isVisible()) {
                                if (GlobalWalking.walkTo(safetile)){
                                    Timing.waitCondition(() -> !Player.isMoving(), General.random(3000, 5000));
                                    status = ("Walking to idle area");
                                }

                                  if (Walking.clickTileMS(safetile., "Walk here")) {
                                    Timing.waitCondition(() -> !Player.isMoving(), General.random(3000, 5000));
                                    General.sleep(400, 700);
                                }


                            } else {
                                if (DaxWalker.walkTo(idleArea.getRandomTile())) {
                                    //Sometimes at ge wait tile area can be right next to bank and thats bad
                                    Timing.waitCondition(() -> !Player.isMoving(), General.random(3000, 5000));
                                    status = ("Walking to idle area");
                                }
                            }

                             */
                        } else {
                            /*
                            if (DaxWalker.walkTo(idleArea.getRandomTile())) {
                                Timing.waitCondition(() -> !Player.isMoving(), General.random(3000, 5000));
                                status = ("Waiting before walking back to idle area");

                                General.println("waiting for " + IDLE_TIMER + " and walking back to waiting area.");
                            }

                             */
                        }
                    }
                }

                break;
            case ATTACKING_MONSTERS:
                /*
                If we are only auto retaliating then do xyz
                If we want to attack specicit npcs while waiting for loot then
                check if we are safespotting then attack them
                 */

                break;

            case HEAL:
                //eat food and loot
                RSItem[] food = Inventory.find(foodName);

                if (food.length > 0 && food[0] != null) {
                    if (getHpPercent() <= eatAt) {
                        if (food[0].getDefinition() != null) {
                            String[] option = food[0].getDefinition().getActions();
                            if (option.length > 0) {
                                if (option[0].equals("Drink")) {
                                    food[0].click("Drink");
                                } else {
                                    food[0].click("Eat");
                                }
                            }
                        }
                        General.sleep(450, 750);
                        for (int i = 0; i < 10 && Player.getAnimation() != -1; i++) {
                            sleep(90, 110);
                        }
                        eatAt = abcInstance.generateEatAtHP();
                    }
                }


                break;

            case EATING_FOR_INVENTORY_SPACE:
                food = Inventory.find(foodName);

                if (food.length > 0 && food[0] != null) {
                    if (food[0].getDefinition() != null) {
                        String[] option = food[0].getDefinition().getActions();
                        if (option.length > 0) {
                            if (option[0].equals("Drink")) {
                                food[0].click("Drink");
                            } else {
                                food[0].click("Eat");
                            }
                        }
                    }
                    General.sleep(450, 750);
                    for (int i = 0; i < 10 && Player.getAnimation() != -1; i++) {
                        sleep(90, 110);
                    }
                }
                break;


        }
        return 50;
    }

    private boolean hasRequiredItems() {

        return true;
    }

    private boolean inRequiredArea() {
        if (Player.getRSPlayer() != null && Player.getPosition() != null) {
            if (Game.getDestination() != null && collectionArea.contains(MyPlayer.getDestination().get().toLocalTile()) && lootExists()) {
                return true;
            }
           // return collectionArea.containsMyPlayer() || idleArea.contains(Player.getPosition());
        }

        return false;
    }

    private boolean inCombat() {

        return CombatInteractions.isFighting() || CombatInteractions.isUnderAttackByTarget();
    }

    private boolean isItemSpawned() {
        return false;
    }

    private boolean isPkerNear() {
        return true;
        /*
        if (Combat.isInWilderness()) {
            wildernessLevel = Combat.getWildernessLevel();
        }

        Optional<org.tribot.script.sdk.types.Player> test = Query.players()
                .withinCombatLevels(wildernessLevel)
                .findFirst();
        return test.isPresent();

         */
    }

    private boolean hasFood() {
        RSItem[] food = Inventory.find("setFoodName");
        return true;
    }

    private boolean isLowHealth() {
        return false;
    }

    private boolean readyToLoot() {
        return hasRequiredItems() && inRequiredArea();


        /*
        Counting down time to loot dangerous loots
        the item we are waiting for is spawned
        is there valid loot to pick up
         */

    }

    private boolean willFightNPCS() {
        /*
        Gui settings, attack monster, attack monster while waiting for loot
        attempt to safespot
        teleport if under attack

         */

        return false;
    }

    private boolean lootExistsStack() {
        Optional<GroundItem> itemTest = Query.groundItems()
                .nameNotEquals("Ashes", "Vial", "Weed", "Beer glass", "Longbow (u)", "Shortbow (u)", "Bones")
                .nameNotContains("Bronze", "Iron", "Wooden", "Waterskin", "Steel", "Coif", "Logs")
                .minStack(minimumStack)
                .findFirst();

        return itemTest.isPresent();

        /*
                RSGroundItem[] loot = GroundItems.findNearest((Filters.GroundItems.nameNotEquals("Ashes", "Vial", "Weed", "Beer glass", "Longbow (u)", "Shortbow (u)", "Bones").and(Filters.GroundItems.nameNotContains("Bronze", "Iron", "Wooden", "Waterskin", "Steel", "Coif", "Logs"))));
        if (loot.length > 0 && loot[0] != null && collectionArea.contains(loot[0]) && loot[0].getDefinition() != null) {
            itemPrice = Pricing.lookupPrice(loot[0].getDefinition().getID()).orElse(-1);
            return itemPrice >= lootAnyItemOverSpinner || loot[0].getStack() >= minimumStack;
        }

         */

    }

    private boolean lootExists() {
        return lootExistsStack() || lootExistsPrice();
    }

     /*
    Are we Ready to bank - (Inventory is full of bankable items (exclude our selected food)
    Are we in danger - (Out of food and low on HP, PVPers nearby)

    Do we have our required items - gear, potions, telegrabs, ect
  - No, Bank for them
        - Yes


    Are we in our waiting area
        -Walk to waiting area area

       Is there loot for us in our collection area
        - yes collect loot

        -no
        - roam our idle area
        - Idle on our waiting area
        - are we fighting monsters/alching/doing other stuff
        - worldhop


Our idle area can be outside of our collection area


     */

    private boolean lootExistsPrice() {
        Optional<GroundItem> itemTest = Query.groundItems()
                .nameNotEquals("Ashes", "Vial", "Weed", "Beer glass", "Longbow (u)", "Shortbow (u)", "Bones")
                .nameNotContains("Bronze", "Iron", "Wooden", "Waterskin", "Steel", "Coif", "Logs")
                .minPrice(lootAnyItemOverSpinner)
                .findFirst();

        return itemTest.isPresent();

        /*
                RSGroundItem[] loot = GroundItems.findNearest((Filters.GroundItems.nameNotEquals("Ashes", "Vial", "Weed", "Beer glass", "Longbow (u)", "Shortbow (u)", "Bones").and(Filters.GroundItems.nameNotContains("Bronze", "Iron", "Wooden", "Waterskin", "Steel", "Coif", "Logs"))));
        if (loot.length > 0 && loot[0] != null && collectionArea.contains(loot[0]) && loot[0].getDefinition() != null) {
            itemPrice = Pricing.lookupPrice(loot[0].getDefinition().getID()).orElse(-1);
            return itemPrice >= lootAnyItemOverSpinner || loot[0].getStack() >= minimumStack;
        }

         */

    }



    private boolean telegrabbingLoot() {
        return attemptToTelegrabLootOver;
    }

    private boolean alchingLoot() {
        return false;
    }

    private boolean worldHoppingForLoot() {
        return false;
    }

    private boolean isLowHP() {
        return getHpPercent() <= eatAt;
    }

    private int getHpPercent() {
        return (Skills.getCurrentLevel(Skills.SKILLS.HITPOINTS) * 100) / Skills.getActualLevel(Skills.SKILLS.HITPOINTS);
    }

    private boolean shouldEatForLoot() {
        RSItem[] food = Inventory.find(foodName);
        return food.length > 0 && food[0] != null;

        /*
        Check if we have eatable food, if we do, eat it
        set boolean if we have food to eat or not
        check for jugs and pie dishes
        check if we can eat food and continue looting
         */

    }


    private boolean isInDanger() {
        if (isPkerNear() && isPoisoned()) {
            return true;
        }
        if (teleblockAttempted) {
            return true;
        }
        if (isLowHP()) {
            return true;
        }
        return isPoisoned();
    }


    /*
    Are we ready to loot
    walk to area
    wait for loot or do other actions

    if we are at bank
    no we are not ready for loot
    do we need gear or food
    do we need to drink a potion
    our iventory is full
     */

    private boolean hasRequiredGear() {
        return true;
    }

    private boolean hasRequiredInventory() {
        return true;
    }

    private void lol2(){
        //Get players total level
        /*
          Optional<WorldQuery> lol = Query.worlds()
                .isNotDangerous()

                .isMainGame()
                .findRandom();
        maxTotalLevelRequirement

         */

    }

    private State getState() {
        /*
        if (readyToLoot()){
            if (inRequiredArea()){
                if (lootExists()){
                    return State.LOOTING;
                } else{
                    return State.WAITING_FOR_LOOT;
                }
            } else {
                return State.WALK_TO_AREA;
            }
        } else {
            if (needsToBank()){
               if (!hasRequiredGear()) {
                   return State.SETTING_UP_GEAR;
               } else {
                   if (!hasRequiredInventory()){
                       return State.SETTING_UP_INVENTORY;
                   } else {
                       return State.DEPOSIT_LOOT;
                   }
               }
            }

        }

         */

        if (Inventory.isFull()) {
            if (shouldEatForLoot()) {
                return State.EATING_FOR_INVENTORY_SPACE;
            } else {
                return State.DEPOSIT;
            }
        } else {
            if (isInDanger()) {
                if (isPkerNear()) {
                    return State.ESCAPE;
                } else {
                    if (isPoisoned()) {
                        return State.POISONED;
                    } else {
                        if (isLowHP()) {
                            if (hasFood()) {
                                return State.HEAL;
                            } else {
                                return State.ESCAPE;
                            }
                        }
                    }
                }
                return State.ESCAPE;
            } else {
                if (hasRequiredItems()) {
                    if (inRequiredArea()) {
                        if (lootExists()) {
                            return State.LOOTING;

                        } else {
                            if (worldHoppingForLoot()) {
                                return State.HOPPING_WORLDS;
                            } else {
                                if (alchingLoot()) {
                                    return State.ALCHING;
                                } else {
                                    if (willFightNPCS()) {
                                        return State.ATTACKING_MONSTERS;
                                    }
                                }
                            }
                            return State.WAITING_FOR_LOOT;

                        }

                    } else {
                        return State.WALK_TO_AREA;
                    }

                } else {
                    return State.GET_REQUIRED_ITEMS;
                }

            }
        }
    }




    // This method will set some booleans for if you should hover, or right click hover the next target. Call it when you want to ask "Should I hover my next target?"
    public void setHoverAndMenuOpenBooleans() {
        this.shouldHover = abcInstance.shouldHover();
        this.shouldOpenMenu = abcInstance.shouldOpenMenu();
    }

    // this will be called when you want to hover the next target. Always call this Anytime you might want to hover the next target. The above method will determine if you // do or not
    public void executeHoverOrMenuOpen(RSObject target) {
        if (Mouse.isInBounds() && this.shouldHover) {
            Clicking.hover(target);
            if (this.shouldOpenMenu)
                if (!ChooseOption.isOpen())
                    DynamicClicking.clickRSObject(target, 3);
        }
    }

    @Override
    public void onPaint(Graphics g) {
        NumberFormat.getInstance();
        long runtime = System.currentTimeMillis() - START_TIME;
        long idletime = (System.currentTimeMillis() - LAST_BREAK);
        g.drawString("Running for: " + Timing.msToString(runtime), 5, 80);

        g.drawString("State: " + scriptState, 5, 120);
        g.drawString("Status: " + status, 5, 140);

        g.drawString("idle time: " + IDLE_TIMER, 5, 160);
        g.drawString("time since last idle: " + Timing.msToString(idletime), 5, 180);

        g.drawString("last Item price:  " + itemPrice, 5, 220);
        g.drawString("miniumum loot value: " + lootAnyItemOverSpinner, 5, 240);

        g.drawString("hp to eat at " + eatAt, 5, 260);
        g.drawString("does loot exist " + lootExists(), 5, 280);
        //g.drawString("are we in collection area" + (collectionArea.containsMyPlayer()), 5, 300);
        //g.drawString("are we in idle area" + (idleArea.containsMyPlayer()), 5, 320);



        if (clickingTile != null) {
            g.drawPolygon(Projection.getTileBoundsPoly(clickingTile, 0));

        }
        if (idleArea != null && gui.isOpen()) {
            // g.drawPolygon(Projection.getTileBoundsPoly(areaIdle.polygon.getBounds(), 0));
            /*
             for (RSTile tile : idleArea.getAllTiles()) {
                if (tile.isOnScreen()) {
                    g.setColor(Color.green);
                    g.drawPolygon(Projection.getTileBoundsPoly(tile.getPosition(), 0));
                }
            }
             */
            /*
            for (WorldTile tile : idleArea.getAllTiles()) {
                if (tile.isVisible()) {
                    g.setColor(Color.green);
                    g.drawPolygon(Projection.getTileBoundsPoly((Positionable) tile.toLocalTile().toWorldTile(), 0));
                }
            }

             */

        }

        if (collectionArea != null && gui.isOpen()) {
            // g.drawPolygon(Projection.getTileBoundsPoly(areaIdle.polygon.getBounds(), 0));
            for (WorldTile tile : collectionArea.getAllTiles()) {
                if (tile.isVisible()) {
                    g.setColor(Color.white);
                    g.drawPolygon(Projection.getTileBoundsPoly((Positionable) tile.toLocalTile().toWorldTile(), 0));
                }
            }
        }


    }


    // As an extra, when your player just sits still, you want to be calling this:
    public void performTimedActions() {


        if (abcInstance.shouldCheckXP()) {
            abcInstance.checkXP();
            General.sleep(General.randomSD(750, 1500, 1000, 150)); // sleep makes sure it checks xp longer.
        }

        if (abcInstance.shouldExamineEntity())
            abcInstance.examineEntity();

        if (abcInstance.shouldMoveMouse())
            abcInstance.moveMouse();

        if (abcInstance.shouldPickupMouse())
            abcInstance.pickupMouse();

        if (abcInstance.shouldRightClick())
            abcInstance.rightClick();

        if (abcInstance.shouldRotateCamera())
            abcInstance.rotateCamera();


        if (abcInstance.shouldLeaveGame())

            abcInstance.leaveGame();

    }


    public enum State {
        LOOTING, WALKING_TO_GE, DEPOSIT, CLOSE_BANK, WALK_TO_AREA, DROP_TRASH, ROAM_AREA,
        EATING_FOR_INVENTORY_SPACE, SETTING_GUI, DRINKING_POTIONS, AUTO_RETALIATING, ATTACKING_MONSTERS,
        ESCAPING_FROM_PKERS, LEAVING_DANGEROUS_AREAS, SETTING_UP_INVENTORY, SETTING_UP_GEAR, ENDING_SCRIPT,
        DROPPING_EMPTY_FOOD_CONTAINERS,
        ESCAPE, TELEGRAB_FAILSAFE, TELEGRABBING, ALCHING, LOOKING_FOR_EXPENSIVE_LOOT,
        IDLING, OUT_OF_SUPPLIES, WAITING_FOR_LOOT, GET_REQUIRED_ITEMS, HOPPING_WORLDS, POISONED, HEAL,

        DEPOSIT_LOOT, PREPPING_ACCOUNT_AT_BANK, CONFIRM
    }


}
