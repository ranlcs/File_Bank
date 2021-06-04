import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BDDAccess{
	private static Connection conec;
	private static String url = "jdbc:mysql://localhost:3306/file_bank";
	private static String user = "root";
	private static String mdp = "12345678";

	private BDDAccess(){ 
		try{
			conec = DriverManager.getConnection(url,user,mdp);
		}catch(SQLException sql){
			System.out.println("Erreur de connection: " + sql);
		}
	}


	public static Connection getInstance(){
		if(conec==null)
			new BDDAccess();
		return conec;
	}

}