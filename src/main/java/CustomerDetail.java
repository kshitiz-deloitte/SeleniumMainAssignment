import java.io.FileNotFoundException;

public class CustomerDetail {
    private static String firstName;
    private static String lastName;
    private static String address;
    private static String customerID;

    CustomerDetail(String fileName) throws FileNotFoundException {
        FileHandler fileHandler = new FileHandler(fileName);
        String [] data = fileHandler.getData();
        firstName = data[0];
        lastName = data[1];
        try{
            address = data[2];
        }catch (Exception exception){

        }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    public String getFullName() {
        String fullName = this.getFirstName()+" "+this.getLastName();
        return fullName.substring(1);
    }
}
