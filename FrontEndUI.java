package main.java.com.techbee.lajoskosa.web.registrationform;

public class FrontEndUI {

    public static void main(String[] args) {
    DAO userDB = new DAO();
	NewUser tempUser = new NewUser(userDB);
    tempUser.registerUserFull();

    }
}
