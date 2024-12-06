public class Node {
    int key; // Unique key for the node
    String firstName, lastName, address, city, state, postal, email, phone;
    Node left, right; // Left and right child pointers

    // Constructor to initialize a new Node
    public Node(int key, String firstName, String lastName, String address, String city, String state, String postal, String email, String phone) {
        this.key = key;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postal = postal;
        this.email = email;
        this.phone = phone;
        this.left = this.right = null;
    }

    // Setters for updating fields
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setAddress(String address) { this.address = address; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setPostal(String postal) { this.postal = postal; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }

    // String representation of the Node
    @Override
    public String toString() {
        return "Key: " + key + "\n" +
                "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Address: " + address + ", " + city + ", " + state + " " + postal + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone + "\n";
    }
}

