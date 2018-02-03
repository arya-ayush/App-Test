package logicturtle.grandmintapp;

import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;

/**
 * Created by user on 03-Feb-18.
 */

public class GrandMintApplication extends Application {

    private static Context sContext;

    public static Context getAppContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }


}
