# vsk-entertaintment-system-
VSK Entertainment - Sound Rental & Event Package Booking System

A console-based desktop application developed in Java that demonstrates the practical application of core Data Structures and Algorithms (DSA) to solve real-world workflows in an entertainment equipment rental business.

This project was developed as part of the Data Structures and Algorithms (ICT 143-2) practical course assignment.

 Project Overview
VSK Entertainment relies on smooth logistics to manage high-demand audio gear and customer packages. This application replaces manual entry pipelines with dynamic data processing components written in pure Java, avoiding any external library or third-party database overhead.

The system provides a clear interactive terminal menu that enables operators to:
1. Search inventory items dynamically using direct database queries.
2. Manage a live customer selection cart using structural memory links.
3. Route transactions fairly through an order processing stream.
4. Instantly organize dynamic package price rates.


 Data Structures & Algorithms Used

To satisfy the system operational requirements, the application implements four distinct technical mechanisms:

1. Binary Search Tree (BST) [Inventory Control]:Used to manage and store available equipment. Items are structurally arranged hierarchically by name to avoid linear scanning overhead  lookup speed).
2. Singly Linked List [Customer Cart]:Powers the temporary shopper selections. Since items are added or shifted on the fly, link-node modifications (`node.next`) eliminate the continuous allocation costs of standard static arrays.
3. FIFO Queue [Booking Pipeline]: Handles finalized order pipelines sequentially. Operates on a strict First-In, First-Out rule (`Enqueue` and `Dequeue`) to ensure customer service transparency.
4. QuickSort Algorithm [Rental Packages] Applied to preset event bundles to organize them instantly by price range using a divide-and-conquer strategy average complexity).



 Setup & Execution Instructions

Follow these simple steps to run the application on your local machine using the terminal or Command Prompt (CMD).

 Prerequisites
 Ensure you have the Java Development Kit (JDK 8 or higher) installed on your operating system.
 Verify your Java installation by running this command in your terminal:
 
  java -version
