import org.junit.Test;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class TestZhihu {

    @Test
    public void test() throws IOException, ClassNotFoundException, SQLException {

        FileInputStream in = new FileInputStream("src/main/resources/db.properties");
        InputStream in2 = new BufferedInputStream(in);
        Properties prop = new Properties();
        prop.load(in2);
        String driver = prop.getProperty("jdbc.driver");
        String url = prop.getProperty("jdbc.url");
        String username = prop.getProperty("jdbc.username");
        String password = prop.getProperty("jdbc.password");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "SELECT author__id,author__photo FROM author_tab WHERE  author__photo LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%");

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            String author__id = rs.getString("author__id");
            String author__photo = rs.getString("author__photo");
            author__photo = author__photo.replace("D:datazhihu/", "D:/data/zhihu/");


            sql = "UPDATE author_tab SET author__photo=? WHERE author__id = ?";
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setString(1, author__photo);
            prep.setString(2, author__id);
            if (prep.execute()) {
                System.out.println("OK" + author__id);
            }
        }

    }
    @Test
    public void test2() throws IOException, ClassNotFoundException, SQLException {

        FileInputStream in = new FileInputStream("src/main/resources/db.properties");
        InputStream in2 = new BufferedInputStream(in);
        Properties prop = new Properties();
        prop.load(in2);
        String driver = prop.getProperty("jdbc.driver");
        String url = prop.getProperty("jdbc.url");
        String username = prop.getProperty("jdbc.username");
        String password = prop.getProperty("jdbc.password");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "SELECT author__id,author__bgphoto FROM author_tab WHERE  author__bgphoto LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "D:datazhihu/%");

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            String author__id = rs.getString("author__id");
            String author__bgphoto = rs.getString("author__bgphoto");
            author__bgphoto = author__bgphoto.replace("D:datazhihu/", "D:/data/zhihu/");


            sql = "UPDATE author_tab SET author__bgphoto=? WHERE author__id = ?";
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setString(1, author__bgphoto);
            prep.setString(2, author__id);
            if (prep.execute()) {
                System.out.println("OK" + author__id);
            }
        }

    }
    @Test
    public void test3() throws IOException, ClassNotFoundException, SQLException {

        FileInputStream in = new FileInputStream("src/main/resources/db.properties");
        InputStream in2 = new BufferedInputStream(in);
        Properties prop = new Properties();
        prop.load(in2);
        String driver = prop.getProperty("jdbc.driver");
        String url = prop.getProperty("jdbc.url");
        String username = prop.getProperty("jdbc.username");
        String password = prop.getProperty("jdbc.password");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "SELECT a_id,content FROM answer_tab WHERE  content LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "D:datazhihu/%");

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            String a_id = rs.getString("a_id");
            String content = rs.getString("content");
            content = content.replace("D:datazhihu/", "D:/data/zhihu/");


            sql = "UPDATE answer_tab SET content=? WHERE a_id = ?";
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setString(1, content);
            prep.setString(2, a_id);
            if (prep.execute()) {
                System.out.println("OK" + a_id);
            }

        }
        System.out.println(sql);
    }


}
