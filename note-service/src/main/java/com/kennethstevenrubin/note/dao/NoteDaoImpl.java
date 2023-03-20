package com.kennethstevenrubin.note.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.env.Environment;

import com.kennethstevenrubin.note.entity.NoteData;
import com.kennethstevenrubin.note.mapper.NoteRowMapper;
import org.springframework.stereotype.Service;

@Service
public class NoteDaoImpl implements NoteDao {

	private ConnectionPool postgresCP = null;
	private ConnectionPool sallyCP = null;

	public NoteDaoImpl(Environment env) {

		////////////////////////////
		// Ensure sally DB exists.
		// Note: assume postgres DB exists....
		this.postgresCP = new ConnectionPool(env, "postgres");
		this.sallyCP = new ConnectionPool(env, "sally");

		/* comment in to drop sally DB.
		try(
				Connection conn = this.postgresCP.getConnection();
				Statement stmt = conn.createStatement();
		) {

			String sql = "DROP DATABASE sally;";
			stmt.executeUpdate(sql);
		} catch (SQLException ee) {

			System.out.println("Bootstrap error: " + ee.getMessage() + ".");
		}
		*/

		// Ensure DB exists.
		try(
				Connection conn = this.sallyCP.getConnection();
				Statement stmt = conn.createStatement();
		) {

			System.out.println("Database exists.");
		} catch (SQLException e) {

			System.out.println("Database does not exist...");

			try(	// Note: this is the only time to use the postgres DB.
					Connection connP = this.postgresCP.getConnection();
					Statement stmt = connP.createStatement();
			) {

				String sql = "CREATE DATABASE sally;";
				stmt.executeUpdate(sql);
				System.out.println("...database created.");
			} catch (SQLException ee) {

				System.out.println("Bootstrap error: " + ee.getMessage() + ".");
			}
		}

		////////////////////////////
		// Ensure notes table exists.
		try (
				Connection conn = this.sallyCP.getConnection();
				Statement stmt = conn.createStatement();
		) {

			String sql = "SELECT EXISTS (\n" +
					"    SELECT FROM \n" +
					"        pg_tables\n" +
					"    WHERE \n" +
					"        schemaname = 'public' AND \n" +
					"        tablename  = 'notes'\n" +
					"    );";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			boolean tableExists = rs.getBoolean(1);
			if (tableExists) {

				System.out.println("Table exists.");
			} else {

				System.out.println("Table does not exist...");

				// Create table.
				sql = "CREATE TABLE notes\n" +
						"(\n" +
						" id INT GENERATED ALWAYS AS IDENTITY,\n" +
						" title varchar(1000) NOT NULL,\n" +
						" body text NOT NULL,\n" +
						" PRIMARY KEY (id)\n" +
						");";
				stmt.execute(sql);

				System.out.println("...table created.");
			}
		} catch (SQLException e) {

			System.out.println("Bootstrap error: " + e.getMessage() + ".");
		}
	}

	@Override
	public List<NoteData> findAll() throws SQLException {

		final List<NoteData> listNotes = new ArrayList<>();
		try (
				Connection conn = this.sallyCP.getConnection();
				Statement stmt = conn.createStatement();
		) {

			final String sql = "SELECT * FROM notes";
			ResultSet rs = stmt.executeQuery(sql);
			NoteRowMapper nrm = new NoteRowMapper();
			while (rs.next()) {

				listNotes.add(nrm.mapRow(rs, 0));
			}
		}
		return listNotes;
	}

	@Override
	public int insertNote(NoteData note) throws SQLException {

		final String sql = "INSERT INTO notes (title, body) VALUES (?, ?);";
		try (
				Connection conn = this.sallyCP.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
		) {

			stmt.setString(1, note.getTitle());
			stmt.setString(2, note.getBody());
			return stmt.executeUpdate();
		}
	}
	
	@Override
	public int updateNote(NoteData note) throws SQLException {

		final String sql = "UPDATE notes SET title = ?, body = ? WHERE id = ?";
		try (
				Connection conn = this.sallyCP.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
		) {

			stmt.setString(1, note.getTitle());
			stmt.setString(2, note.getBody());
			stmt.setInt(3, note.getId());
			return stmt.executeUpdate();
		}
	}

	@Override
	public int deleteNote(NoteData note) throws SQLException {

		final String sql = "DELETE FROM notes WHERE id = ?";
		try (
				Connection conn = this.sallyCP.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
		) {

			stmt.setInt(1, note.getId());
			return stmt.executeUpdate();
		}
	}
}
