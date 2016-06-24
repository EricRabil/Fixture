package com.ericrabil.fixture.database.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ericrabil.fixture.Fixture;
import com.ericrabil.fixture.api.Database;
import com.ericrabil.fixture.api.Entry;
import com.ericrabil.fixture.api.exception.DatabaseExistsException;
import com.ericrabil.fixture.database.DAOException;
import com.ericrabil.fixture.database.IDatabaseDAO;

public class DBDatabaseDAO implements IDatabaseDAO{
	
	private Connection conn;
	private Fixture f;
	private SQLException exc;
	
	public DBDatabaseDAO(Fixture fixture, Connection connection){
		this.conn = connection; this.f = fixture;
	}
	
	@Override
	public Database createDatabase(String name){
		String nodename = "dat_" + name.toLowerCase().replace(" ", "_");
		if(databaseExists(name, nodename)){
			throw new DatabaseExistsException(name, nodename);
		}
		String sql = "INSERT INTO `nodes`(`node`, `nice_name`) VALUES (?,?)";
		return null;
	}
	
	private boolean databaseExists(String name, String id){
		return false;
	}
	
	@Override
	public ArrayList<Entry> getEntries(Database db) throws DAOException {
		ArrayList<Entry> entries = new ArrayList<Entry>();
		String sql = "SELECT * FROM `data` WHERE node = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, db.getSQLID());
			stmt.execute();

			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					entries.add(new Entry(rs.getString("id"), rs.getString("val"), rs.getInt("uniqueid"), rs.getInt("db_uuid")));
				}
			}
		} catch (SQLException e) {
			this.exc = e;
			throw new DAOException(sql, e);
		}
		return entries;
	}

	@Override
	public ArrayList<Database> getDatabases() throws DAOException {
		ArrayList<Database> dbs = new ArrayList<Database>();
		String sql = "SELECT * FROM `nodes`";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.execute();

			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					Database db = new Database(this.f, rs.getString("node"), rs.getString("nice_name"), null, rs.getInt("identifier"));
					db.setEntries(getEntries(db));
					dbs.add(db);
				}
			}
		} catch (SQLException e) {
			this.exc = e;
			throw new DAOException(sql, e);
		}
		
		return dbs;
	}

	@Override
	public void addEntry(String key, String value, Database db) throws DAOException {
		String sql = "INSERT INTO `data`(`node`, `db_uuid`, `id`, `val`) VALUES ('" + db.getSQLID() + "', ?, ?, ?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, db.getUUID());
			stmt.setString(2, key);
			stmt.setString(3, value);
			stmt.execute();
			f.updateDatabases();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void removeEntry(Entry e) throws DAOException {
		String sql = "DELETE FROM `data` WHERE `uniqueid`=?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setInt(1, e.getUUID());
			stmt.execute();
			f.updateDatabases();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	@Override
	public void updateEntry(Entry e, String key, String value) throws DAOException {
		String sql = "UPDATE `data` SET `id`=?,`val`=? WHERE uniqueid=?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, key);
			stmt.setString(2, value);
			stmt.setInt(3, e.getUUID());
			stmt.execute();
			f.updateDatabases();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}

}
