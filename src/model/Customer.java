//
//package model;
//public class Customer {
//    private String firstName;
//    private String lastName;
//    private String mobile;
//    // Constructor with all parameters
//    public Customer(String firstName, String lastName, String mobile) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.mobile = mobile;
//    }
//    // Optional Constructor with firstName and lastName
//    public Customer(String firstName, String lastName) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.mobile = ""; // Set a default value for mobile if not provided
//    }
//    // Getters
//    public String getFirstName() {
//        return firstName;
//    }
//    public String getLastName() {
//        return lastName;
//    }
//    public String getMobile() {
//        return mobile;
//    }
//}
//package model;
//
//public class Customer {
//    private String firstName;
//    private String lastName;
//    private String mobile;
//
//    // Constructors
//    public Customer(String firstName, String lastName, String mobile) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.mobile = mobile;
//    }
//
//    public Customer(String firstName, String lastName) {
//        this(firstName, lastName, "");
//    }
//
//    // Getters
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//}
package model;

public class Customer {
    private String firstName;
    private String lastName;
    private String mobile;

    public Customer(String firstName, String lastName, String mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getMobile() { return mobile; }
}
