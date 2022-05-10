package main;

import java.sql.*;

public class DBHandler extends ConfigsDb{
    Connection dbConnection;
    public int id;

    public Connection getDBConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        // Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }



    public void executeQuery(String query) {
        Connection conn = null;
        try {
            conn = getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public int getIdFromName(String name){
        Connection conn = null;
        try {
            conn = getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = "SELECT city_id FROM city WHERE city_name = '"+ name + "'";
        //String query = "SELECT city_id FROM city";

        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while(rs.next()) {
                id = rs.getInt(Const.CITY_ID);
                System.out.println(id);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

}
