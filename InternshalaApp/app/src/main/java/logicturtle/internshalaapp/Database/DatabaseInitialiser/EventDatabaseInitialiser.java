package logicturtle.internshalaapp.Database.DatabaseInitialiser;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;
import logicturtle.internshalaapp.Database.DatabaseHolder.AppDatabase;
import logicturtle.internshalaapp.Database.Model.Event;

/**
 * Created by user on 27-Jan-18.
 */

public class EventDatabaseInitialiser {

    public static void populateAsync(@NonNull final AppDatabase db, Event event) {
        EventDatabaseInitialiser.PopulateDbAsync task = new EventDatabaseInitialiser.PopulateDbAsync(db, event);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db, Event event) {
        populateWithData(db, event);
    }


    private static Event addEvent(final AppDatabase db, Event event) {
        db.eventDao().insertAll(event);
        return event;
    }

    private static void populateWithData(AppDatabase db, Event event) {
        addEvent(db, event);
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;
        private final Event event;

        PopulateDbAsync(AppDatabase db, Event event) {
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
