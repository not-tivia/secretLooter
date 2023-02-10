package scripts.aLooter.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class Constants {



    public final static RSArea PRESET_GRAND_EXCHANGE = new RSArea(
            new RSTile[] {
                    new RSTile(3148, 3515, 0),
                    new RSTile(3141, 3493, 0),
                    new RSTile(3144, 3489, 0),
                    new RSTile(3141, 3473, 0),
                    new RSTile(3161, 3469, 0),
                    new RSTile(3185, 3470, 0),
                    new RSTile(3186, 3500, 0),
                    new RSTile(3195, 3509, 0),
                    new RSTile(3174, 3514, 0)
            }
    );

    public final static RSArea areaGrandExchange = new RSArea(
            new RSTile[]{
                    new RSTile(3148, 3515, 0),
                    new RSTile(3141, 3493, 0),
                    new RSTile(3144, 3489, 0),
                    new RSTile(3141, 3473, 0),
                    new RSTile(3161, 3469, 0),
                    new RSTile(3185, 3470, 0),
                    new RSTile(3186, 3500, 0),
                    new RSTile(3195, 3509, 0),
                    new RSTile(3174, 3514, 0)
            }
    );



    //idle area for middle of ge
    /*
    public final static RSArea areaIdle = new RSArea(
            new RSTile[]{
                    new RSTile(3174, 3487, 0),
                    new RSTile(3168, 3480, 0),
                    new RSTile(3160, 3480, 0),
                    new RSTile(3155, 3486, 0),
                    new RSTile(3155, 3493, 0),
                    new RSTile(3162, 3490, 0),
                    new RSTile(3163, 3487, 0)
            }
    );

     */

    //public final static RSArea PRESET_GRAND_EXCHANGE_PVP_WORLD;

    // public final static RSArea PRESET_EDGEVILLE;

    //


    /*
    	    GE normal
			GE PVP World
			Edgevillie
			Custom PVP Location
			Custom safe location
			Custom PVE location
			PVP clan looting
     */
}
