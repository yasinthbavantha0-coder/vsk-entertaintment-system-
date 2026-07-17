import java.util.ArrayList;
import java.util.Scanner;

// ==========================================
// 1. DATA STRUCTURE NODE DEFINITIONS
// ==========================================

// Binary Search Tree Node for Inventory Control
class BSTNode {
    int id;
    String name;
    int price;
    int stock;
    BSTNode left, right;

    public BSTNode(int id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.left = this.right = null;
    }
}

// Singly Linked List Node for Customer Cart
class CartNode {
    String name;
    int price;
    CartNode next;

    public CartNode(String name, int price) {
        this.name = name;
        this.price = price;
        this.next = null;
    }
}

// Queue Node Object for Booking FIFO Queue
class Order {
    String customerName;
    int totalAmount;

    public Order(String customerName, int totalAmount) {
        this.customerName = customerName;
        this.totalAmount = totalAmount;
    }
}

// Package Object for Rental Sorting
class RentalPackage {
    String packageName;
    int price;

    public RentalPackage(String packageName, int price) {
        this.packageName = packageName;
        this.price = price;
    }
}

// ==========================================
// 2. MAIN SYSTEM CORE & DASHBOARD
// ==========================================
public class VSK_Dashboard {
    private static BSTNode inventoryRoot = null;
    private static CartNode cartHead = null;
    private static ArrayList<Order> bookingQueue = new ArrayList<>(); // FIFO Queue Representation
    private static ArrayList<RentalPackage> packages = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Preset Core Data Loading
        initData();

