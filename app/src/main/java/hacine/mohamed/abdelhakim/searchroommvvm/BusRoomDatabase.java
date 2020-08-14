package hacine.mohamed.abdelhakim.searchroommvvm;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.  In a real
 * app, consider exporting the schema to help you with migrations.
 */

@Database(entities = {Bus.class}, version = 1, exportSchema = false)
abstract class BusRoomDatabase extends RoomDatabase {

    abstract BusDao wordDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile BusRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static BusRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BusRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BusRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     *
     * If you want to populate the database only when the database is created for the 1st time,
     * override RoomDatabase.Callback()#onCreate
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                BusDao dao = INSTANCE.wordDao();
                // dao.deleteAll();
                List<Bus> woo = new ArrayList<>();
                woo.add(new Bus("NewYork" , "Washington","34",1));
                woo.add(new Bus("Washington" , " Brazil","25",2));
                woo.add(new Bus("texas" , "California","23",3));
                woo.add(new Bus("Seatle" , "New York ","H",4));
                woo.add(new Bus("Utah " , "Ontario","H",4));
                woo.add(new Bus("Chicago" , "Dalas","P1",5));
                woo.add(new Bus("Miami" , "SiliconValley","354",6));
                woo.add(new Bus("Dalas" , "Mexixo","341",7));
                woo.add(new Bus("NewMexico" , "Virginia ","343",8));
                woo.add(new Bus("Colorado" , "Florida","102",9));
                woo.add(new Bus("Maryland" , "Alaska","102",10));
                woo.add(new Bus("Louisiana" , "Hawaii","43",12));
                woo.add(new Bus("Ohio" , "Origon","13",12));
                woo.add(new Bus("Michigan" , "Kentucky","1",13));
                dao.insert(woo);

//                word = new Bus("World");
//                dao.insert(word);
            });
        }
    };
}
