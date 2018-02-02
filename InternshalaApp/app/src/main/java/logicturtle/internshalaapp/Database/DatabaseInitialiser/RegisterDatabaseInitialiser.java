package logicturtle.internshalaapp.Database.DatabaseInitialiser;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import logicturtle.internshalaapp.Database.DatabaseHolder.AppDatabase;
import logicturtle.internshalaapp.Database.Model.Event;
import logicturtle.internshalaapp.Database.Model.RegisterEvent;

/**
 * Created by user on 31-Jan-18.
 */

public class RegisterDatabaseInitialiser {

    public static void populateAsync(@NonNull final AppDatabase db, RegisterEvent event) {
        RegisterDatabaseInitialiser.PopulateDbAsync task = new RegisterDatabaseInitialiser.PopulateDbAsync(db, event);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db, RegisterEvent event) {
        populateWithData(db, event);
    }


    private static RegisterEvent addEvent(final AppDatabase db, RegisterEvent event) {
        db.registerDao().insertAll(event);
        return event;
    }

    private static void populateWithData(AppDatabase db, RegisterEvent event) {
        addEvent(db, event);
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;
        private final RegisterEvent event;

        PopulateDbAsync(AppDatabase db, RegisterEvent event) {
            mDb = db;
            this.event = event;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithData(mDb, event);
            return null;
        }
    }
}
