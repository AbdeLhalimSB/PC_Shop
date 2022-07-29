
package pc_shop.Control.ViewControle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pc_shop.Control.ConnectionDB;

/**
 *
 * @author sahba
 */
public class Bank_CL {
    @FXML
    Label te,np,ns;
    @FXML
    PieChart chart;
    
    @FXML
    public void initialize() throws SQLException {
        getdata();
        setchart();
    }
    
    Statement state;
    
    
    public void setchart(){
        //chart.setTitle("Moneys Analytics"); //Setting the title of the Pie chart
        chart.setClockwise(true); //setting the direction to arrange the data 
        chart.setStartAngle(180);
        chart.getData().add(new PieChart.Data("Total Earning",Double.parseDouble(te.getText())));
        chart.getData().add(new PieChart.Data("Non Selled", Integer.parseInt(ns.getText())));
        
    }
    
    public void getdata() throws SQLException{
        int price=0,qua=0;
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT SUM(Total) FROM bills");
        if(result.next()){
            te.setText(result.getString(1));
        }
        result =state.executeQuery("SELECT SUM(Price) FROM products");
        if(result.next()){
            price=result.getInt(1);
        }
        result =state.executeQuery("SELECT SUM(Quantity) FROM products");
        if(result.next()){
            qua=result.getInt(1);
        }
        int r = price*qua;
        ns.setText(Integer.toString(r));
        
        
        
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
    
}
