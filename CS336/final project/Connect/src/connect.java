import java.sql.*;

/**
 * Created by abhi on 11/25/16.
 */
public class connect {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://collegecost.cuuw09qotyfh.us-east-2.rds.amazonaws.com:3306/my_project1";
    static final String USER = "sql336";
    static final String PASS = "collegecost";

    public static void main(String[] args){
        Connection conn =  null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql;
            sql = "SELECT College, TUITIONFEE_IN, State FROM my_project1.all";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                System.out.print("\"");
                System.out.print(rs.getString("College"));
                System.out.print("\",");
                System.out.println();
            }




            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
