package com.kennethstevenrubin.note.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kennethstevenrubin.note.dao.NoteDao;
import com.kennethstevenrubin.note.entity.NoteData;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	NoteDao NoteDao;

	@Override
	public List<NoteData> findAll() throws SQLException {

		return NoteDao.findAll();
	}

	@Override
	public int insertNote(NoteData note) throws SQLException {

		return NoteDao.insertNote(note);
	}

	@Override
	public int updateNote(NoteData note) throws SQLException {

		return NoteDao.updateNote(note);
	}

	@Override
	public int deleteNote(NoteData note) throws SQLException {

		return NoteDao.deleteNote(note);
	}
}
