package com.kennethstevenrubin.note.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.kennethstevenrubin.note.entity.NoteData;

public class NoteRowMapper implements RowMapper<NoteData> {

	@Override
	public NoteData mapRow(ResultSet rs, int arg1) throws SQLException {

		NoteData note = new NoteData();
		note.setId(rs.getInt("id"));
		note.setTitle(rs.getString("title"));
		note.setBody(rs.getString("body"));
		return note;
	}
}
