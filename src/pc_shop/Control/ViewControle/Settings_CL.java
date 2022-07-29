
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pc_shop.Control.ConnectionDB;

/**
 *
 * @author sahba
 */
public class Settings_CL {
    @FXML
    TextField pc,gpu,cpu,ram,md,bt,mn,c,ps,s,cl,od,cables,ac;
    
    @FXML
    public void initialize() throws SQLException {
        getprices();
    }
    
    Statement state;
    
    public void getprices() throws SQLException{
        state = ConnectionDB.openConnection().createStatement();
        ResultSet r =state.executeQuery("SELECT `PC`, `GPU`, `CPU`, `RAM`, `Motherboard`, `Battery`, `Monitor`, `Cas`, `PS`, `Storag`, `Coolings`, `OD`, `Cable`, `Accessories` FROM `settings`");
        if(r.next()){
            //r.next();
            pc.setText(r.getString(1));
            gpu.setText(r.getString(2));
            cpu.setText(r.getString(3));
            ram.setText(r.getString(4));
            md.setText(r.getString(5));
            bt.setText(r.getString(6));
            mn.setText(r.getString(7));
            c.setText(r.getString(8));
            ps.setText(r.getString(9));
            s.setText(r.getString(10));
            cl.setText(r.getString(11));
            od.setText(r.getString(12));
            cables.setText(r.getString(13));
            ac.setText(r.getString(14));
            ConnectionDB.closeConnection();
            }
    }
    
    public void save(Event e) throws SQLException{
        state = ConnectionDB.openConnection().createStatement();
        state.executeUpdate("Delete FROM `settings` WHERE id = 1");
        state.executeUpdate("insert into settings values( '"+pc.getText()+"', '"+gpu.getText()+"','"+cpu.getText()+"','"+ram.getText()+"','"+md.getText()+"','"+bt.getText()+"','"+mn.getText()+"','"+c.getText()+"','"+ps.getText()+"','"+s.getText()+"','"+cl.getText()+"','"+od.getText()+"','"+cables.getText()+"','"+ac.getText()+"' ,1); ");
        ConnectionDB.closeConnection();
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
