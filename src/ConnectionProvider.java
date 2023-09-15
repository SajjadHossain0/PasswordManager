/*
password_manager - Database
users - table for users
password_data - table for that user to store data
users - user_data > are connected by foreign key(user_id)

// to check username pass are correct ot not.
select * from users where username ='value' and password ='value';

// to add new user.
insert into users(username, password) values ('value','value');

// show user data from user_data table through foreign key(user_id) from users table.
SELECT * FROM user_data WHERE User_id = [user_id];

// save data to user_data table
insert into user_data(Name, Email, Password, Details, User_id) values('value','value','value',[user_id])

// update data of user_data table.
update user_data set Name = 'value', Email = 'value', Password = 'value', Details = 'value' where ID = [id];

// delete row/ user data fron user_data table.
delete from user_data where id= [id];

*/

import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectionProvider {
    private static Connection connection = null;
    public static Connection getConnection(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/password_manager",
                    username = "root",
                    password = "*****";

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("!! success !!");

        }catch (Exception e){
            System.out.println(e);
        }

        return connection;
    }
}

