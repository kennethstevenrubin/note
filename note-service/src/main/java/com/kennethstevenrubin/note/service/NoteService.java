package com.kennethstevenrubin.note.service;

import java.sql.SQLException;
import java.util.List;

import com.kennethstevenrubin.note.entity.NoteData;

public interface NoteService {

	List<NoteData> findAll() throws SQLException;

	int insertNote(NoteData note) throws SQLException;

	int updateNote(NoteData note) throws SQLException;

	int deleteNote(NoteData note) throws SQLException;
	
}
