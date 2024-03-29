import com.sun.org.glassfish.gmbal.Description;

import java.sql.*;

public class JdbcText {

    private  static String url="jdbc:mysql://localhost:3306/practice";
    private  static String user="root";
    private static  String  password="root";
    private  static ResultSet rs=null;

//    CallableStatement对象执行存储过程

    /*DELIMITER $
    CREATE PROCEDURE pro_findById(IN sid INT)
    BEGIN
    SELECT *FROM student WHERE id=sid;
    END $*/
    public void test5(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url,user,password);
            String sql="CALL pro_findById(?)";
            CallableStatement  stat=conn.prepareCall(sql);
            stat.setInt(1,4);
           rs =stat.executeQuery();//注意。在执行存储过程中必须使用CALL pro_findById
   while (rs.next()){

       int id=rs.getInt("id");
       String  name=rs.getString("name");
      int age=rs.getInt("age") ;
      System.out.println(id);
       System.out.println(name);
       System.out.println(age);


   }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    //带有输出参数的存储过程
   /* DELIMITER $
    CREATE PROCEDURE pro_findById2(IN sid INT,OUT sname VARCHAR(20))
    BEGIN
    SELECT NAME INTO sname FROM student WHERE id=sid;

    END $*/
    public void test6(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url,user,password);
            String sql="CALL pro_findById2(?,?)";
            CallableStatement  stat=conn.prepareCall(sql);
            stat.setInt(1,4);
//            注册一个输出参数
//                    参数1：参数的位置
//                    参数2：表示存储过程的out参数的数据库类型


            stat.registerOutParameter(2, Types.VARCHAR);

            stat.executeQuery();

            //结果在输出参数中

          String name=stat.getString(2);//位置与设置输出参数的 位置保持一致
         System.out.println(name);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
