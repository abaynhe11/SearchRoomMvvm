package hacine.mohamed.abdelhakim.searchroommvvm;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.RoomWarnings;

@Entity(tableName = "word_table")
public class Bus {

    @Ignore
    private boolean expanded;
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @PrimaryKey(autoGenerate = true )
    @NonNull
    private int id;
// depart means bus first stop

    @ColumnInfo(name = "depart")
    private String depart;

//busln  means bus ligne
    @ColumnInfo(name = "busln")
    private String busln ;

// arrive means bus last stop
    @ColumnInfo(name = "arivee")
    private String arrive;

    public Bus(@NonNull String depart, @NonNull String arrive, @NonNull String busln , @NonNull int id) {
        this.depart = depart;
        this.arrive = arrive;
        this.busln =busln ;
        this.expanded = true;
        this.id=id;
    }



    public String getBusln() {
        return busln;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setBusln(String busln) {
        this.busln = busln;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    @NonNull
    public String getArrive() {
        return arrive;
    }

    @NonNull
    public String getDepart() {
        return depart;
    }
    public boolean isExpanded() {
        return expanded;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
