package IMS;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Inventory {
    private final List<Items> items = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    public static void main(String[] ajiMeraKaddu) {
        Inventory inventory = new Inventory();
        System.out.println("========================Main Menu========================");
        System.out.println("1. Add an item in the inventory.");
        System.out.println("2. Increase the count value of an item");
        System.out.println("3. Decrease the count value of an item");
        System.out.println("4. Delete the item from the inventory.");
        System.out.println("5. Update the existing item of the inventory");
        System.out.println("6. Display all the items of the inventory.");
        System.out.println("7. Search the item in the inventory by name and category.");
        System.out.println("8. Exit the program.");
        System.out.print("Enter your choice: ");
        int choice = inventory.sc.nextInt();
        inventory.sc.nextLine(); //To consume(Flush) the remaining newline characters
        while (true) {
            String name;
            String category;
            int posi;
            switch (choice) {
                case 1:
                    inventory.addItem();
                    System.out.println("Item added successfully....👍");
                    break;

                case 2:
                    System.out.print("Enter the name of item: ");
                    name = inventory.sc.nextLine();
                    System.out.print("Enter the category of item: ");
                    category = inventory.sc.nextLine();
                    posi = inventory.searchItem(name, category);
                    if (posi == -1) {
                        System.out.println("Item not found⚠️. Please try again.");
                        break;
                    } else {
                        System.out.print("Enter the count value: ");
                        int countValue = inventory.sc.nextInt();
                        while (countValue<=0){
                            System.out.print("🙏Please provide me the positive integer as a count value: ");
                            countValue = inventory.sc.nextInt();
                        }
                        inventory.sc.nextLine();
                        inventory.incItemCount(posi, countValue);
                    }
                    System.out.println("Increment of item count done successfully....👍");
                    break;

                case 3:
                    System.out.print("Enter the name of item: ");
                    name = inventory.sc.nextLine();
                    System.out.print("Enter the category of item: ");
                    category = inventory.sc.nextLine();
                    posi = inventory.searchItem(name, category);
                    if (posi == -1) {
                        System.out.println("Item not found⚠️. Please try again.");
                        break;
                    } else {
                        System.out.print("Enter the count value: ");
                        int countValue = inventory.sc.nextInt();
                        while (countValue<=0){
                            System.out.print("🙏Please provide me the positive integer as a count value: ");
                            countValue = inventory.sc.nextInt();
                        }
                        inventory.sc.nextLine();
                        inventory.decItemCount(posi, countValue);
                        System.out.println("Decrement of item count done successfully....👍");
                    }
                    break;

                case 4:
                    System.out.print("Enter the name of item: ");
                    name = inventory.sc.nextLine();
                    System.out.print("Enter the category of item: ");
                    category = inventory.sc.nextLine();
                    inventory.deleteItem(name, category);
                    break;

                case 5:
                    inventory.updateItem();
                    break;

                case 6:
                    inventory.displayItems();
                    System.out.println("Displaying done successfully...👍");
                    break;

                case 7:
                    System.out.print("Enter the name of the item: ");
                    name = inventory.sc.nextLine();
                    System.out.print("Enter the category of the item: ");
                    category = inventory.sc.nextLine();
                    int index = inventory.searchItem(name, category);
                    if (index == -1) {
                        System.out.println("Oopss🙈, No such item found in the inventory...");
                    } else {
                        System.out.println("Hurray👏, item found at " + index + " location");
                    }
                    break;
                case 8:
                    System.out.println("...Exiting the program...\n🙋‍♀️Bye Bye🙋‍, Have a great time...");
                    inventory.sc.close();
                    System.exit(0);
                default:
                    System.out.println("Oopss⚠️, Invalid choice. Please try again.");
                    break;
            }
            System.out.println("Press Enter continue...");
            inventory.sc.nextLine();
            System.out.println("========================Main Menu========================");
            System.out.println("1. Add an item in the inventory.");
            System.out.println("2. Increase the count value of an item");
            System.out.println("3. Decrease the count value of an item");
            System.out.println("4. Delete the item from the inventory.");
            System.out.println("5. Update the existing item of the inventory");
            System.out.println("6. Display all the items of the inventory.");
            System.out.println("7. Search the item in the inventory by name and category.");
            System.out.println("8. Exit the program.");
            System.out.print("Enter your choice: ");
            choice = inventory.sc.nextInt();
            inventory.sc.nextLine();
        }
    }

    private void addItem() {
        String id = generateId();
        while (isIdAlreadyGiven(id)) {
            id = generateId();
            sc.nextLine();//to consume(Flush) the remaining newline characters
        }
        System.out.print("Enter item name: ");
        String name = sc.nextLine();
        System.out.print("Enter item category: ");
        String category = sc.nextLine();
        int posi = searchItem(name, category);
        if (posi == -1) {
            items.add(new Items(id, name, category, 1));
        } else {
            incItemCount(posi, 1);
        }
    }

    private String generateId() {
        Random random = new Random();
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        return "ItemID#" +
                alphabets.charAt(random.nextInt(52)) +
                alphabets.charAt(random.nextInt(52)) +
                alphabets.charAt(random.nextInt(52)) +
                numbers.charAt(random.nextInt(10)) +
                numbers.charAt(random.nextInt(10)) +
                numbers.charAt(random.nextInt(10));
    }

    private boolean isIdAlreadyGiven(String id) {
        for (Items item : items) {
            if (item.getItemId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private int searchItem(String name, String category) {
        int positionOfItem = -1;
        for (Items item : items) {
            if (item.getItemName().equalsIgnoreCase(name.trim())
                    && item.getItemCategory().equalsIgnoreCase(category.trim())) {
                positionOfItem = items.indexOf(item);
                break;
            }
        }
        return positionOfItem;
    }

    private void incItemCount(int position, int countValue) {
        items.get(position).setItemCount(items.get(position).getItemCount() + countValue);
    }

    private void decItemCount(int position, int countValue) {
        items.get(position).setItemCount(items.get(position).getItemCount() - countValue);
        if (items.get(position).getItemCount() <= 0) {
            System.out.print("Count value of this item become zero or less than zero, So ");
            deleteItem(items.get(position).getItemName(), items.get(position).getItemCategory());
        }
    }

    private void deleteItem(String name, String category) {
        if (items.isEmpty()) {
            System.out.println("There is no item in the inventory, Please add some...🙏");
        } else {
            int posi = searchItem(name, category);
            if (posi == -1) {
                System.out.println("Item not found⚠️. Please try again.");
            } else {
                items.remove(posi);
                System.out.println("Your item has been deleted successfully from the inventory... 🗑️");
            }
        }
    }

    private void updateItem() {
        System.out.print("Enter the name of item: ");
        String name = sc.nextLine();
        System.out.print("Enter the category of item: ");
        String category = sc.nextLine();
        int posi = searchItem(name, category);
        if (posi == -1) {
            System.out.println("Item not found⚠️. Please try again.");
        } else {
            System.out.println("1. Name");
            System.out.println("2. Category");
            System.out.println("3. Count");
            System.out.print("What would you like to update? : ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine().trim();
                    int position = searchItem(newName, category);
                    if (position != -1 && !name.equalsIgnoreCase(newName)) {
                        System.out.println(
                                "Item matched with an existing item, So we have incremented the count of the existing item.");
                        incItemCount(position, items.get(posi).getItemCount());
                        items.remove(posi);
                    } else {
                        items.get(posi).setItemName(newName);
                        System.out.println("Good job, Your item has been updated successfully in the inventory...👍");
                    }
                }
                case 2 -> {
                    System.out.print("Enter new category: ");
                    String newCat = sc.nextLine().trim();
                    int position = searchItem(name, newCat);
                    if (position != -1 && !category.equalsIgnoreCase(newCat)) {
                        System.out.println(
                                "Item matched with an existing item, So we have incremented the count of the existing item.");
                        incItemCount(position, items.get(posi).getItemCount());
                        items.remove(posi);
                    } else {
                        items.get(posi).setItemCategory(newCat);
                        System.out.println("Good job, Your item has been updated successfully in the inventory...👍");
                    }
                }
                case 3 -> {
                    System.out.print("⚠️You are forcefully changing the count value⚠️, Are you sure you want to update the count value(Y/N): ");
                    char confirm = sc.next().charAt(0);
                    while (confirm != 'n' && confirm != 'N' && confirm != 'y' && confirm != 'Y'){
                        System.out.print("Andhe ho kya bhai🙈, Type only (Y/N): ");
                        confirm = sc.next().charAt(0);
                    }
                    if (confirm == 'n' || confirm == 'N') {
                        sc.nextLine(); //Flush
                        return;
                    }
                    System.out.print("Enter new count value: ");
                    int newCount = sc.nextInt();
                    sc.nextLine(); //Flush
                    if (newCount > 0) {
                        items.get(posi).setItemCount(newCount);
                        System.out.println("Good job, Your item has been updated successfully in the inventory...👍");
                    } else {
                        System.out.println("Invalid count⚠️. Please try again.");
                    }
                }
                default -> System.out.println("Wrong input! Try again.");
            }
        }
    }

    private void displayItems() {
        if (items.isEmpty()) {
            System.out.println("\t⚠️⚠️There is no item in the inventory, Please add some...🙏");
        } else {
            System.out.println("Displaying all the items of the inventory: ");
            for (Items item : items) {
                System.out.println("\t"+item);
            }
        }
    }
}