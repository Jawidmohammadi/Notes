package com.jawidmohammadi.notes;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private NoteViewModel noteViewModel;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setHasFixedSize(true);

    final NoteAdapter adapter = new NoteAdapter();
    recyclerView.setAdapter(adapter);

    noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
    noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>(){
      @Override
      public void onChanged(@NonNull List<Note> notes) {
        adapter.setNotes(notes);
// update recycler view

      }
    });
  }

}
