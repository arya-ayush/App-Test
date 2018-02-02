package logicturtle.internshalaapp.Database.DataAccessObject;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import logicturtle.internshalaapp.Database.Model.RegisterEvent;

/**
 * Created by user on 31-Jan-18.
 */

@Dao
public interface RegisterDao {

    @Insert
    void insertAll(RegisterEvent... events);

    @Query("SELECT * FROM registers_event where user_id LIKE  :userId")
    List<RegisterEvent> getEvent(int userId);



}
