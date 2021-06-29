
package uttec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {
    
        private String basededatos;
    private String usuario;
    private String password;
    private String url;

    Connection conecta = null;
    Statement stm = null;
    PreparedStatement pstm = null;

    public Conexion() {
        basededatos = "sistema";
        usuario = "root";
        password = null;
        url = "jdbc:mysql://localhost/" + basededatos;
    }
    public Connection conecta(){
         try {
            conecta = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            conecta = DriverManager.getConnection(url, usuario, password);

            JOptionPane.showMessageDialog(null, "CONEXION CON EXITO", "RESULTADO", JOptionPane.INFORMATION_MESSAGE);
            return conecta;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "NO SE PUEDE ENCONTAR LA BASE DE DATOS",
                    "CONEXION SIN EXITO", JOptionPane.ERROR_MESSAGE);

        } catch (InstantiationException ex) {

            JOptionPane.showMessageDialog(null,
                    "no se encontro la ruta del Driver",
                    "error del Driver", JOptionPane.ERROR_MESSAGE);

        } catch (IllegalAccessException ex) {

            JOptionPane.showMessageDialog(null,
                    "el nombre del usuario o contraseña"
                    + "son incorrectas",
                    "error de alidacion", JOptionPane.ERROR_MESSAGE);

        } catch (ClassNotFoundException ex) {

            JOptionPane.showMessageDialog(null,
                    "no se encuentra la clase" + ex,
                    "error de clase", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;

    }
 
    public void consultaAdmi() throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT * FROM Administrador";
        pstm = conecta.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {
              System.out.println(rs.getInt("id_adm"));
            System.out.println(rs.getString("nombre_adm"));
             System.out.println(rs.getString("ap_pat"));
             System.out.println(rs.getString("ap_mat"));
             System.out.println(rs.getString("usuario"));
             System.out.println(rs.getString("password"));
             System.out.println(rs.getString("tipo_user"));
        }//while
    }//consultajuego

    public ResultSet consultaAdmi(String user,String pass) throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT * FROM Administrador WHERE usuario='" + user+"' && password='"+pass+"'";
        pstm = conecta.prepareStatement(sql);
        rs = pstm.executeQuery();
        return rs;

    }
    
       public void insertaAcademia(String noms) throws SQLException {
        String sql = "INSERT INTO academia" + "(nombre)"
                + "VALUES ('" + noms + "')";
        pstm = conecta.prepareStatement(sql);
        pstm.execute();
    }
       
//////////////////////////////////////////////////////////////////////////////////////////////////////
  public int consultarAcademia(String x) throws SQLException{
      ResultSet rs = null;
       String sql="SELECT id_academia from academia where nombre = ?";
         pstm = conecta.prepareStatement(sql);
         pstm.setString(1, x);
        rs = pstm.executeQuery();
        int id = 0;
        while(rs.next()){
            id = rs.getInt("id_academia");
        }
     return id;    
  }
 
       public void insertarMater(String nomm,int x) throws SQLException {
         String sql="INSERT INTO materia(nombre, id_academia) VALUES (?, ?)";
           pstm=conecta.prepareStatement(sql);
           pstm.setString(1, nomm);
           pstm.setInt(2, x);
           pstm.execute();
           
       }
////////////////////////////////////////////////////////////////////////////////////////////////////////////    
   
   public ResultSet ConsultarMater(int x)throws SQLException {
           ResultSet rs = null;
       String sql="SELECT nombre from materia where id_academia = ?";
         pstm = conecta.prepareStatement(sql);
          pstm.setInt(1, x);
        rs = pstm.executeQuery();
     return rs;   
       
   }
   
   //////////////////////////////////////////////////////////////////////////////////////////////////////
  public int consultarMateria(String x) throws SQLException{
      ResultSet rs = null;
       String sql="SELECT id_materia from materia where nombre =?";
         pstm = conecta.prepareStatement(sql);
         pstm.setString(1, x);
        rs = pstm.executeQuery();
        int id = 0;
        while(rs.next()){
            id = rs.getInt("id_materia");
        }
     return id;    
  }
 public int consultarProfesor(String y) throws SQLException{
      ResultSet rs = null;
       String sql="SELECT id_profesor from profesor where  id_profesor=?";
         pstm = conecta.prepareStatement(sql);
         pstm.setString(1, y);
        rs = pstm.executeQuery();
        int id = 0;
        while(rs.next()){
            id = rs.getInt("id_profesor");
        }
     return id;    
  }
       public void insertarRelacion(int y,int x) throws SQLException {
         String sql="INSERT INTO imparte(id_profesor,id_materia) VALUES (?, ?)";
           pstm=conecta.prepareStatement(sql);
           pstm.setInt(1, y);
           pstm.setInt(2, x);
           pstm.execute();
           
       }
