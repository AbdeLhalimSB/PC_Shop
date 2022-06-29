
package pc_shop.Control.ViewControle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pc_shop.Control.ConnectionDB;
import pc_shop.Model.ModelProduct;

/**
 *
 * @author sahba
 */
public class Services_CL {
    
    @FXML
    Button btn_add,btnBack,btn_search,refresh,sell;
    @FXML
    TextField Name_tx,Qua_tx,Price_tx,Des_tx,search_tx,empn_tx;
    @FXML
    ComboBox slm;
    @FXML
    Label error,TOTAL,n,q,pr,d,tt;
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
    @FXML
    private Pane print;
    @FXML
    TextArea w_tx;
    
    int ID;
    double total;
    
    ProductControl pc = new ProductControl();
    
    @FXML
    public void initialize() throws SQLException {
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
    
    
    public void clickTable(Event e)
    {
       ModelProduct product =  (ModelProduct) product_view.getSelectionModel().getSelectedItem();
       Name_tx.setText(product.getName());
       Qua_tx.setText(product.getQuantity()+"");
       Price_tx.setText(product.getPrice()+"");
       Des_tx.setText(product.getDescription()+"");
       ID=product.getId();
       //slm.setValue(product.getType());
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
     
     String  TypeProduct[]={"PC","GPU","CPU"};;
    ObservableList<String> olType =FXCollections.observableArrayList(TypeProduct);

    
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
    
    public void Sell(Event e) throws SQLException{
        ModelProduct product =  (ModelProduct) product_view.getSelectionModel().getSelectedItem();
        if(Integer.parseInt(Qua_tx.getText())==0){
            error.setText("Cannot Sell 0 Quantity");
        }
        if(product.getQuantity()>0){
            int nq = product.getQuantity()-Integer.parseInt(Qua_tx.getText());
            state.executeUpdate("UPDATE products set  `Quantity` = "+nq+" WHERE id = "+ID);
        }
        else{
            error.setText("Stock is out");
        }
        //total=0;
        total+=product.getPrice()*Double.parseDouble(Qua_tx.getText());
        TOTAL.setText(Double.toString(total));
        printerinfos(product);
        pro(e);
    }
    
    public void Print(Event e){
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null) {
          PageLayout pageLayout = printerJob.getPrinter().createPageLayout(Paper.A4, PageOrientation.PORTRAIT, 0, 0, 0, 0);

          boolean success = printerJob.printPage(pageLayout, print);
          if (success) {
            printerJob.endJob();
          }
        }
    }
    
    public void printerinfos(ModelProduct p){
        n.setText(p.getName());
        pr.setText(Double.toString(p.getPrice()));
        q.setText(Qua_tx.getText());
        d.setText(p.getDescription());
        tt.setText(Double.toString(total));
    }
    
}
