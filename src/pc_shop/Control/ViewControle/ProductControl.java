
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
import pc_shop.Model.ModelProduct;

/**
 *
 * @author sahba
 */
public class ProductControl{
    
    
    @FXML
    Button btn_add,btnBack,btn_search;
    @FXML
    TextField Name_tx,Qua_tx,Price_tx,Des_tx,search_tx;
    @FXML
    ComboBox slm;
     @FXML
    private TableView product_view;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn quantity;
    @FXML
    private TableColumn price;
    @FXML
    private TableColumn type;
    @FXML
    private TableColumn description;
    
    int ID;
    
    @FXML
    public void initialize() throws SQLException {
        slm.getItems().removeAll(slm.getItems());
        slm.getItems().addAll("PC", "GPU", "CPU","RAM","Motherboard","Battery","Monitor","Case","Power Supplie","Storage","Coolings","Optical Drives","Cable","Accessories");
        slm.getSelectionModel().select("PC");
        DisplayProducts();
    }
    
        
    
    public void DisplayProducts() throws SQLException{
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        //show data
        product_view.setItems(getAllProduct());
    }
        
    
    Statement state ;
    
    public void add() throws SQLException{
        ModelProduct product = new ModelProduct();
        product.setName(Name_tx.getText());
        product.setQuantity(Integer.parseInt(Qua_tx.getText()));
        product.setPrice(Double.parseDouble(Price_tx.getText()));
        product.setDescription(Des_tx.getText());
        product.setType(slm.getValue().toString());
        insert(product);
        clear();
        DisplayProducts();
    }
    public void del(Event e){
        delete(ID);
        clear();
    }
    public void edit(Event e){
        ModelProduct product = new ModelProduct();
        product.setName(Name_tx.getText());
        product.setQuantity(Integer.parseInt(Qua_tx.getText()));
        product.setPrice(Double.parseDouble(Price_tx.getText()));
        product.setDescription(Des_tx.getText());
        product.setType(slm.getValue().toString());
        update(product);
        clear();
    }
    
    public void clickTable(Event e)
    {
       ModelProduct product =  (ModelProduct) product_view.getSelectionModel().getSelectedItem();
       Name_tx.setText(product.getName());
       Qua_tx.setText(product.getQuantity()+"");
       Price_tx.setText(product.getPrice()+"");
       Des_tx.setText(product.getDescription()+"");
       ID=product.getId();
       slm.setValue(product.getType());
    }
    
    public void insert(ModelProduct  product)
    {
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("INSERT INTO `products` ( `Name`, `Quantity`, `Price`, `Description`, `Type`) VALUES ('"+product.getName()+"','"+product.getQuantity()+"', '"+product.getPrice()+"' , '"+product.getDescription()+"' , '"+product.getType()+"') ");
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id)
    {
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("Delete FROM `Products` WHERE id = " + id);
            ConnectionDB.closeConnection();
            DisplayProducts();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(ModelProduct product)
    {
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("UPDATE products set  `Name` = "+"'"+product.getName()+"'"+", `Quantity` = "+product.getQuantity()+", `Price` = " + product.getPrice() +", `Type` = "+"'"+product.getType()+"',"+" `Description` = '"+product.getDescription()+"' WHERE id = "+ID );
            ConnectionDB.closeConnection();
            DisplayProducts();
        } catch (SQLException ex) {
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
            ConnectionDB.closeConnection();
        }
    }
    
    public ObservableList<ModelProduct> getAllProduct() throws SQLException{
        ObservableList product=FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT * FROM products");
        while(result.next()){
            ModelProduct obj = new ModelProduct();
            obj.setId(result.getInt(1));
            obj.setName(result.getString(2));
            obj.setQuantity(result.getInt(3));
            obj.setPrice(result.getDouble(4));
            obj.setDescription(result.getString(5));
            obj.setType(result.getString(6));
            product.add(obj);
        }
        ConnectionDB.closeConnection();
        return product;
    }
    
    public ObservableList<ModelProduct> getSearchProduct(String name) throws SQLException{
        ObservableList product=FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT * FROM products where Name Like '%"+name+"%' ");
        while(result.next()){
            ModelProduct obj = new ModelProduct();
            obj.setId(result.getInt(1));
            obj.setName(result.getString(2));
            obj.setQuantity(result.getInt(3));
            obj.setPrice(result.getDouble(4));
            obj.setDescription(result.getString(5));
            obj.setType(result.getString(6));
            product.add(obj);
        }
        ConnectionDB.closeConnection();
        return product;
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

              Parent root = FXMLLoader.load(getClass().getResource("/pc_shop/View/Home.fxml"));       
              Scene scene = new Scene(root);       
              stage.setScene(scene);
              stage.show();

       } catch (Exception ex) {
           System.out.println("y"+ex.getMessage());
       }
    }
     
    

    
    public void clear(){
        Name_tx.setText("");
        Qua_tx.setText("");
        Price_tx.setText("");
        Des_tx.setText("");
        Des_tx.setText("");
    }
    
    public void pro(Event e) throws SQLException{
        DisplayProducts();
        clear();
    }
    
}
