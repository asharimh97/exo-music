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

    public static String CURRENT_MEDIA ;
    public static String CURRENT_MEDIA_TITLE ;
    public static String CURRENT_MEDIA_DAERAH ;

    public static String WILL_PLAY = "WILL_PLAY" ;
    public static String WILL_PLAY_TITLE = "WILL_PLAY_TITLE" ;
    public static String WILL_PLAY_DAERAH = "WILL_PLAY_DAERAH" ;

    public static String NEXT_SONG = "WILL_PLAY" ;
    public static String PREV_SONG = "WILL_PLAY" ;

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
