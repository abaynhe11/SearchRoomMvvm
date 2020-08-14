package hacine.mohamed.abdelhakim.searchroommvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BusViewModel mBusViewModel;
    EditText etFilter;
     RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etFilter = findViewById(R.id.editsearch);
        recyclerView = findViewById(R.id.recyclerView99);

        inirvbus();

    }
    private  void inirvbus(){
        final BusListAdapter adapter = new BusListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Get a new or existing ViewModel from the ViewModelProvider.
        mBusViewModel = new ViewModelProvider(this).get(BusViewModel.class);
        mBusViewModel.populaterecyclerview();

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mBusViewModel.searchData.observe(this, new Observer<List<Bus>>() {
            @Override
            public void onChanged(List<Bus> buses) {





                adapter.setWords(buses);

            }
        });
        etFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //   mBusViewModel.searchData .setValue(mRepository.getrede());
           adapter.notifyDataSetChanged();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mBusViewModel.refresh(charSequence.toString());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });




    }
}