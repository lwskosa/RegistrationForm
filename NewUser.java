package main.java.com.techbee.lajoskosa.web.registrationform;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewUser {
    private final String CUSTOMER_SERVICE_NUMBER = "1-800-000-0000";
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String userName;
    private int age;
    DAO dao;

    public NewUser(DAO dao) {
        this.firstName = "";
        this.lastName = "";
        this.phoneNumber = "";
        this.email = "";
        this.userName = "";
        this.age = -1;
        this.dao = dao;

    }

    public NewUser(String firstName, String lastName, String phoneNumber, String email, String userName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userName = userName;
        this.age = age;
    }
//    ==================
//    = GET USER INPUT =
//    ==================

    /**
     *
     * @return
     */
    public String getUserString() {
        return new Scanner(System.in).nextLine();
    }
//    =======================
//    = ENCAPSULATION RULES =
//    =======================
//    ---- First Name
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

//    ---- Last Name
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

//    ---- Phone Number
    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber.replaceAll("[ .\\-\\(\\)]","").trim(); }

    public String getFormattedPhoneNumber(){
        Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{4})");
        Matcher matcher = pattern.matcher(getPhoneNumber());
        return matcher.replaceFirst("1-$1-$2-$3");
    }
//    ---- Email Address
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email.trim(); }

//    ---- Username
    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName.trim(); }

//    ---- Age
    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

//    ====================
//    = VALIDATION RULES =
//    ====================
//    ----  Limits
    private final int NAME_MIN_LENGTH     = 1;
    private final int NAME_MAX_LENGTH     = 24;
    private final int PHONE_MAX_LENGTH    = 10;
    private final int EMAIL_MIN_USER_LENGTH = 2;
    private final int USERNAME_MIN_LENGTH = 8;
    private final int USERNAME_MAX_LENGTH = 24;
    private final int AGE_MIN = 18;
    private final int AGE_MAX = 110;

    //    ---- REGEX Patterns
    private final String regexName     = "(?i)[a-z\\- ]{1," + NAME_MAX_LENGTH + "}";
    private final String regexPhone    = "\\(?([\\d]{3})\\)?[-\\. ]?([\\d]{3})[-\\. ]?([\\d]{4})";
    private final String regexEmail    = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]{2,}+@[a-zA-Z0-9.-]+$";
    private final String regexUserName = "(?i)[a-z0-9\\-]{" + USERNAME_MIN_LENGTH + "," + USERNAME_MAX_LENGTH + "}";

    // ---- Methods
    public boolean validateName(String name) {
        return name.matches(regexName);
    }

    public boolean validatePhoneNumber() {
        return getPhoneNumber().matches(regexPhone);
    }

    public boolean validateEmail() {
        return getEmail().matches(regexEmail);
    }

    public boolean validateUserName() {
        return getUserName().matches(regexUserName);
    }

    public boolean validateAge() {
        final int age = getAge();
        return (age >= AGE_MIN && age <= AGE_MAX);
    }
//    =============
//    = FORM DATA =
//    =============

    public void formGetFirstName() {
        do {
            System.out.println("Please enter your First Name:");
            setFirstName(getUserString());

            feedbackName(getFirstName());

        } while (!validateName(getFirstName()));
    }

    public void formGetLastName() {
        do {
            System.out.println("Please enter your Last Name:");
            setLastName(getUserString());
            feedbackName(getLastName());

        } while (!validateName(getLastName()));
    }

    public void formGetPhoneNumber() {
        do {
            System.out.println("Please enter your Phone Number:");
            setPhoneNumber(getUserString());
            feedbackPhone();

        } while (!validatePhoneNumber());
    }

    public void formGetEmail() {
        do {
            System.out.println("Please enter your Email:");
            setEmail(getUserString());
            feedbackEmail();

        } while (!validateEmail());
    }

    public void formGetUserName() {
        do {
            System.out.println("Please enter Username you would like to use:");
            setUserName(getUserString());
            feedbackUserName();
        } while (!validateUserName());
    }

    public void formGetAge() {
        do {
            System.out.println("Please enter your age:");
            setAge(intTryParse(getUserString(),AGE_MIN,AGE_MAX));
            feedbackAge();

        } while (!validateAge());
    }


    /**
     * TryParse implementation
     *
     * @param value String value to be parsed as int
     * @return Parsed integer
     */
    private int intTryParse(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return Integer.MIN_VALUE;
        }
    }

    /**
     * TryParse implementation
     *
     * @param value String value to be parsed as int
     * @param min   Minimum acceptable value
     * @param max   Maximum acceptable value
     * @return Parsed integer
     */
    private int intTryParse(String value, int min, int max) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return Integer.MIN_VALUE;
        }
    }

    /**
     * PadLeft implementation. User for text formatting, clamps text to left side
     *
     * @param text      text to be padded to the left
     * @param padAmount total character size
     * @return Left-padded text
     */
    protected String padLeft(String text, int padAmount) {
        return String.format("%-" + padAmount + "s", text);
    }

    /**
     * PadLeft implementation. User for text formatting, clamps text to right side
     *
     * @param text      text to be padded to the right
     * @param padAmount total character size
     * @return Right-padded text
     */
    protected String padRight(String text, int padAmount) {
        return String.format("%" + padAmount + "s", text);
    }