        while (true) {
            System.out.println("\n=======================================================");
            System.out.println("                 VSK ENTERTAINMENT                     ");
            System.out.println("     Sound Rental & Event Package Booking System       ");
            System.out.println("=======================================================");
            System.out.println("1. Search Inventory Items (Binary Search Tree Search) ");
            System.out.println("2. Add Rental Item to Shopping Cart (Linked List Node)");
            System.out.println("3. Place Booking / Process Next Queue Order (FIFO)   ");
            System.out.println("4. View Preset Rental Packages Sorted by Price (QuickSort)");
            System.out.println("5. Exit Application                                    ");
            System.out.print("Select an option (1-5): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    searchInventoryMenu();
                    break;
                case "2":
                    cartMenu();
                    break;
                case "3":
                    queueMenu();
                    break;
                case "4":
                    sortPackagesMenu();
                    break;
                case "5":
                    System.out.println("\nExiting System. Thank you for using VSK Entertainment!");
                    System.exit(0);
                default:
                    System.out.println("\n[Error] Invalid Input! Please enter a number between 1 and 5.");
            }
        }
    }

    private static void initData() {
        // BST Setup
        inventoryRoot = insertBST(inventoryRoot, 1, "Mic", 1500, 15);
        inventoryRoot = insertBST(inventoryRoot, 2, "Mic Stand", 1200, 10);
        inventoryRoot = insertBST(inventoryRoot, 3, "Line Array", 45000, 4);
        inventoryRoot = insertBST(inventoryRoot, 4, "JBL Speaker", 18000, 8);
        inventoryRoot = insertBST(inventoryRoot, 5, "Bin", 25000, 6);
        inventoryRoot = insertBST(inventoryRoot, 6, "Keyboard", 32000, 3);

        // QuickSort Preload Array Setup
        packages.add(new RentalPackage("Wedding Sound Pack", 45000));
        packages.add(new RentalPackage("Small Birthday Pack", 12000));
        packages.add(new RentalPackage("Outdoor Concert Pack", 95000));
    }

    // ==========================================
    // MODULE 1: BINARY SEARCH TREE UTILITIES
    // ==========================================
    private static BSTNode insertBST(BSTNode root, int id, String name, int price, int stock) {
        if (root == null) return new BSTNode(id, name, price, stock);
        if (name.compareToIgnoreCase(root.name) < 0) {
            root.left = insertBST(root.left, id, name, price, stock);
        } else {
            root.right = insertBST(root.right, id, name, price, stock);
        }
        return root;
    }

    private static void searchInventoryMenu() {
       12sq23wefsxwsesdweswdeddddddd
        System.out.print("\nEnter keyword to search inventory (Mic, Mic Stand, Line Array, JBL Speaker, Bin, Keyboard): ");
        String query = scanner.nextLine().trim().toLowerCase();
        if (query.isEmpty()) return;

        System.out.println("\n✔️ SEARCH RESULTS IN BST DATABASE:");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        boolean[] found = {false};
        traverseAndSearchBST(inventoryRoot, query, found);

        if (!found[0]) {
            System.out.println(" No matching item found in Binary Search Tree.");
        }
    }

    private static void traverseAndSearchBST(BSTNode node, String query, boolean[] found) {
        if (node == null) return;
        if (node.name.toLowerCase().contains(query)) {
            System.out.println("• ID: " + node.id + " | Name: " + node.name + " | Price: Rs." + node.price + " | Stock: " + node.stock);
            found[0] = true;
        }
        traverseAndSearchBST(node.left, query, found);
        traverseAndSearchBST(node.right, query, found);
    }

    // ==========================================
    // MODULE 2: LINKED LIST SHOPPING CART UTILITIES
    // ==========================================
    private static void cartMenu() {
        String[] presetItems = {"Mic", "Mic Stand", "Line Array", "JBL Speaker", "Bin", "Keyboard"};
        int[] presetPrices = {1500, 1200, 45000, 18000, 25000, 32000};

        System.out.println("\nAvailable Items To Add:");
        for (int i = 0; i < presetItems.length; i++) {
            System.out.println((i + 1) + ". " + presetItems[i] + " - Rs." + presetPrices[i]);
        }
        System.out.print("Select item number to add to cart: ");
        try {
            int itemIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (itemIndex >= 0 && itemIndex < presetItems.length) {
                // Insert Node into Linked List
                CartNode newNode = new CartNode(presetItems[itemIndex], presetPrices[itemIndex]);
                if (cartHead == null) {
                    cartHead = newNode;
                } else {
                    CartNode temp = cartHead;
                    while (temp.next != null) temp = temp.next;
                    temp.next = newNode;
                }
                System.out.println(" Added successfully.");
                printCart();
            } else {
                System.out.println(" Invalid choice selection!");
            }
        } catch (Exception e) {
            System.out.println(" Please enter a valid item index number.");
        }
    }

    private static void printCart() {
        System.out.println("\n--- Current Customer Cart status ---");
        CartNode temp = cartHead;
        int total = 0;
        if (temp == null) {
            System.out.println("Shopping cart is empty.");
            return;
        }
        while (temp != null) {
            System.out.println(" " + temp.name + " — Rs." + temp.price);
            total += temp.price;
            temp = temp.next;
        }
        System.out.println("Grand Total Reference: Rs. " + total);
    }

    // ==========================================
    // MODULE 3: FIFO BOOKING QUEUE UTILITIES
    // ==========================================
    private static void queueMenu() {
        System.out.println("\nBooking Operations Queue Options:");
        System.out.println("1. Checkout Current Cart & Place Order (Enqueue)");
        System.out.println("2. Process Next Pending Booking in Pipeline (Dequeue)");
        System.out.print("Select option: ");
        String qChoice = scanner.nextLine().trim();

        if (qChoice.equals("1")) {
            System.out.print("Enter Customer Name: ");
            String customerName = scanner.nextLine().trim();
            
            CartNode temp = cartHead;
            int total = 0;
            while (temp != null) { total += temp.price; temp = temp.next; }

            if (customerName.isEmpty() || total == 0) {
                System.out.println(" Operation failed. Cart cannot be empty and name must be filled!");
                return;
            }

            // Enqueue (Add to tail of line)
            bookingQueue.add(new Order(customerName, total));
            System.out.println("✔️ Order placed in FIFO booking line successfully!");
            cartHead = null; // Flush active cart out
            printQueue();

        } else if (qChoice.equals("2")) {
            if (bookingQueue.isEmpty()) {
                System.out.println(" Queue pipeline is completely empty.");
                return;
            }
            // Dequeue (Remove from front of queue index 0)
            Order processed = bookingQueue.remove(0);
            System.out.println(" Processed Order for Customer: " + processed.customerName + " [Total: Rs. " + processed.totalAmount + "]");
            printQueue();
        }
    }

    private static void printQueue() {
        System.out.println("\n--- Current Live Booking Queue Stream ---");
        if (bookingQueue.isEmpty()) {
            System.out.println("Queue pipeline is currently empty.");
            return;
        }
        for (int i = 0; i < bookingQueue.size(); i++) {
            Order o = bookingQueue.get(i);
            System.out.println("Position [" + (i + 1) + "]: " + o.customerName + " — Total: Rs." + o.totalAmount);
        }
    }

    // ==========================================
    // MODULE 4: QUICKSORT SORTING ENGINE
    // ==========================================
    private static void sortPackagesMenu() {
        System.out.println("\nPackages before triggering sorting engine:");
        for (RentalPackage p : packages) {
            System.out.println("• " + p.packageName + " -> Rs." + p.price);
        }

        // Invoke QuickSort Algorithm Logic
        quickSort(packages, 0, packages.size() - 1);

        System.out.println("\nPackages organized cleanly by price via QuickSort algorithm:");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        for (RentalPackage p : packages) {
            System.out.println("• " + p.packageName + " ➔ Rs." + p.price);
        }
    }

    private static void quickSort(ArrayList<RentalPackage> list, int low, int high) {
        if (low < high) {
            int pIndex = partition(list, low, high);
            quickSort(list, low, pIndex - 1);
            quickSort(list, pIndex + 1, high);
        }
    }

    private static int partition(ArrayList<RentalPackage> list, int low, int high) {
        int pivot = list.get(high).price;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).price <= pivot) {
                i++;
                RentalPackage temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        RentalPackage temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }
}