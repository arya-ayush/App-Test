package logicturtle.internshalaapp.Database.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by user on 31-Jan-18.
 */

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "uid", childColumns = "user_id", onDelete = CASCADE),tableName="registers_event")
public class RegisterEvent {

    @PrimaryKey(autoGenerate = true)
    private int rid;

    @ColumnInfo(name = "user_id")
    private int userId;

    @ColumnInfo(name = "event_id")
    private int eventId;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
