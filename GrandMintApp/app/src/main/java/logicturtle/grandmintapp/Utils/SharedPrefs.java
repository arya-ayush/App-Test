package logicturtle.grandmintapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import logicturtle.grandmintapp.GrandMintApplication;

/**
 * Created by user on 03-Feb-18.
 */

public class SharedPrefs {

    private static final String NAME = "name";


    private SharedPrefs() {

    }

    private static SharedPreferences getPrefrences() {
        Context context = GrandMintApplication.getAppContext();
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static String getUserName() {
        return getPrefrences().getString(NAME, null);
    }

    public static void setUserNsame(String name) {
        SharedPreferences.Editor editor = getPrefrences().edit();
        editor.putString(NAME, name);
        editor.commit();
    }

}
