package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class AdminAuthDAO {

	private static Connection conn;
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/gamezonedb";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "admin";

	public static Map<String, String> checkUserPass(String username, String password) {
		String query = "select firstname, lastname " + "from user_admin " + "where username ='"
				+ username + "' and password ='" + password + "'",
				firstName = "",
				lastName = "";
		Map<String, String> adminInfo = null;
		getDbConnection();
		Statement statement = null;
		ResultSet rs = null;

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			if(rs.next()){
				firstName = rs.getString("firstname");
				lastName = rs.getString("lastname");
				adminInfo = new HashMap<String, String>(3);
				adminInfo.put("loggedIn", "true");
				adminInfo.put("firstName", firstName);
				adminInfo.put("lastName", lastName);
			}
			statement.close();
			rs.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return adminInfo;
	}

	private static void getDbConnection() {
		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}
	}
	
	public static void DB_Close() throws Throwable{
		if(conn != null){
			conn.close();
		}
	}
};
