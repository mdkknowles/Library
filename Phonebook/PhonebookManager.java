public class PhonebookManager {
    private PhonebookNode head;
    private PhonebookNode root;
    
    public PhonebookManager() {
        PhonebookNode head = null;
        PhonebookNode root = null;
    }
    
    //The createEntry method adds the entry to the end of the list.
    public void createEntry(String name, String address, String city, int phone) {
        PhonebookNode entry = new PhonebookNode(name, address, city, phone);
        if (this.head != null) {
            this.head.next = entry;
            entry.prev = head;
            this.head = entry;
        } else {
            entry.prev = null;
            entry.next = null;
            this.head = entry;
            this.root = entry;
        }
    }

    public void deleteEntry() {
        if (this.head == null) {
            //If empty, return printed error.
            System.out.println("This phonebook is empty!");
        } else if (this.head.prev == null) {
            //If at start of list, set start of list to next element.
            this.head.next.prev = null;
            this.head = this.head.next;
        } else if (this.head.next == null) {
            //If at end of list, set end of list to previous element.
            this.head.prev.next = null;
            this.head = this.head.prev;
        } else {
            //Normally adjust to prev and next entries and reset head.
            this.head.prev.next = this.head.next;
            this.head.next.prev = this.head.prev;
            this.head = this.head.next;
        }
    }

    public void sortAlphabetical() {
        
    }

    public getNode
}