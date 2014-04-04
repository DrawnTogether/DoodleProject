package com.doodleproject.dt.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.doodleproject.dt.client.GreetingService;
import com.doodleproject.dt.shared.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
GreetingService {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/doodle";
	//  Database credentials
	static final String USER = "root";
	static final String PASS = "";
	static final String TABLENAME = "users";
	static final String TABLENAME2 = "events";
	static final String TABLENAME3 = "sondaggio";
	static final String TABLENAME4 = "commenti";


	@Override
	public boolean registration(User user) throws IllegalArgumentException {
		Connection conn = null;
		PreparedStatement  statement = null;
		String createTable = "CREATE TABLE IF NOT EXISTS "+TABLENAME + " (name VARCHAR(255), username VARCHAR(255), password VARCHAR(255), mail VARCHAR(255))"; 
		try {
			//COME SCRIVERE import com.mysql.jdbc.Driver caricata in modo dinamico
			Class.forName("com.mysql.jdbc.Driver");
			conn =  DriverManager.getConnection(DB_URL, USER, PASS);
			//CONTROLLO INJECTION E SE LA TABELLA ESISTE
			statement = conn.prepareStatement(createTable);
			statement.executeUpdate();
			//CONTROLLO SE username ESISTE 
			if(usernameExist(conn, user.getUsername())){
				System.out.println("username gia' esistente");
				return false;
			}
			//INSERIMENTO NUOVO USER
			else{
				String insertTableSQL = "INSERT INTO "+TABLENAME+
						"(name, username, password, mail) VALUES(?,?,?,?)";
				statement = conn.prepareStatement(insertTableSQL);
				statement.setString(1, user.getName());
				statement.setString(2,user.getUsername());
				statement.setString(3, user.getPassword());
				statement.setString(4, user.getMail());
				statement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean usernameExist(Connection conn,String nickname) throws SQLException{
		PreparedStatement  statement = null;
		String username = "empty";
		String selectSQL = "SELECT name FROM "+TABLENAME+" WHERE username=? ";
		statement = conn.prepareStatement(selectSQL);
		statement.setString(1, nickname);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			username = rs.getString(1);
		}
		if(username.contentEquals("empty"))
			return false;
		else return true;

	}

	@Override
	public boolean login(User user) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		PreparedStatement  statement = null;
		conn =  DriverManager.getConnection(DB_URL, USER, PASS);
		String password = "empty";
		String selectSQL = "SELECT username,password FROM "+TABLENAME+" WHERE username=? ";
		statement = conn.prepareStatement(selectSQL);
		statement.setString(1, user.getUsername());
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			password = rs.getString(2);
		}
		return (password.contentEquals(user.getPassword()) ? true:false);

	}
}
