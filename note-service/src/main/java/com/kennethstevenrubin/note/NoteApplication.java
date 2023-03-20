package com.kennethstevenrubin.note;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

@SpringBootApplication(scanBasePackages="com.kennethstevenrubin")
public class NoteApplication {

	public static void main(String[] args) {

		// Now start up the spring boot app....
		SpringApplication.run(NoteApplication.class, args);
	}
}
