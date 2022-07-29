
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pc_shop.Control.ConnectionDB;
import pc_shop.Model.ModelProduct;

/**
 *
 * @author sahba
 */
public class Bills_CL {
    @FXML
    Label error,TOTAL,n,q,pr,d,tt,date;
    @FXML
    TextArea w_tx;
    @FXML
    TextField empn_tx,cl_name;
    @FXML
    DatePicker datep;
    @FXML
    Pane print;
    @FXML
    ComboBox ids;
    
    @FXML
    public void initialize() throws SQLException {
        getBillbyid();
    }
    
    Statement state ;
    
    public void getBill(Event e) throws SQLException{
        clear();
        error.setText("");
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT  `date`, `Name`, `Quantity`, `Price`, `Description`, `Total`, `Warranty`, `Employee`, `client` FROM `bills` WHERE date='"+datep.getValue().toString()+"'");
        if(result.next()){
            date.setText(result.getString(1));
            n.setText(result.getString(2));
            q.setText(result.getString(3));
            pr.setText(result.getString(4));
            d.setText(result.getString(5));
            tt.setText(result.getString(6));
            w_tx.setText(result.getString(7));
            empn_tx.setText(result.getString(8));
            cl_name.setText(result.getString(9));
        }
        else{
            error.setText("Non Bill on this date");
        }
        ConnectionDB.closeConnection();
    }
    public void Print(Event e) throws SQLException{
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null) {
          PageLayout pageLayout = printerJob.getPrinter().createPageLayout(Paper.A4, PageOrientation.PORTRAIT, 0, 0, 0, 0);

          boolean success = printerJob.printPage(pageLayout, print);
          if (success) {
            printerJob.endJob();
          }
        }
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
        date.setText("");
        n.setText("");
        q.setText("");
        pr.setText("");
        d.setText("");
        tt.setText("");
        w_tx.setText("");
        empn_tx.setText("");
        cl_name.setText("");
    }
    
    public void getBillbyid() throws SQLException{
        ObservableList<String> l =FXCollections.observableArrayList();
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT id FROM `bills`");
        while(result.next()){
            l.add(result.getString(1));
        }
        ConnectionDB.closeConnection();
        ids.setItems(l);
        ids.getSelectionModel().select("Choose...");
    }
    
    public void getoneBillwithid(Event e) throws SQLException{
        clear();
        error.setText("");
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT  `date`, `Name`, `Quantity`, `Price`, `Description`, `Total`, `Warranty`, `Employee`, `client` FROM `bills` WHERE id='"+ids.getValue().toString()+"'");
        if(result.next()){
            date.setText(result.getString(1));
            n.setText(result.getString(2));
            q.setText(result.getString(3));
            pr.setText(result.getString(4));
            d.setText(result.getString(5));
            tt.setText(result.getString(6));
            w_tx.setText(result.getString(7));
            empn_tx.setText(result.getString(8));
            cl_name.setText(result.getString(9));
        }
        else{
            error.setText("Non Bill on this date");
        }
        ConnectionDB.closeConnection();
    }
    
}
