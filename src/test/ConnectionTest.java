package test;

import java.sql.*;
import java.util.Properties;

public class ConnectionTest {
  public static void main(String[] args) {
        Connection conn = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Properties props = new Properties();
            props.put("user", "root");
            props.put("password", "root");
            props.put("characterEncoding", "UTF-8");
            String url = "jdbc:mysql://localhost/architecture";

            conn = DriverManager.getConnection(url, props);
            System.out.println("연결 성공");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from products");

            while (rs.next()) {
              int ID = rs.getInt(1);
              String name = rs.getString(2);
              String type = rs.getString(3);
              System.out.println(ID + ", " + name + ", " + type);
            }
        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
    }
}
