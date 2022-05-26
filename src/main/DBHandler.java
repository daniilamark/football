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
            ShowAlert.showAlertInformation("Результат удаления",  "Данные используются в другой таблице!");
        }
    }

    public int getIdFromName(String name, String entity){
        Connection conn = null;
        try {
            conn = getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = "";
        if (entity == "city") {
            query = "SELECT city_id FROM city WHERE city_name = '"+ name + "'";
        } else if (entity == "stadium") {
            query = "SELECT stadium_id FROM stadium WHERE stadium_name = '"+ name + "'";
        } else if (entity == "trainer") {
            query = "SELECT trainer_id FROM trainer WHERE trainer_name = '"+ name + "'";
        } else if (entity == "team") {
            query = "SELECT team_id FROM team WHERE team_name = '" + name + "'";
        }

        //String query = "SELECT city_id FROM city";

        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while(rs.next()) {
                if (entity == "city") {
                    id = rs.getInt(Const.CITY_ID);
                } else if (entity == "stadium") {
                    id = rs.getInt(Const.STADIUM_ID);
                } else if (entity == "trainer") {
                    id = rs.getInt(Const.TRAINER_ID);
                } else if (entity == "team") {
                    id = rs.getInt(Const.TEAM_ID);
                }

            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }
}
