public class Customer {
    String customerId;
    String firstName;
    String lastName;
    String address;
    String phone;
    String email;
    String startDate;
    double facilityDistance;
    String status;
    String facilityId;

    public Customer(String customerId, String firstName, String lastName, String address, String phone,
            String email, String startDate, double facilityDistance, String status, String facilityId) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.startDate = startDate;
        this.facilityDistance = facilityDistance;
        this.status = status;
        this.facilityId = facilityId;
    }

    @Override
    public String toString() {
        return customerId + " | " + firstName + " " + lastName + " | " + address + " | " + phone + " | " + email
                + " | start: " + startDate + " | " + facilityDistance + " mi | " + status + " | facility: " + facilityId;
    }
}
