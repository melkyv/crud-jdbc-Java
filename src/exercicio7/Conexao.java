package exercicio7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static String jdbcURL = "jdbc:mysql://localhost:3306/exercicio7";
	private static String user = "melky";
	private static String password = "exercicio7pass";
	private static Connection conn;
	
	public static Connection getConexao() {
		try {
		if (conn==null) {
				conn = DriverManager.getConnection(jdbcURL, user, password);
				return conn;
		} else {
			return conn;
		}	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
