package com.example.notesapp.viewmodel;

import android.content.Context;

import com.example.notesapp.room.AppDatabase;
import com.example.notesapp.room.Note;
import com.example.notesapp.room.NoteDao;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class NotesRepositiory {

    private AppDatabase appDatabase;
    private NoteDao noteDao;
    private LiveData<List<Note>> noteLiveData;
    public NotesRepositiory(Context context) {
        appDatabase = AppDatabase.getInstance(context);
        noteDao = appDatabase.noteDao();
        noteLiveData = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        insertDummyData();
        return noteLiveData;
    }

    private void insertDummyData(){
        for (int i = 0; i < 100; i++) {
            Note note = new Note("title "+i,"Desc "+i,100);
            //noteList.add(note);
            if(noteDao != null)
                noteDao.insert(note);
        }
    }

    public void deleteAllNotes() {
        noteDao.deleteAllNotes();
    }

    public void insert(Note note){
        noteDao.insert(note);
    }

    public void update(Note note){
        noteDao.update(note);
    }

    public void delete(Note note){
        noteDao.delete(note);
    }



}
