public class ObjectClass {
    String fName;
    String lName;
    String address;
    String city;
    String pNumber;

    ObjectClass next;  // pointer to next node

    public ObjectClass(String fName, String lName, String address, String city, String pNumber) {
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.city = city;
        this.pNumber = pNumber;
        this.next = null;
    }

    public String fullNameKey() {
        return (lName + "," + fName).toLowerCase();
    }

    @Override
    public String toString() {
        return fName + " " + lName + " | " + pNumber + " | " + address + ", " + city;
    }
}