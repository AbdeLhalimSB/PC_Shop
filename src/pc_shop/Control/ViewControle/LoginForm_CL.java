
package pc_shop.Control.ViewControle;

import java.io.IOException;
import java.sql.SQLException;
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
    Label lbmsg;
    
    Admin ad =new Admin();
    LoginControl lg = new LoginControl();
    
    public void isSign(Event e) throws SQLException, IOException{
        ad.setEmail(email_tx.getText());
        ad.setPassword(pass_tx.getText());
        if(lg.checker(ad)){
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();                  
            stage.close();
//          if(rg.getType().equals("admin")){
            Parent root = FXMLLoader.load(getClass().getResource("/pc_shop/View/Home.fxml"));       
            Scene scene = new Scene(root);       
            stage.setScene(scene);
            stage.show();
        }
        else{
            lbmsg.setText("Email or Password is wrong !!");
        }
    }
}