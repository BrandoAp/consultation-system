
import java.sql.Connection;
import connection.connection;

public class Main {
    public static void main(String[] args) {

        Connection conexion = connection.getConnection();

        if(conexion != null){
            System.out.println("Operations can be performed on the database.");
            try {
                conexion.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}