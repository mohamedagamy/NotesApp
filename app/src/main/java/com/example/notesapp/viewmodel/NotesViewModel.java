package com.example.notesapp.viewmodel;

import android.app.Application;

import com.example.notesapp.room.Note;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NotesViewModel extends AndroidViewModel {

    private LiveData<List<Note>> noteLiveData;
    private NotesRepositiory notesRepositiory;

    public NotesViewModel(@NonNull Application application) {
        super(application);

        //Here we call Repository
        notesRepositiory = new NotesRepositiory(application);
        noteLiveData = notesRepositiory.getAllNotes();

    }

    public LiveData<List<Note>> getNoteLiveData() {
        return noteLiveData;
    }

    public void deleteAllNotes() {
        notesRepositiory.deleteAllNotes();
    }

    public void insert(Note note) {
        notesRepositiory.insert(note);
    }

    public void update(Note note) {
        notesRepositiory.update(note);
    }

    public void delete(Note note) {
        notesRepositiory.delete(note);
    }


}
