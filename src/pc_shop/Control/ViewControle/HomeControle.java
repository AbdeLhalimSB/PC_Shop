
package pc_shop.Control.ViewControle;

import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author sahba
 */
public class HomeControle {
    
    @FXML
    Button btn_prod;
    @FXML
    Button btn_exit;
    
    
    public void openEmployee(Event e) throws IOException{
        Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();                  
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/pc_shop/View/AEmployee.fxml"));       
            Scene scene = new Scene(root);       
            stage.setScene(scene);
            stage.show();
    }
//    public void openServices(Event e) throws IOException{
//        Node node = (Node) e.getSource();
//            Stage stage = (Stage) node.getScene().getWindow();                  
//            stage.close();
//            Parent root = FXMLLoader.load(getClass().getResource("/pc_shop/View/Services.fxml"));       
//            Scene scene = new Scene(root);       
//            stage.setScene(scene);
//            stage.show();
//    }
    public void openStatistice(Event e) throws IOException{
        Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();                  
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/pc_shop/View/Statistics.fxml"));       
            Scene scene = new Scene(root);       
            stage.setScene(scene);
            stage.show();
    }
//    public void openEmployee(Event e) throws IOException{
//        Node node = (Node) e.getSource();
//            Stage stage = (Stage) node.getScene().getWindow();                  
//            stage.close();
//            Parent root = FXMLLoader.load(getClass().getResource("/pc_shop/View/Employee.fxml"));       
//            Scene scene = new Scene(root);       
//            stage.setScene(scene);
//            stage.show();
//    }
//    
//    public void support(Event e){
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Support !!");
//        alert.setHeaderText("Contact us ");
//        alert.setContentText("Email : sahbani.2001@gmail.com\nPhone : +212628447107");
//        alert.showAndWait();
//        
//    }
    
    public void Close(Event e) {
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
    }
    
}
