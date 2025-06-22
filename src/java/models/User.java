
package models;

import java.sql.Date;


public class User {

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", username=" + username + ", password=" + password + ", role=" + role + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", dob=" + dob + ", phoneNumber=" + phoneNumber + '}';
    }

    public User(int userId, String username, String password, String role, String firstName, String lastName, String email, Date dob, String phoneNumber) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
    }
    
    public User() {
        this.userId = 0;
        this.username = "Empty";
        this.password = "EmptyPassword";
        this.role = "No Role selected";
        this.firstName = "no first Name";
        this.lastName = "No Last Name";
        this.email = "Empty Emal";
        this.dob = null;
        this.phoneNumber = "Empty Phone";
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    private int userId;
    private String username;
    private String password;
    private String role; // admin, librarian, patron
    private String firstName;
    private String lastName;
    private String email;
    private java.sql.Date dob;
    private String phoneNumber;
}
