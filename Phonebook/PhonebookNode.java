public class PhonebookNode {
    private String name;
    private String address;
    private String city;
    private int phone;
    PhonebookNode prev;
    PhonebookNode next;

    public PhonebookNode(String name, String address, String city, int phone) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
    
    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public int getPhone() {
        return this.phone;
    }

    public void print() {
        System.out.println(this.getName());
        System.out.println("\t"+this.getAddress());
        System.out.println("\t"+this.getCity());
        System.out.println("\t"+this.getPhone());
    }
}
