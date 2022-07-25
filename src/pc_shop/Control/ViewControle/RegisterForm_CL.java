
package pc_shop.Control.ViewControle;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pc_shop.Control.ConnectionDB;
import pc_shop.Model.Admin;

/**
 *
 * @author sahba
 */
public class RegisterForm_CL {
    
    @FXML
    Label goto_log;
    @FXML
    Button Singup;
    @FXML
    TextField Email_tx,Password_tx,Name_tx;
    @FXML
    RadioButton Admin,Employee;
    
    Statement state ;
    
    public void Goto_Log(Event e) throws IOException{
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();                  
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/pc_shop/View/Login.fxml"));       
        Scene scene = new Scene(root);       
        stage.setScene(scene);
        stage.show();
    }
    public void register(Event e) throws SQLException{
        Admin ad =new Admin();
        ad.setEmail(Email_tx.getText());
        ad.setName(Name_tx.getText());
        ad.setPassword(Password_tx.getText());
        if(Employee.isCache()){
            ad.setPermission("Employee");
        }
        else{
            ad.setPermission("Admin");
        }
        state = ConnectionDB.openConnection().createStatement();
        state.executeUpdate("insert into users (`Email`,`Password`,`Name`,`perms`) values ('"+ad.getEmail()+"', '"+ad.getPassword()+"', '"+ad.getName()+"', '"+ad.getPermission()+"') ");
        ConnectionDB.closeConnection();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Register");
        alert.setHeaderText("Register status ");
        alert.setContentText("Register Done !!");
        alert.showAndWait();
    }
}
