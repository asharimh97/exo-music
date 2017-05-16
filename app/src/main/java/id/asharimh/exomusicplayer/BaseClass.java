package id.asharimh.exomusicplayer;

/**
 * Created by ASHARI on 05/11/2017.
 */

public class BaseClass {

    public static String MONTSERRAT_PATH = "fonts/Montserrat.ttf" ;
    public static String MONTSERRAT_BOLD_PATH = "fonts/MontserratBold.ttf" ;
    public static String LATO_THIN_PATH = "fonts/LatoThin.ttf" ;
    public static String LATO_LIGHT_PATH = "fonts/LatoLight.ttf" ;

    public static String PAGE_ACTIVE ;

    public static String PLAY_STATE = "PAUSED" ;

    public static void togglePlayState(){
        if (PLAY_STATE == "PAUSED")
            PLAY_STATE = "PLAYING" ;
        else
            PLAY_STATE = "PAUSED" ;
    }

    public static String getPlayState(){
        return PLAY_STATE ;
    }

    public static void setPageActive(String state){
        PAGE_ACTIVE = state ;
    }

}
