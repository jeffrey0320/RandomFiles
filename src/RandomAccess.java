import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class RandomAccess {
    public static void main(String[] args) throws IOException {
        write();
        read();
    }

    public static void write() throws IOException {
        final int NUM_ITEMS = 2; // Number of items
        String description;      // Item description
        int units;               // Units on hand

        // Create a Scanner object for keyboard input.
        Scanner keyboard = new Scanner(System.in);

        // Create an array to hold InventoryItem objects.
        InventoryItem[] items = new InventoryItem[NUM_ITEMS];

        // Get data for the InventoryItem objects.
        System.out.println("Enter data for " + NUM_ITEMS + " inventory items.");

        for (int i = 0; i < items.length; i++)
        {
            // Get the description.
            System.out.print("Enter an item description: ");
            description = keyboard.nextLine();

            // Get the units on hand.
            System.out.print("Enter the number of units: ");
            units = keyboard.nextInt();

            // Consume the remaining newline.
            keyboard.nextLine();

            // Create an InventoryItem object in the array.
            items[i] = new InventoryItem(description, units);
        }

        // Create an InventoryFile object.
        InventoryItemFile file = new InventoryItemFile("Inventory.dat");

        // Write the contents of the array to the file.
        for (int i = 0; i < items.length; i++)
        {
            file.writeInventoryItem(items[i]);
        }

        // Close the file.
        file.close();

        System.out.println("The data was written to the Inventory.dat file.");
        System.out.println();
    }

    public static void read() throws IOException {
        int recordNumber;     // Record number
        String again;         // To get a Y or an N
        InventoryItem item;   // An object from the file

        // Create a Scanner object for keyboard input.
        Scanner keyboard = new Scanner(System.in);

        // Open the file.
        InventoryItemFile file = new InventoryItemFile("Inventory.dat");

        // Report the number of records in the file.
        System.out.println("The Inventory.dat file has " + file.getNumberOfRecords() + " records.");

        // Get a record number from the user and
        // display the record.
        do {
            // Get the record number.
            System.out.print("Enter the number of the record you wish to see: ");
            recordNumber = keyboard.nextInt();

            // Consume the remaining newline.
            keyboard.nextLine();

            // Move the file pointer to that record.
            file.moveFilePointer(recordNumber);

            // Read the record at that location.
            item = file.readInventoryItem();

            // Display the record.
            System.out.println("\nDescription: " + item.getDescription());
            System.out.println("Units: " + item.getUnits());

            // Ask the user whether to get another record.
            System.out.print("\nDo you want to see another record? (Y/N): ");
            again = keyboard.nextLine();
        } while (again.charAt(0) == 'Y' || again.charAt(0) == 'y');

        // Close the file.
        file.close();

        System.out.println("\nDone");
    }
}
