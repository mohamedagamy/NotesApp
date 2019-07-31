package com.example.notesapp.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.notesapp.R;
import com.example.notesapp.room.Note;
import com.example.notesapp.viewmodel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesAdapter.OnItemClickListener, LifecycleOwner {
    NotesViewModel notesViewModel;
    NotesAdapter notesAdapter;
    RecyclerView notesRecyclerView;
    FloatingActionButton fab;

    public static final String EXTRA_TITLE = "com.example.notesapp.ui.EXTRA_TITLE";
    public static final String EXTRA_DESC = "com.example.notesapp.ui.EXTRA_DESC";
    public static final String EXTRA_ORDER = "com.example.notesapp.ui.EXTRA_ORDER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        notesViewModel.getNoteLiveData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                if(notesAdapter != null)
                    notesAdapter.submitList(notes);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Add New Note", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,AddNoteActivity.class);
                startActivityForResult(intent,101);
            }
        });
    }

    private void init() {

        notesRecyclerView = findViewById(R.id.rv_notes);
        fab = findViewById(R.id.fab_add_note);
        notesAdapter = new NotesAdapter(this);

        notesRecyclerView.setAdapter(notesAdapter);
        notesRecyclerView.setHasFixedSize(true);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void OnNoteClick(Note note) {
        Toast.makeText(this, "Note Clicked: "+note.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(MainActivity.this,UpdateNoteActivity.class);
        intent.putExtra(EXTRA_TITLE,note.getTitle());
        intent.putExtra(EXTRA_DESC,note.getDescription());
        intent.putExtra(EXTRA_ORDER,note.getPriority());
        startActivityForResult(intent,102);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_item_delete:
            notesViewModel.deleteAllNotes();
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 101:
                    Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show();
                    break;
                case 102:
                    Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
                    break;
                default:
            }
        } else {
            Toast.makeText(this, "Note not saved/updated", Toast.LENGTH_SHORT).show();
        }

    }
}
