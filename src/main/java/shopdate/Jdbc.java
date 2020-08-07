package shopdate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Jdbc {
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

//	String DB_URL = "jdbc:postgresql://localhost:5432/chen";
//	String USER = "postgres";
//	String PASS = "postgres";
	
	String DB_URL = "jdbc:mysql://localhost:3306/shop";
    String USER = "root";
    String PASS = "213sos1995";

	public Connection getDb() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
//		Class.forName("org.postgresql.Driver");
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				stmt = conn.createStatement();
			} catch (SQLException ex) {
				Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return conn;
	}

	public void closeDb() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public void insertShopDate(Shop sp) throws SQLException {
		String sql = "insert into t_shopinfo(name,tel,address)values('";
		sql += sp.getName() + "','";
		sql += sp.getTel() + "','";
		sql += sp.getAddress() + "')";
		System.out.println(sql);
		stmt.executeUpdate(sql);
	}
}
