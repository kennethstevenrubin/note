package com.kennethstevenrubin.note.controller;

import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kennethstevenrubin.note.entity.NoteData;
import com.kennethstevenrubin.note.service.NoteService;

@RestController
@RequestMapping("/note/v1")
public class ApplicationController {

	@Autowired
	NoteService NoteService;

	@CrossOrigin()
	@GetMapping(value = "/getNotes", produces = "application/json")
	public List<NoteData> getNotes(HttpServletRequest request) throws SQLException {

		List<NoteData> noteList = NoteService.findAll();
		if (!noteList.isEmpty()) {

			noteList.get(0).setBody(noteList.get(0).getBody() + request.getRequestURL().toString());
		}
		return noteList;
	}

	@CrossOrigin()
	@PostMapping(value = "/createNote", produces = "application/json")
	public int createNote(@RequestBody NoteData note) throws SQLException {

		return NoteService.insertNote(note);
	}

	@CrossOrigin()
	@RequestMapping(method = RequestMethod.PUT, path = "/updateNote", produces = "application/json")
	public int updateNote(@RequestBody NoteData note) throws SQLException {

		 return NoteService.updateNote(note);
	}

	@CrossOrigin()
	@DeleteMapping(value = "/deleteNote", produces = "application/json")
	public int deleteNote(@RequestBody NoteData note) throws SQLException {

		 return NoteService.deleteNote(note);
	}
}
