
package pc_shop.Control.ViewControle;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pc_shop.Control.ConnectionDB;
import pc_shop.Control.LoginControl;
import pc_shop.Model.Admin;

/**
 *
 * @author sahba
 */
public class LoginForm_CL {
    @FXML
    TextField email_tx;
    @FXML
    PasswordField pass_tx;
    @FXML
    Button btnlogin;
    @FXML
    Label lbmsg,goto_reg;
    
    Admin ad =new Admin();
    LoginControl lg = new LoginControl();
    Statement st;
    
    public void isSign(Event e) throws SQLException, IOException{
        ad.setEmail(email_tx.getText());
        ad.setPassword(pass_tx.getText());
        if(lg.checker(ad)){
            st=ConnectionDB.openConnection().createStatement();
            ResultSet res = st.executeQuery("SELECT perms FROM `users` where Email='"+ad.getEmail()+"' ");
            res.beforeFirst();
            res.next();
            String val = res.getString(1);
            ConnectionDB.closeConnection();
            if(val.contains("Employee")){
                Node node = (Node) e.getSource();
                Stage stage = (Stage) node.getScene().getWindow();                  
                stage.close();
                Parent root = FXMLLoader.load(getClass().getResource("/pc_shop/View/Home.fxml"));       
                Scene scene = new Scene(root);       
                stage.setScene(scene);
                stage.show();
            }
            else{
                Node node = (Node) e.getSource();
                Stage stage = (Stage) node.getScene().getWindow();                  
                stage.close();
                Parent root = FXMLLoader.load(getClass().getResource("/pc_shop/View/AHome.fxml"));       
                Scene scene = new Scene(root);       
                stage.setScene(scene);
                stage.show();
            }
        }
        else{
            lbmsg.setText("Email or Password is wrong !!");
        }
    }
    
    public void Goto_Reg(Event e) throws IOException{
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();                  
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/pc_shop/View/Register.fxml"));       
        Scene scene = new Scene(root);       
        stage.setScene(scene);
        stage.show();
    }
        
}
