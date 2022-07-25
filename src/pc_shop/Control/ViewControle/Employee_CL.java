
package pc_shop.Control.ViewControle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pc_shop.Control.ConnectionDB;
import pc_shop.Model.Admin;

/**
 *
 * @author sahba
 */
public class Employee_CL {
    @FXML
    Button btn_add,btnBack,btn_search;
    @FXML
    TextField Name_tx,Email_tx,Pass_tx,search_tx;
    @FXML
    TableView product_view;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn email;
    @FXML
    private TableColumn pass;
    @FXML
    private TableColumn perm;
    @FXML
    ComboBox permss;
    
    int ID;
    
    @FXML
    public void initialize() throws SQLException {
        permss.getItems().removeAll(permss.getItems());
        permss.getItems().addAll("Employee","Admin");
        permss.getSelectionModel().select("Employee");
        DisplayEmployees();
    }
    
    
    public void DisplayEmployees() throws SQLException{
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        pass.setCellValueFactory(new PropertyValueFactory<>("Password"));
        perm.setCellValueFactory(new PropertyValueFactory<>("Permission"));
        product_view.setItems(getAllProduct());
    }
        
    
    Statement state ;
    
    public void add(Event e) throws SQLException{
        Admin employee = new Admin();
        employee.setName(Name_tx.getText());
        employee.setEmail(Email_tx.getText());
        employee.setPassword(Pass_tx.getText());
        insert(employee);
        clear();
        DisplayEmployees();
    }
    public void del(Event e){
        delete(ID);
        clear();
    }
    public void edit(Event e){
        Admin ad = new Admin();
        ad.setName(Name_tx.getText());
        ad.setEmail(Email_tx.getText());
        ad.setPassword(Pass_tx.getText());
        update(ad);
        clear();
    }
    
    public void clickTable(Event e)
    {
       Admin ad =  (Admin) product_view.getSelectionModel().getSelectedItem();
       Name_tx.setText(ad.getName()+"");
       Email_tx.setText(ad.getEmail()+"");
       Pass_tx.setText(ad.getPassword()+"");
       permss.setValue(ad.getPermission()+"");
       ID=ad.getId();
    }
    
    public void insert(Admin ad)
    {
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("INSERT INTO `users` ( `Name`, `Email`, `Password`,`perms`) VALUES ('"+ad.getName()+"','"+ad.getEmail()+"', '"+ad.getPassword()+"'), '"+ad.getPermission()+"');");
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(Employee_CL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id)
    {
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("Delete FROM `users` WHERE id = " + id);
            ConnectionDB.closeConnection();
            DisplayEmployees();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Admin ad)
    {
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("UPDATE users set  `Name` = '"+ad.getName()+"', `Email` = '"+ad.getEmail()+"', `Password` = '" + ad.getPassword()+"', `perms`='"+ad.getPermission()+"' WHERE id = "+ID );
            ConnectionDB.closeConnection();
            DisplayEmployees();
        } catch (SQLException ex) {
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
            ConnectionDB.closeConnection();
        }
    }
    
    public ObservableList<Admin> getAllProduct() throws SQLException{
        ObservableList Admin=FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT * FROM users");
        while(result.next()){
            Admin obj = new Admin();
            obj.setId(result.getInt(1));
            obj.setName(result.getString(2));
            obj.setEmail(result.getString(3));
            obj.setPassword(result.getString(4));
            obj.setPermission(result.getString(5));
            Admin.add(obj);
        }
        ConnectionDB.closeConnection();
        return Admin;
    }
    
    public ObservableList<Admin> getSearchProduct(String name) throws SQLException{
        ObservableList Admin=FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT * FROM users where Name Like '%"+name+"%' ");
        while(result.next()){
            Admin obj = new Admin();
            obj.setId(result.getInt(1));
            obj.setName(result.getString(2));
            obj.setEmail(result.getString(3));
            obj.setPassword(result.getString(4));
            obj.setPermission(result.getString(5));
            Admin.add(obj);
        }
        ConnectionDB.closeConnection();
        return Admin;
    }
    
    public void Search(Event e) throws SQLException{
        product_view.setItems(getSearchProduct(search_tx.getText()));
        System.out.println(search_tx.getText());
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
     

    
    public void clear(){
        Name_tx.setText("");
        Email_tx.setText("");
        Pass_tx.setText("");
    }
    
    public void pro(Event e) throws SQLException{
        DisplayEmployees();
        clear();
    }
}
