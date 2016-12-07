package users;

import java.util.ArrayList;

/**
 * Created by piotrek on 06.12.16.
 */
public abstract class User {
    public String login;
    public String password;
    public String name;
    public String surname;
    public String email;

    public abstract ArrayList<String> getProfile();
    /*public abstract ArrayList<Course> getCourseList();*/
    /*public abstract ArrayList<Group> getGroupList();*/
}
