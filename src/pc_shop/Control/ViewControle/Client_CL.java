
package pc_shop.Control.ViewControle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pc_shop.Control.ConnectionDB;

/**
 *
 * @author sahba
 */
public class Client_CL {
    
    @FXML
    private TableView cl;
    @FXML
    private TableColumn Name;
    @FXML 
    Label tc,cs;
    
    Statement state;
    
    @FXML
    public void initialize() throws SQLException {
        DisplayClients();
        addstate();
    }
    
    public void DisplayClients() throws SQLException{
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        cl.setItems(getCls());
    }
    
    public ObservableList<String> getCls() throws SQLException{
        ObservableList cl =FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT client FROM bills");
        while(result.next()){
            cl.add(result.getString(1));
        }
        ConnectionDB.closeConnection();
        return cl;
    }
    
    public void Back(Event e)
    {
     try {
           Node node = (Node) e.getSource();
           Stage stage = (Stage) node.getScene().getWindow();                  
           stage.close();

           Parent root = FXMLLoader.load(getClass().getResource("/pc_shop/View/AHome.fxml"));       
           Scene scene = new Scene(root);       
           stage.setScene(scene);
           stage.show();

    } catch (Exception ex) {
        System.out.println("y"+ex.getMessage());
    }
    }
    
    public void addstate() throws SQLException{
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT client FROM bills WHERE Total = (SELECT MAX(Total) FROM bills);");
        result.next();
        tc.setText(result.getString(1));
        result =state.executeQuery("SELECT COUNT(Total) FROM bills where client='"+tc.getText()+"'");
        result.next();
        cs.setText(result.getString(1));
        ConnectionDB.closeConnection();
    }
    
}
