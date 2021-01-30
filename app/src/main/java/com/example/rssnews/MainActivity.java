package com.example.rssnews;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements Adapter.OnClickListener {

    private RecyclerView recyclerView;
    Adapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    // Firebase Realtime Database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a instance of the database and get
        // its reference
        mbase = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recyclerView);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<news> options = new FirebaseRecyclerOptions.Builder<news>()
                .setQuery(mbase, news.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new Adapter(options, this);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onClick(String url) {
        String TAG = "TAG";
        Log.d(TAG, "onClick: clicked" + url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        this.startActivity(browserIntent);
    }
}  //end class