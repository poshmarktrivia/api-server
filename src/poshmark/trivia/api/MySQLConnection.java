package poshmark.trivia.api;

import java.sql.*;  

public class MySQLConnection{
	
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    public Connection createConnection() throws Exception {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/poshmarktrivia?serverTimezone=UTC","root","");
			
			
		} catch (SQLException e) {
			throw e;
		}finally {
			
        }
		return connect;  
    }
    
    public ResultSet getData(Connection connect,String query) throws Exception {
    	try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			throw e;
		}finally {
        }
    	return resultSet;
    }
    
    public void updateOrDeleteDate(Connection connect,String query) throws Exception {
    	try {
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}finally {

        }
    }
	
    public void closeConnection(Connection connect)throws Exception {
    	try {
			connect.close();
		} catch (SQLException e) {
			throw e;
		}
    }
	 
}
