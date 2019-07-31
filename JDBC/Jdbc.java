import com.sun.org.glassfish.gmbal.Description;

import java.sql.*;

public class JdbcText {

    private  static String url="jdbc:mysql://localhost:3306/practice";
    private  static String user="root";
    private static  String  password="root";
    private  static ResultSet rs=null;


        public void test1() throws SQLException {
            //执行DDL操作也就是是建
            Connection conn=null;
            Statement stmt=null;
         try {
             //通过反射获取驱动的驱动程序

             Class.forName("com.mysql.jdbc.Driver");
             //从驱动程序管理类获取连接
             conn= DriverManager.getConnection(url,user,password);
             //通过Connection对象获取statement对象
             stmt=conn.createStatement();
//             准备sql语句
             String sql="CREATE TABLE student4(id INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(20),gender VARCHAR(2))";
            //执行sql语句返回结果
             int count=stmt.executeUpdate(sql);
             System.out.println(count);

         }catch ( Exception e){
             e.printStackTrace();
             throw new RuntimeException(e);

         }finally {
             if (stmt!=null)
                 try {
                     stmt.close();
                 }catch ( SQLException e){
                     e.printStackTrace();
                     throw new RuntimeException();

                 }


             if (conn!=null)
                 try{
                     conn.close();

                 }catch ( SQLException e){
                     e.printStackTrace();
                     throw new RuntimeException();
                 }


         }



        }

        public  void  test2(){
            //通过jdbc执行DML语句（insert updata delete）

            Connection conn=null;
            Statement smat=null;
            try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url,user,password);
                smat=conn.createStatement();
                String sql1="INSERT INTO student3(NAME,gender) VALUES('张磊','男')";
                String sql2="INSERT INTO student3(NAME,gender) VALUES('黎明','男')";
                String sql3= "INSERT INTO student3(NAME,gender) VALUES('刘德华','女')";
                String sql4="INSERT INTO student3(NAME,gender) VALUES('志杰','男')";

               smat.executeUpdate(sql1);
               smat.executeUpdate( sql2);
               smat.executeUpdate(sql3);
                int count=smat.executeUpdate( sql4);


               System.out.println(count);
        }catch ( Exception e){
                e.printStackTrace();
                throw new RuntimeException();

            }finally {
                if (smat!=null)
                    try {
                        smat.close();
                    }catch ( SQLException e){
                        e.printStackTrace();
                        throw new RuntimeException();

                    }
                if (conn!=null)
                    try{
                        conn.close();

                    }catch ( SQLException e){
                        e.printStackTrace();
                        throw new RuntimeException();
                    }
            }

        }


   public void test3(){

  //执行DQL（查询语句select）
       try {
           Class.forName("com.mysql.jdbc.Driver");
           Connection conn=DriverManager.getConnection(url,user,password);
           Statement stat=conn.createStatement();
           String sql="SELECT *FROM student";

           //会返回一个Resultset 对象
            rs=stat.executeQuery(sql);


            //遍历
           while (rs.next()){
               int id=rs.getInt("id");
               String name=rs.getString("name");
               int age=rs.getInt("age");
               int servlet=rs.getInt("servlet");
               int mysql=rs.getInt("mysql");
               String address=rs.getString("address");
               System.out.println(id);
               System.out.println(name);
               System.out.println(age);
               System.out.println(servlet);
               System.out.println(mysql);
               System.out.println(address);
           }

       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
  

}
