package logicturtle.internshalaapp.Database.DataAccessObject;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import logicturtle.internshalaapp.Database.Model.User;

/**
 * Created by user on 26-Jan-18.
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user where email LIKE  :email AND password LIKE :password")
    User loginUser(String email, String password);

    @Query("SELECT COUNT(*) from user")
    int countUsers();

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user")
    void deleteAll();

}
