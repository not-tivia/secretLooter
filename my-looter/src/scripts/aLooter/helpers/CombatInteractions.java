package scripts.aLooter.helpers;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSCharacter;
import org.tribot.api2007.types.RSNPC;

public class CombatInteractions {

    public static boolean isFighting(){
        RSCharacter ch = Player.getRSPlayer().getInteractingCharacter();

        if (ch== null){
            return false;
        }
        if (ch != null && ch.getPosition().distanceTo(Player.getPosition()) <= 4 && ch.isInCombat()){
            if (Timing.waitCondition(() -> ch != null && ch.getPosition().distanceTo(Player.getPosition()) <= 4 && ch.isInCombat(), General.random(2000, 3000))){
                return true;
            }
        }
        return false;

    }

    public static boolean isUnderAttackByTarget(){
        RSNPC[] attackingme = NPCs.getAll();
        return attackingme.length > 0 && attackingme[0].isInteractingWithMe() && Player.getPosition().distanceTo(attackingme[0]) <= 1 && PathFinding.canReach(attackingme[0], false);
    }



}
