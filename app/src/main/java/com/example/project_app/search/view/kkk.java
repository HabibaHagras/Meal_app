//package com.example.rxlab4;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.widget.EditText;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
//import io.reactivex.rxjava3.core.Observable;
//import io.reactivex.rxjava3.core.ObservableOnSubscribe;
//import io.reactivex.rxjava3.schedulers.Schedulers;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//public class MainActivity extends AppCompatActivity {
//
//    private String[] names = {"Refat", "Kotb", "Ahmed", "Mohamed", "Omar", "Mahmoud"};
//    private NameAdapter adapter;
//    private RecyclerView recyclerView;
//
//    EditText searchEditText;
//
//    @SuppressLint("CheckResult")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        searchEditText =findViewById(R.id.searchEditText);
//
//        setupRecyclerView();
//
//        // Creating an observable for text changes in the EditText
//        Observable.create((ObservableOnSubscribe<String>) emitter ->
//                        searchEditText.addTextChangedListener(new TextWatcher() {
//                            @Override
//                            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
//                            }
//
//                            @Override
//                            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
//                                emitter.onNext(charSequence.toString());
//                            }
//
//                            @Override
//                            public void afterTextChanged(Editable editable) {
//                            }
//                        }))
//                .debounce(300, TimeUnit.MILLISECONDS) // Add a debounce to avoid rapid searches
//                .map(String::toLowerCase)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(searchTerm -> {
//                    List<String> filteredNames = filterNames(searchTerm);
//                    adapter.setNames(filteredNames);
//                });
//    }
//
//    private void setupRecyclerView() {
//        recyclerView = findViewById(R.id.recyclerView);
//        adapter = new NameAdapter();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
//        recyclerView.setAdapter(adapter);
//    }
//
//    private List<String> filterNames(String searchTerm) {
//        List<String> filteredNames = new ArrayList<>();
//        for (String name : names) {
//            if (name.toLowerCase().contains(searchTerm)) {
//                filteredNames.add(name);
//            }
//        }
//        return filteredNames;
//    }
//}