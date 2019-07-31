import com.sun.org.glassfish.gmbal.Description;

import java.sql.*;

public class JdbcText {

    private  static String url="jdbc:mysql://localhost:3306/practice";
    private  static String user="root";
    private static  String  password="root";
    private  static ResultSet rs=null;



  //预编译对象执行SQL操作
//    PreparedStatement与Statement的区别就是其可以利用？进行参数站位，后边进行参数的赋值，
//    PreparedStatement可以进行SQL缓存，Statement不可以，效率要比Statement快
//    Statement存在SQL注入的风险。PreparedStatement可以有效防止用户的注入
//预编译的select delet 类似的操作就不在写了
    public void test4(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url,user,password);

            String sql="INSERT INTO student(id,NAME) VALUES (?,?)";
               PreparedStatement stat=conn.prepareStatement(sql);
               stat.setString(1,"4");
               stat.setString(2,"蔡徐坤");
               stat.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
