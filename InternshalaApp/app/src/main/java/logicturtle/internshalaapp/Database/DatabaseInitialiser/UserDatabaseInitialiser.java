package logicturtle.internshalaapp.Database.DatabaseInitialiser;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import logicturtle.internshalaapp.Database.DatabaseHolder.AppDatabase;
import logicturtle.internshalaapp.Database.Model.User;

/**
 * Created by user on 26-Jan-18.
 */

public class UserDatabaseInitialiser {

    private static final String TAG = UserDatabaseInitialiser.class.getName();
    private static boolean LOGIN_STATUS = false;

    public static void populateAsync(@NonNull final AppDatabase db, User user) {
        PopulateDbAsync task = new PopulateDbAsync(db, user);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db, User user) {
        populateWithData(db, user);
    }

    public static List<User> getAllUsers(@NonNull final AppDatabase db) {
        return db.userDao().getAll();
    }

    public static boolean loginUser(@NonNull final AppDatabase db, String email, String password) {
        User user = db.userDao().loginUser(email, password);
        if (user!=null)
            LOGIN_STATUS = true;
        return LOGIN_STATUS;
    }

    public static User getUser(@NonNull final AppDatabase db, String email, String password) {
        User user = db.userDao().loginUser(email, password);
        return user;
    }

    private static User addUser(final AppDatabase db, User user) {
        db.userDao().insertAll(user);
        return user;
    }

    private static void populateWithData(AppDatabase db, User user) {
        addUser(db, user);

        List<User> userList = db.userDao().getAll();
        Log.d(UserDatabaseInitialiser.TAG, "Rows Count: " + userList.size());
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;
        private final User user;

        PopulateDbAsync(AppDatabase db, User user) {
            mDb = db;
            this.user = user;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithData(mDb, user);
            return null;
        }

    }
}