////////////////////////////////////////////////////////////////////////////////////////////////////////////    
   
   
   
           public ResultSet consultaAcademia() throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT nombre FROM academia";
        pstm = conecta.prepareStatement(sql);
        rs = pstm.executeQuery();
        return rs;
    }
           

       public ResultSet consultaProfesor() throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT concat( id_profesor,'-', nombre, ' ',ap_pat,' ' , ap_mat)as nombre_completo FROM profesor";
        pstm = conecta.prepareStatement(sql);
        rs = pstm.executeQuery();
        return rs;
    }
   
       public void guaRelacion(String nomm,String nomac,String nompro) throws SQLException {
         String sql="INSERT INTO relacion"+"(materia,academia,profesor)"
                   +"VALUES ('"+nomm+"','"+nomac+"','"+nompro+"')";
           pstm=conecta.prepareStatement(sql);
           pstm.execute();
       }

    public void conPro()throws SQLException{
        ResultSet rs=null;
        String sql="SELECT * FROM profesor";
          pstm = conecta.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {
              System.out.println(rs.getInt("id_profesor"));
            System.out.println(rs.getString("nombre"));
             System.out.println(rs.getString("ap_pat"));
             System.out.println(rs.getString("ap_mat"));
             System.out.println(rs.getString("usuario"));
             System.out.println(rs.getString("contrasena"));
             System.out.println(rs.getString("tipo"));
        }//whil  
    }
        public ResultSet conPro(String user,String pass) throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT * FROM profesor WHERE usuarario='" + user+"' && contrasena='"+pass+"'";
        pstm = conecta.prepareStatement(sql);
        rs = pstm.executeQuery();
        return rs;
    } 
        
  public void pregunta(String pregunta,int Mater)throws SQLException {
            String sql="INSERT INTO pregunta(pregunta,id_materia) VALUES (?, ?)";
           pstm=conecta.prepareStatement(sql);
           pstm.setString(1, pregunta);
           pstm.setInt(2, Mater);
           pstm.execute();
  }
  //////////////////////////////////////////////////
    public int consultarPregunta(String x) throws SQLException{
      ResultSet rs = null;
       String sql="SELECT id_pregunta from pregunta where pregunta = ?";
         pstm = conecta.prepareStatement(sql);
         pstm.setString(1, x);
        rs = pstm.executeQuery();
        int id = 0;
        while(rs.next()){
            id = rs.getInt("id_pregunta");
        }
     return id;    
  }
   public void respuestaInse(String respuesta,String tipo,int idPre)throws SQLException {
            String sql="INSERT INTO respuesta(tipo,descripcion,id_pregunta) VALUES (?,?,?)";
           pstm=conecta.prepareStatement(sql);
           pstm.setString(1, tipo);
           pstm.setString(2, respuesta);
           pstm.setInt(3, idPre);
           pstm.execute();
  }
   ////////////////tabla////////////////// ------<<<<<<<<<<<
    public ResultSet consultaPre()throws SQLException{
         ResultSet rs = null;
        String sql = "SELECT id_pregunta,pregunta FROM pregunta";
        pstm = conecta.prepareStatement(sql);
        rs = pstm.executeQuery();
        return rs;

    }
       public ResultSet consultare()throws SQLException{
       ResultSet rs = null;
       String sql="SELECT descripcion from respuesta";
         pstm = conecta.prepareStatement(sql);
        pstm = conecta.prepareStatement(sql);
        rs = pstm.executeQuery();
     return rs;    
    }
       /////////////////////////////////////////////////////
     public int IdProfesor(String y) throws SQLException{
      ResultSet rs = null;
       String sql="SELECT id_profesor from profesor where  usuarario=?";
         pstm = conecta.prepareStatement(sql);
         pstm.setString(1, y);
        rs = pstm.executeQuery();
        int id = 0;
        while(rs.next()){
            id = rs.getInt("id_profesor");
        }
     return id;      
     }
         public ResultSet enviaMater(int idm)throws SQLException{
           ResultSet rs=null;
           String sql="select nombre from materia inner join imparte on\n" +
"materia.id_materia=imparte.id_materia where id_profesor=?";
           pstm=conecta.prepareStatement(sql);
            pstm.setInt(1, idm);
           rs=pstm.executeQuery();

     return rs;  
       }
     ///////////////////////////////////////////////////////
 public void creIns(int id_mat,int id_pro,int nup,String tie,int cod)throws SQLException {
String sql="INSERT INTO examen(id_materia,id_profesor,num_preguntas,tiempo,codigo) VALUES (?,?,?,?,?)";
pstm=conecta.prepareStatement(sql);
pstm.setInt(1, id_mat);
pstm.setInt(2, id_pro);
pstm.setInt(3, nup);
pstm.setString(4, tie);
pstm.setInt(5, cod);
pstm.execute();
 }  
public ResultSet tablPregu(int idma)throws SQLException {
    ResultSet rs = null;
            String sql="SELECT pregunta from pregunta where id_materia='"+idma+"'";
         pstm = conecta.prepareStatement(sql);
       pstm.setInt(1, idma);
        rs = pstm.executeQuery();
        return rs; 
} 
}