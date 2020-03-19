package com.jawidmohammadi.notes;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities =  {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

  private static NoteDatabase instance; // we create this becasue singleton we cant do multiple instance we use this instance evrey where

  public abstract NoteDao noteDao(); // abstract because it doesnt have any body room will take care of that.

  // we create singleton set only one thread at a time synchronized avoid that
  static synchronized NoteDatabase getInstance(Context context){
    if (instance == null){
      instance = Room.databaseBuilder(context.getApplicationContext(),
          NoteDatabase.class, "note database")
          .fallbackToDestructiveMigration()
          .addCallback(roomCallBack)
          .build();
    }
    return instance;
  }

  private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
    }
  };


  private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
    private NoteDao noteDao;

    private PopulateDbAsyncTask(NoteDatabase db){
      noteDao = db.noteDao();
    }

    @Override
    protected Void doInBackground(Void... voids) {
     noteDao.insert(new Note("Title 1", "Description 1", 1));
      noteDao.insert(new Note("Title 2", "Description 2", 2));
      noteDao.insert(new Note("Title 3", "Description 3", 3));
      return null;
    }
  }

  }