//    ==================================
//    = DISPLAY HINTS ABOUT BAD INPUTS =
//    ==================================


    private void feedbackName(String name) {
        if (!validateName(getFirstName())) {
            if (name.equals("")) {
                System.out.printf("► Name is required and must be at least %d character long\n", NAME_MIN_LENGTH);
            }
            if (name.length() > NAME_MAX_LENGTH) {
                System.out.printf("► Name must be less than %d characters\n" +
                                "======================================= IMPORTANT! =======================================\n\t" +
                                    "Please contact Customer Service at %d if this error was not a mistake\n" +
                                "==========================================================================================\n",
                        NAME_MAX_LENGTH, CUSTOMER_SERVICE_NUMBER);
            }
            if (name.matches("(?i)[^a-z'-]")) ;
            {
                System.out.println("► Name must must not contain numbers or special characters with the exception of apostrophes (\"'\") and hyphen (\"-\")");
            }
        }
        printSeparator();
    }

    private void feedbackPhone() {
        if (getPhoneNumber().equals("")) {
            System.out.println("► Phone is required and cannot be empty");
        }
        if (getPhoneNumber().length() != PHONE_MAX_LENGTH) {
            System.out.println("► Please check if number was entered correctly. 3 digit area code, 7 digit phone number");
        }
        if (getPhoneNumber().matches("(?i)[a-z!@#$%^&*()+\\/]")) {
            System.out.println("► Only numbers, spaces (\" \"), periods (\".\") and hyphens (\"-\") are allowed");
        }
        printSeparator();
    }

    private void feedbackEmail() {
        String email = getEmail();
        String user  = email.substring(1,email.indexOf("@"));
        if(email.equals("")){
            System.out.println("► Email is required and cannot be empty");
        }
        if(user.length() < EMAIL_MIN_USER_LENGTH){
            System.out.printf("► Username must be at least %d characters long\n", EMAIL_MIN_USER_LENGTH);
        }
        if (email.matches("[!#$%^&\\*\\(\\)+/]")) {
            System.out.println("► Invalid Email address - no special characters are allowed");
        }
        printSeparator();
    }

    private void feedbackUserName() {
        if (getUserName().length() < USERNAME_MIN_LENGTH || getUserName().length() > USERNAME_MAX_LENGTH) {
            System.out.printf("► Username must be between %d and %d characters\n",
                    USERNAME_MIN_LENGTH, USERNAME_MAX_LENGTH);
        }
        if (getUserName().matches("(?i)[^a-z'-]")) {
            System.out.println("► Username must must not contain numbers or special characters");
        }
        printSeparator();
    }

    private void feedbackAge(){
        if(!validateAge()){
            System.out.printf("Please enter only positive Integer numbers between %d and %d.\n\tUnable to parse value\n", AGE_MIN, AGE_MAX);
        }
        printSeparator();
    }

    public void displayReview(){
        System.out.println("====================================================================");
        System.out.println("=                    PLEASE REVIEW YOUR CHOICES                    =");
        System.out.println("====================================================================");
        System.out.println(this);
        optionsMainMenu(dao);



    }

    public void registerUserFull(){
        formGetFirstName();
        formGetLastName();
        formGetPhoneNumber();
        formGetEmail();
        formGetUserName();
        formGetAge();
        displayReview();
    }


    private void optionsMainMenu(DAO dao){
        System.out.println("\t1. All values are correct, confirm registration");
        System.out.println("\t2. Edit value(s)");
        printSeparator(8,'*');
        System.out.println("\t5. Start over (current changes will be lost)");
        printSeparator(8,'*');
        System.out.println("\t0. Cancel registration and quit (all data will be lost!)");
        System.out.print("Please select an option: ");
        String choice = getUserString();
        switch(choice){
            case "1":
                System.out.println("Call DAO functions");
                dao.addUser(this);
                break;

            case "2":
                optionsEditMenu();
                break;

            case "5":
                registerUserFull();
                break;

            case "0":
                System.exit(0);
                break;

            default:
                System.out.println("Please enter a number");
                optionsMainMenu(dao);
                break;
        }
    }

    public void optionsEditMenu(){
        System.out.println("What would you like to edit?");
        System.out.println("\t1. First Name");
        System.out.println("\t2. Last Name");
        System.out.println("\t3. Phone Number");
        System.out.println("\t4. Email");
        System.out.println("\t5. Username");
        System.out.println("\t6. Age");
        printSeparator(8,'*');
        System.out.println("\t0. Done Editing");
        System.out.print("Please enter choice: ");
        String choice = getUserString();

        switch(choice){
            case "1":
                formGetFirstName();
                optionsEditMenu();
                break;

            case "2":
                formGetLastName();
                optionsEditMenu();
                break;

            case "3":
                formGetPhoneNumber();
                optionsEditMenu();
                break;

            case "4":
                formGetEmail();
                optionsEditMenu();
                break;
            case "5":
                formGetUserName();
                optionsEditMenu();
                break;
            case "6":
                formGetAge();
                optionsEditMenu();
                break;
            case "0":
                displayReview();
                optionsMainMenu(dao);
                break;

            default:
                System.out.println("Please enter a number in list");
                optionsMainMenu(dao);
                break;
        }
    }

    @Override
    public String toString(){
        return String.format(
                "\tFirst Name...... %s\n" +
                "\tLast Name....... %s\n" +
                "\tPhone Number.... %s\n" +
                "\tEmail Address... %s\n" +
                "\tUsername........ %s\n" +
                "\tAge............. %d\n\n",
                getFirstName(),getLastName(),getFormattedPhoneNumber(),getEmail(),getUserName(),getAge());
    }

    private void printSeparator(){
        System.out.println("----------------\n");
    }
    private void printSeparator(int amount, char character){
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<amount;i++){
            builder.append(character);
        }
        System.out.println(builder);
    }



}
