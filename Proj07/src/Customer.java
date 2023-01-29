public class Customer {
    private String idNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public Customer(String idNumber, String firstName, String lastName, String email, String phone) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
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

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public String toString() {
        return "ID: " + idNumber + " Name: " + firstName + " " + lastName + " Email: " + email + " Phone: " + phone;
    }
}
