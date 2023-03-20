package com.kennethstevenrubin.note.dao;

import java.sql.SQLException;
import java.util.List;

import com.kennethstevenrubin.note.entity.NoteData;

public interface NoteDao {

	List<NoteData> findAll() throws SQLException;

	int insertNote(NoteData note) throws SQLException;

	int updateNote(NoteData note) throws SQLException;

	int deleteNote(NoteData note) throws SQLException;
}
