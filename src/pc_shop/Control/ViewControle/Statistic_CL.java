
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pc_shop.Control.ConnectionDB;

/**
 *
 * @author sahba
 */
public class Statistic_CL {
    @FXML
    PieChart chart;
    @FXML
    Button btn_back;
    @FXML
    Label totalsells,pcs,cpus,gpus,totalst,pcst,cpust,gpust;
    @FXML
    public void initialize() throws SQLException {
        getalldata();
        getallstocks();
        setchart();
    }
    
    Statement state ;
    
    public void setchart(){

        chart.setTitle("Sales Analytics"); //Setting the title of the Pie chart
        chart.setClockwise(true); //setting the direction to arrange the data 
        chart.setStartAngle(180);
        chart.getData().add(new PieChart.Data("PC",Integer.parseInt(pcs.getText())));
        chart.getData().add(new PieChart.Data("CPU", Integer.parseInt(cpus.getText())));
        chart.getData().add(new PieChart.Data("GPU", Integer.parseInt(gpus.getText())));    
    }
    
    public void Back(Event e){
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
    
    public void getalldata() throws SQLException{
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT * FROM statistic");
        if(result.next()){
            totalsells.setText(result.getString(1));
            pcs.setText(result.getString(2));
            cpus.setText(result.getString(3));
            gpus.setText(result.getString(4));
        }
        ConnectionDB.closeConnection();
    }
    
    public void getallstocks() throws SQLException{
        state = ConnectionDB.openConnection().createStatement();
        ResultSet result =state.executeQuery("SELECT SUM(Quantity) FROM `products`;");
        result.beforeFirst();
        result.next();
        totalst.setText(result.getString(1));
        result =state.executeQuery("SELECT SUM(Quantity) FROM `products`  where Type= 'PC' ;");
        result.beforeFirst();
        result.next();
        pcst.setText(result.getString(1));
        result =state.executeQuery("SELECT SUM(Quantity) FROM `products`  where Type= 'CPU' ;");
        result.beforeFirst();
        result.next();
        cpust.setText(result.getString(1));
        result =state.executeQuery("SELECT SUM(Quantity) FROM `products`  where Type= 'GPU' ;");
        result.beforeFirst();
        result.next();
        gpust.setText(result.getString(1));
        ConnectionDB.closeConnection();
    }
    
    public void Reset(Event e) throws SQLException{
        state = ConnectionDB.openConnection().createStatement();
        state.executeUpdate("UPDATE `statistic` SET `totalsells`='0',`pcs`='0',`cpus`='0',`gpus`='0',`totalstock`='0',`pcst`='0',`cpust`='0',`gpust`='0' ");
        ConnectionDB.closeConnection();
        getalldata();
    }
    
}
