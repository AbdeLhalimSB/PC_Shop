
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
    Statement st,st2;
     String type;
    public boolean checker(Admin ad) throws SQLException{
        st=ConnectionDB.openConnection().createStatement();
        ResultSet res = st.executeQuery("select * from users where Email='"+ad.getEmail()+"' and Password='"+ad.getPassword()+"'");
        //ResultSet per = st.executeQuery("select Permission from users where Email='"+ad.getEmail()+"' and Password='"+ad.getPassword()+"'");
//        ad.setPermission("employee");
        if(res.next()){
            return true;
        }
        else{
            return false;
        }
    }
    
    public String getType(){
        
        if(type.equals("admin"))
            return "admin";
        else
            return "employee";
    }
    
//    public boolean per_checker(Admin ad) throws SQLException{
//        st=ConnectionDB.openConnection().createStatement();
//        ResultSet r = st.executeQuery("select Permission from users where Email='"+ad.getEmail()+"'");
//        if(r.toString()=="admin"){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
    
}
