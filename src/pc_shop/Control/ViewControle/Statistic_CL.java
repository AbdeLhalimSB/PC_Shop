
package pc_shop.Control.ViewControle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
    AnchorPane print;
    @FXML
    Label totalsells,pcs,cpus,gpus,totalst,pcst,cpust,gpust,rams,mds,bs,ms,cs,pss,ss,pcos,ods,cables,acs,ram,md,b,m,c,psss,s,pco,od,cable,ac;
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
        chart.getData().add(new PieChart.Data("RAM", Integer.parseInt(ram.getText())));   
        chart.getData().add(new PieChart.Data("Motherboard", Integer.parseInt(md.getText()))); 
        chart.getData().add(new PieChart.Data("Battery", Integer.parseInt(b.getText())));  
        chart.getData().add(new PieChart.Data("Monitor", Integer.parseInt(m.getText())));  
        chart.getData().add(new PieChart.Data("Case", Integer.parseInt(c.getText())));  
        chart.getData().add(new PieChart.Data("Power Supplie", Integer.parseInt(psss.getText())));  
        chart.getData().add(new PieChart.Data("Storage", Integer.parseInt(s.getText())));  
        chart.getData().add(new PieChart.Data("PC Cooling", Integer.parseInt(pco.getText())));  
        chart.getData().add(new PieChart.Data("Optical Drives", Integer.parseInt(od.getText())));  
        chart.getData().add(new PieChart.Data("Cable", Integer.parseInt(cable.getText())));  
        chart.getData().add(new PieChart.Data("Accessories", Integer.parseInt(ac.getText())));  
        
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
            ram.setText(result.getString(5));
            md.setText(result.getString(6));
            b.setText(result.getString(7));
            m.setText(result.getString(8));
            c.setText(result.getString(9));
            psss.setText(result.getString(10));
            s.setText(result.getString(11));
            pco.setText(result.getString(12));
            od.setText(result.getString(13));
            cable.setText(result.getString(14));
            ac.setText(result.getString(15));
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
        result =state.executeQuery("SELECT SUM(Quantity) FROM `products`  where Type= 'RAM' ;");
        result.beforeFirst();
        result.next();
        rams.setText(result.getString(1));
        result =state.executeQuery("SELECT SUM(Quantity) FROM `products`  where Type= 'Motherboard' ;");
        result.beforeFirst();
        result.next();
        mds.setText(result.getString(1));
        result =state.executeQuery("SELECT SUM(Quantity) FROM `products`  where Type= 'Battery' ;");
        result.beforeFirst();
        result.next();
        bs.setText(result.getString(1));
        result =state.executeQuery("SELECT SUM(Quantity) FROM `products`  where Type= 'Monitor' ;");
        result.beforeFirst();
        result.next();
        ms.setText(result.getString(1));
        result =state.executeQuery("SELECT SUM(Quantity) FROM `products`  where Type= 'Case' ;");
        result.beforeFirst();
        result.next();
        cs.setText(result.getString(1));
        result =state.executeQuery("SELECT SUM(Quantity) FROM `products`  where Type= 'Power Supplie' ;");
        result.beforeFirst();
        result.next();
        pss.setText(result.getString(1));
        result =state.executeQuery("SELECT SUM(Quantity) FROM `products`  where Type= 'Storage' ;");
        result.beforeFirst();
        result.next();
        ss.setText(result.getString(1));
        result =state.executeQuery("SELECT SUM(Quantity) FROM `products`  where Type= 'Battery' ;");
        result.beforeFirst();
        result.next();
        bs.setText(result.getString(1));
        result =state.executeQuery("SELECT SUM(Quantity) FROM `products`  where Type= 'Coolings' ;");
        result.beforeFirst();
        result.next();
        pcos.setText(result.getString(1));
        result =state.executeQuery("SELECT SUM(Quantity) FROM `products`  where Type= 'Optical Drives' ;");
        result.beforeFirst();
        result.next();
        ods.setText(result.getString(1));
        result =state.executeQuery("SELECT SUM(Quantity) FROM `products`  where Type= 'Cable' ;");
        result.beforeFirst();
        result.next();
        cables.setText(result.getString(1));
        result =state.executeQuery("SELECT SUM(Quantity) FROM `products`  where Type= 'Accessories' ;");
        result.beforeFirst();
        result.next();
        acs.setText(result.getString(1));

        ConnectionDB.closeConnection();
    }
    
    public void Reset(Event e) throws SQLException{
        state = ConnectionDB.openConnection().createStatement();
        state.executeUpdate("UPDATE `statistic` SET `totalsells`='0',`pcs`='0',`cpus`='0',`gpus`='0',`totalstock`='0',`pcst`='0',`cpust`='0',`gpust`='0' ");
        ConnectionDB.closeConnection();
        getalldata();
    }
    
    public void Print(Event e){
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null) {
          PageLayout pageLayout = printerJob.getPrinter().createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, 0, 0, 0, 0);

          boolean success = printerJob.printPage(pageLayout, print);
          if (success) {
            printerJob.endJob();
          }
        }
    }
    
}
