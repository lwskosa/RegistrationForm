package main.java.com.techbee.lajoskosa.web.registrationform;

import java.util.ArrayList;
import java.util.List;

enum Filter{
    BY_PHONENUMBER,
    BY_EMAIL,
    BY_USERNAME
}

public class DAO {
    List<NewUser> users;
    public DAO(){
        // Simulate Database fetch
        users = new ArrayList<>();
        users.add(new NewUser("Andrew","Smith",      "5551234567","andrew@email.com",  "asmith75",   47));
        users.add(new NewUser("Mary",  "Clearwater" ,"5553210987","marycw@email.com",  "MaryClear",  32));
        users.add(new NewUser("Luke",  "Groundflyer","5555855334","lukew@academy.edu", "lukepadawan",21));
    }

    public void addUser(NewUser newUser){
//        int index =  users.lastIndexOf(users.get(users.size()-1));
        users.add(newUser);
    }

    public NewUser findUser(String query, Filter filter) {
        for (NewUser user : users) {
            switch (filter) {
                case BY_PHONENUMBER:
                    if (user.getPhoneNumber().equals(query)) {
                        return user;
                    }
                case BY_EMAIL:
                    if (user.getEmail().equals(query)) {
                        return user;
                    }
                case BY_USERNAME:
                    if (user.getUserName().equals(query)) {
                        return user;
                    }
            }
        }
        return null;
    }

    public void updateUser(NewUser user){
        user.toString();
        user.optionsEditMenu();
    }

    public void deleteUser(String qury, Filter filter){


    }




}
