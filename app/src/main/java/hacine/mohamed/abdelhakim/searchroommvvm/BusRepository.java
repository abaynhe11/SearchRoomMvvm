package hacine.mohamed.abdelhakim.searchroommvvm;


import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

class BusRepository {

    private BusDao mBusDao;
    private LiveData<List<Bus>> mAllWords;
    private MutableLiveData<ArrayList<Bus>> jmAllWords;
    // Note that in order to unit test the BusRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    BusRepository(Application application) {
        BusRoomDatabase db = BusRoomDatabase.getDatabase(application);
        mBusDao = db.wordDao();
        mAllWords = mBusDao.getAlphabetizedBuses();


    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Bus>> getAllWords() {
        return mAllWords;
    }

    MutableLiveData<ArrayList<Bus>> getAllWordsd() {
        return jmAllWords;
    }

    List<Bus> getAllSearch(String search) {
        return mBusDao.getSearchResults(search);
    }

    List<Bus> Getdata() {
        return mBusDao.getResults();
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Bus bus) {
        BusRoomDatabase.databaseWriteExecutor.execute(() -> {
            mBusDao.insert(bus);
        });
    }
}