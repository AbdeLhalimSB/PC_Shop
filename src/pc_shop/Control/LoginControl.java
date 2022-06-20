
package pc_shop.Control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pc_shop.Model.Admin;

/**
 *
 * @author sahba
 */
public class LoginControl {
    Statement st;
    public boolean checker(Admin ad) throws SQLException{
        st=ConnectionDB.openConnection().createStatement();
        ResultSet res = st.executeQuery("select * from users where Email='"+ad.getEmail()+"' and Password='"+ad.getPassword()+"'");
        if(res.next()){
            return true;
        }
        else{
            return false;
        }
    }
}
