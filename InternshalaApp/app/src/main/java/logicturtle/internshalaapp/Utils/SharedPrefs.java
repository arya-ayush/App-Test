package logicturtle.internshalaapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import logicturtle.internshalaapp.Database.DatabaseHolder.AppDatabase;
import logicturtle.internshalaapp.Database.Model.User;
import logicturtle.internshalaapp.InternshalaApplication;

/**
 * Created by user on 26-Jan-18.
 */

public class SharedPrefs {

    private static final String USER_PROFILE = "user";
    private static final String LOGIN_ID = "id";
    public static final int LOGIN_TOKEN = -999;


    private SharedPrefs() {

    }

    private static SharedPreferences getPrefrences() {
        Context context = InternshalaApplication.getAppContext();
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static int getLoginId() {
        return getPrefrences().getInt(LOGIN_ID, LOGIN_TOKEN);
    }

    public static void setLoginToken(int token) {
        SharedPreferences.Editor editor = getPrefrences().edit();
        editor.putInt(LOGIN_ID, token);
        editor.commit();
    }


    public static void clearLoggedInUser() {
        SharedPreferences.Editor editor = getPrefrences().edit();
        editor.remove(USER_PROFILE);
        editor.remove(LOGIN_ID);
        editor.apply();
    }

    public static User getUser() {
        Gson gson = new Gson();
        String userJson = getPrefrences().getString(USER_PROFILE, null);
        return gson.fromJson(userJson, User.class);
    }

    public static void setUser(User user, Context context) {
        AppDatabase db=AppDatabase.getAppDatabase(context);
        Gson gson = new Gson();
        SharedPreferences.Editor editor = getPrefrences().edit();
        editor.putString(USER_PROFILE, gson.toJson(user));
        editor.putInt(LOGIN_ID, db.userDao().loginUser(user.getEmail(),user.getPassword()).getUid());
        editor.apply();
    }



}
