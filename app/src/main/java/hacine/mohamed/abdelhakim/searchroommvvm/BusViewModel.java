package hacine.mohamed.abdelhakim.searchroommvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class BusViewModel extends AndroidViewModel {
    MutableLiveData<List<Bus>> searchData = new MutableLiveData<>();
    private BusRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<Bus>> mAllWords;


    public BusViewModel(Application application) {
        super(application);
        mRepository = new BusRepository(application);
        mAllWords = mRepository.getAllWords();


    }

    LiveData<List<Bus>> getAllWords() {
        return mAllWords;
    }

//    void insert(Bus bus) {
//        mRepository.insert(bus);
//    }

// for search
    public void refresh(String searchText) {
        String formattedSearchText = "%" + searchText + "%";
      new GetSearchDataTask().execute(formattedSearchText);
    }

// perforn=m search on background thread
    private class GetSearchDataTask extends AsyncTask<String, Void,     List<Bus> > {

        @Override
        protected    List<Bus>  doInBackground(String... params) {
            List<Bus>  r1 = mRepository.getAllSearch(params[0]);

            return r1;
        }



        protected void onPostExecute(   List<Bus> data) {
            super.onPostExecute( data);
            searchData.setValue(  data); //change LiveData value

        }
    }

// this function and class bleow populate data when app first lunches ... if u dont use it u will see emty Recycler view but u still can search
    public void populaterecyclerview() {

        new PupulatData().execute();
    }


    private class PupulatData extends AsyncTask<Void, Void,     List<Bus> > {

        @Override
        protected    List<Bus>  doInBackground(Void... params) {
            List<Bus>  r1 = mRepository.Getdata();

            return r1;
        }



        protected void onPostExecute(   List<Bus> data) {
            super.onPostExecute( data);
            searchData.setValue(  data); //change LiveData value

        }
    }





}
