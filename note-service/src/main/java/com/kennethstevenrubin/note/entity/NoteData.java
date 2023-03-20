package com.kennethstevenrubin.note.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class NoteData {

	private int id;

	private String title;

	private String body;
}
