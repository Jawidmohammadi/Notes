package com.jawidmohammadi.notes;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities =  {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

  private static NoteDatabase instance;



}
