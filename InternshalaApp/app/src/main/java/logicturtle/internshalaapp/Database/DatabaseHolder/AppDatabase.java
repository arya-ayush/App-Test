package logicturtle.internshalaapp.Database.DatabaseHolder;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import logicturtle.internshalaapp.Database.DataAccessObject.EventDao;
import logicturtle.internshalaapp.Database.DataAccessObject.RegisterDao;
import logicturtle.internshalaapp.Database.DataAccessObject.UserDao;
import logicturtle.internshalaapp.Database.Model.Event;
import logicturtle.internshalaapp.Database.Model.RegisterEvent;
import logicturtle.internshalaapp.Database.Model.User;

/**
 * Created by user on 26-Jan-18.
 */


@Database(entities = {User.class, Event.class, RegisterEvent.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();

    public abstract EventDao eventDao();

    public abstract RegisterDao registerDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
