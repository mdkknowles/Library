/**
 * Binary Search Tree (BST) implementation to manage records.
 * Provides operations for adding, deleting, searching, traversing, and counting nodes.
 */
public class BinarySearchTree {
    private Node root; // Root node of the BST

    // Constructor to initialize an empty BST
    public BinarySearchTree() {
        this.root = null;
    }

    public void add(int key, String firstName, String lastName, String address, String city, String state, String postal, String email, String phone) {
        root = addRecursively(root, key, firstName, lastName, address, city, state, postal, email, phone);
    }

    // Recursive helper for adding a new node
    private Node addRecursively(Node current, int key, String firstName, String lastName, String address, String city, String state, String postal, String email, String phone) {
        if (current == null) {
            return new Node(key, firstName, lastName, address, city, state, postal, email, phone); // Create a new node if position is empty
        }
        if (key < current.key) {
            current.left = addRecursively(current.left, key, firstName, lastName, address, city, state, postal, email, phone); // Traverse left
        } else if (key > current.key) {
            current.right = addRecursively(current.right, key, firstName, lastName, address, city, state, postal, email, phone); // Traverse right
        }
        return current;
    }

    /**
     * Deletes a record by its key.
     *
     * @param key Unique key of the record to delete
     * @return True if the record was deleted, false if not found
     */
    public boolean delete(int key) {
        if (search(key) == null) {
            return false; // Node with the given key not found
        }
        root = deleteRecursively(root, key);
        return true;
    }

    // Recursive helper for deleting a node
    private Node deleteRecursively(Node current, int key) {
        if (current == null) return null;

        if (key < current.key) {
            current.left = deleteRecursively(current.left, key);
        } else if (key > current.key) {
            current.right = deleteRecursively(current.right, key);
        } else {
            // Node to delete found
            if (current.left == null && current.right == null) return null; // No children
            if (current.left == null) return current.right; // One child (right)
            if (current.right == null) return current.left; // One child (left)

            // Two children: Replace with in-order successor
            Node smallest = findSmallest(current.right);
            current.key = smallest.key;
            current.firstName = smallest.firstName;
            current.lastName = smallest.lastName;
            current.address = smallest.address;
            current.city = smallest.city;
            current.state = smallest.state;
            current.postal = smallest.postal;
            current.email = smallest.email;
            current.phone = smallest.phone;
            current.right = deleteRecursively(current.right, smallest.key);
        }
        return current;
    }

    // Helper to find the smallest node in a subtree
    private Node findSmallest(Node node) {
        return node.left == null ? node : findSmallest(node.left);
    }

    /**
     * Searches for a record by its key.
     *
     * @param key Unique key of the record
     * @return The Node if found, otherwise null
     */
    public Node search(int key) {
        return searchRecursively(root, key);
    }

    // Recursive helper for searching a node
    private Node searchRecursively(Node current, int key) {
        if (current == null || current.key == key) {
            return current; // Node found or end of path
        }
        return key < current.key ? searchRecursively(current.left, key) : searchRecursively(current.right, key);
    }

    /**
     * Performs in-order traversal of the BST.
     * Displays records in sorted order of keys.
     */
    public void traverseInOrder() {
        traverseInOrderRecursively(root);
    }

    private void traverseInOrderRecursively(Node node) {
        if (node != null) {
            traverseInOrderRecursively(node.left);
            System.out.println(node); // Print node using its toString() method
            traverseInOrderRecursively(node.right);
        }
    }

    /**
     * Performs pre-order traversal of the BST.
     * Displays records in root-left-right order.
     */
    public void traversePreOrder() {
        traversePreOrderRecursively(root);
    }

    private void traversePreOrderRecursively(Node node) {
        if (node != null) {
            System.out.println(node);
            traversePreOrderRecursively(node.left);
            traversePreOrderRecursively(node.right);
        }
    }
}