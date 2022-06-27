
package pc_shop.Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sahba
 */
public class ConnectionDB {
    private static Connection con=null;

    private ConnectionDB() {
        
    }
    
    
    public static Connection openConnection() throws SQLException{
        if(con==null){
            con = DriverManager.getConnection("jdbc:mysql://localhost/pcshop","root","");
        }
        return con;
    }
    
    public static void closeConnection(){
        if(con!=null){
            con=null;
        }
    }
}
