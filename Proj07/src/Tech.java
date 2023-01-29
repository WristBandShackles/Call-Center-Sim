public class Tech {
    private String idNumber;
    private String firstName;
    private String lastName;
    private String username;

    public Tech(String idNumber, String firstName, String lastName, String username) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    public String getIdNumber() {
        return this.idNumber;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getUsername() {
        return this.username;
    }

    public String toString() {
        return "ID: " + idNumber + " Name: " + firstName + " " + lastName + " Username: " + username;
    }
}
