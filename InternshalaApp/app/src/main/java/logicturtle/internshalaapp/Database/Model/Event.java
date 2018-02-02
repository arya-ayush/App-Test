package logicturtle.internshalaapp.Database.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by user on 27-Jan-18.
 */

@Entity(tableName = "events")
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int eid;

    public void setEid(int eid) {
        this.eid = eid;
    }

    @ColumnInfo(name = "name")
    private String name;

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "venue")
    private String venue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getEid() {
        return eid;
    }

}
