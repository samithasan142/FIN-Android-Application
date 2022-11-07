package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class BloodSearchResult extends AppCompatActivity {

    RecyclerView recview;
    myadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_search_result);

        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        String s = getIntent().getStringExtra("type");
        s = convert(s);
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(s), model.class)
                        .build();

        adapter=new myadapter(options);
        recview.setAdapter(adapter);
    }

    private String convert(String s) {
        if(s.equals("A(ve+)")) return "a+";
        else if(s.equals("A(ve-)")) return "a-";
        else if(s.equals("B(ve+)")) return "b+";
        else if(s.equals("B(ve-)")) return "b-";
        else if(s.equals("AB(ve+)")) return "ab+";
        else if(s.equals("AB(ve-)")) return "ab-";
        else if(s.equals("O(ve+)")) return "o+";
        else return "o-";
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}