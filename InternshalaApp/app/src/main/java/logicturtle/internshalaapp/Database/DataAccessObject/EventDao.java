package logicturtle.internshalaapp.Database.DataAccessObject;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import logicturtle.internshalaapp.Database.Model.Event;

/**
 * Created by user on 27-Jan-18.
 */
@Dao
public interface EventDao {

    @Insert
    void insertAll(Event... events);

    @Delete
    void delete(Event event);

    @Query("SELECT * FROM events")
    List<Event> getAll();

    @Query("SELECT * FROM events where eid LIKE  :eventId")
    Event getEvent(int eventId);

    @Query("SELECT COUNT(*) from events")
    int countEvents();

    @Query("DELETE FROM events")
    void deleteAll();

}
