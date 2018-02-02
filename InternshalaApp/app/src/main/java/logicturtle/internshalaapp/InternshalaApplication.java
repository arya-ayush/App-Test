package logicturtle.internshalaapp;

import android.app.Application;
import android.content.Context;

import logicturtle.internshalaapp.Database.DatabaseHolder.AppDatabase;

/**
 */

public class InternshalaApplication extends Application {

    private static Context sContext;

    private static AppDatabase appDatabase = null;

    public static Context getAppContext() {
        return sContext;
    }

    public static AppDatabase getAppDataBase() {
        return appDatabase;
    }


    @Override
    public void onCreate() {
        super.onCreate();
     //   appDatabase = AppDatabase.getAppDatabase(sContext);
        sContext = getApplicationContext();
    }

}
