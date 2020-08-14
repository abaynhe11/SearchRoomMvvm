package hacine.mohamed.abdelhakim.searchroommvvm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BusDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from word_table")
    LiveData<List<Bus>> getAlphabetizedBuses();





    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Bus bus);

    @Query("DELETE FROM word_table")
    void deleteAll();




// if u want to modify search just modify this SQl Query
    @Query("SELECT * FROM word_table WHERE depart like:desc or arivee like:desc  or busln like:desc   " )


     List<Bus> getSearchResults(String desc) ;
//    @Query("SELECT  from word_table   LIKE :desc")
//    List<Bus> getSearchResults(String desc  ) ;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Bus> woo);



    @Query("SELECT * FROM word_table   " )
    List<Bus> getResults() ;
}
