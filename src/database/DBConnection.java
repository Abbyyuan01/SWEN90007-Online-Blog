package database;
import java.sql.*;

public class DBConnection {

	private static final String DB_CONNECTION = "jdbc:postgresql://ec2-174-129-27-158.compute-1.amazonaws.com:5432/da0u8lk1euv289";
	
	private static final String DB_USER = "ryqoqnpycwtmcx";
	private static final String DB_PASSWORD = "7cce20f63a035c860a51c3b02ee51ad3158d038db08cacfe77d314c2cce28e99";
	
	private static Connection dbConnection;
		
	public static PreparedStatement prepare(String stm) throws SQLException{
		
		PreparedStatement preparedStatement = null;
		
		try {
			Connection dbConnection = getDBConnection();
			
			preparedStatement = dbConnection.prepareStatement(stm);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return preparedStatement;
	}
	
	public static final Connection getDBConnection() {
		
		if (dbConnection == null) {
			try {
				DriverManager.registerDriver(new org.postgresql.Driver());
				
				dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
				
				//dbConnection.setAutoCommit(false);
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return dbConnection;
		
	}
}
