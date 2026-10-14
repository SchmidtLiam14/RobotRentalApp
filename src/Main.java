import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Robot> robots = new ArrayList<>();
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nRobot Rental Staff Menu");
            System.out.println("1. Customers");
            System.out.println("2. Robots");
            System.out.println("3. Rent a robot");
            System.out.println("4. Return a robot");
            System.out.println("5. Schedule delivery");
            System.out.println("6. Schedule pickup");
            System.out.println("7. Reports");
            System.out.println("0. Exit");
            String choice = prompt("Choose an option: ");

            switch (choice) {
                case "1": customerMenu(); break;
                case "2": robotMenu(); break;
                case "3": rentRobot(); break;
                case "4": returnRobot(); break;
                case "5": scheduleDelivery(); break;
                case "6": schedulePickup(); break;
                case "7": reportsMenu(); break;
                case "0": running = false; break;
                default: System.out.println("Invalid option.");
            }
        }
        System.out.println("Goodbye!");
    }

    // just a helper so we dont have to print + nextLine every time
    static String prompt(String msg) {
        System.out.print(msg);
        return in.nextLine().trim();
    }

    static double promptDouble(String msg) {
        while (true) {
            try {
                return Double.parseDouble(prompt(msg));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    // customers

    static void customerMenu() {
        System.out.println("\nCustomers");
        System.out.println("1. Add customer");
        System.out.println("2. Edit customer");
        System.out.println("3. Delete customer");
        System.out.println("4. Search customers");
        System.out.println("5. List all customers");
        String choice = prompt("Choose an option: ");

        switch (choice) {
            case "1": addCustomer(); break;
            case "2": editCustomer(); break;
            case "3": deleteCustomer(); break;
            case "4": searchCustomers(); break;
            case "5": listCustomers(); break;
            default: System.out.println("Invalid option.");
        }
    }

    static Customer findCustomer(String id) {
        for (Customer c : customers) {
            if (c.customerId.equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    static void addCustomer() {
        String id = prompt("Customer ID: ");
        if (findCustomer(id) != null) {
            System.out.println("That ID already exists.");
            return;
        }
        String first = prompt("First name: ");
        String last = prompt("Last name: ");
        String address = prompt("Address: ");
        String phone = prompt("Phone: ");
        String email = prompt("Email: ");
        String startDate = prompt("Start date (YYYY-MM-DD): ");
        double distance = promptDouble("Facility distance (miles): ");
        String facilityId = prompt("Facility ID: ");

        customers.add(new Customer(id, first, last, address, phone, email, startDate, distance, "Active", facilityId));
        System.out.println("Customer added.");
    }

    static void editCustomer() {
        Customer c = findCustomer(prompt("Customer ID to edit: "));
        if (c == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.println("Current: " + c);
        System.out.println("(leave blank to keep the current value)");

        // blank input means keep whatever was there before
        String input = prompt("First name: ");
        if (!input.isEmpty()) c.firstName = input;
        input = prompt("Last name: ");
        if (!input.isEmpty()) c.lastName = input;
        input = prompt("Address: ");
        if (!input.isEmpty()) c.address = input;
        input = prompt("Phone: ");
        if (!input.isEmpty()) c.phone = input;
        input = prompt("Email: ");
        if (!input.isEmpty()) c.email = input;
        input = prompt("Facility distance (miles): ");
        if (!input.isEmpty()) c.facilityDistance = Double.parseDouble(input);
        input = prompt("Status (Active/Inactive): ");
        if (!input.isEmpty()) c.status = input;
        input = prompt("Facility ID: ");
        if (!input.isEmpty()) c.facilityId = input;

        System.out.println("Customer updated: " + c);
    }

    static void deleteCustomer() {
        Customer c = findCustomer(prompt("Customer ID to delete: "));
        if (c == null) {
            System.out.println("Customer not found.");
            return;
        }
        customers.remove(c);
        System.out.println("Customer deleted.");
    }

    static void searchCustomers() {
        String term = prompt("Search by ID, name, or email: ").toLowerCase();
        boolean found = false;
        for (Customer c : customers) {
            String fullName = (c.firstName + " " + c.lastName).toLowerCase();
            if (c.customerId.toLowerCase().contains(term) || fullName.contains(term)
                    || c.email.toLowerCase().contains(term)) {
                System.out.println(c);
                found = true;
            }
        }
        if (!found) System.out.println("No matching customers.");
    }

    static void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers yet.");
            return;
        }
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    // robots

    static void robotMenu() {
        System.out.println("\nRobots");
        System.out.println("1. Add robot");
        System.out.println("2. Edit robot");
        System.out.println("3. Delete robot");
        System.out.println("4. Search robots");
        System.out.println("5. List all robots");
        String choice = prompt("Choose an option: ");

        switch (choice) {
            case "1": addRobot(); break;
            case "2": editRobot(); break;
            case "3": deleteRobot(); break;
            case "4": searchRobots(); break;
            case "5": listRobots(); break;
            default: System.out.println("Invalid option.");
        }
    }

    static Robot findRobot(String id) {
        for (Robot r : robots) {
            if (r.assetId.equalsIgnoreCase(id)) {
                return r;
            }
        }
        return null;
    }

    static void addRobot() {
        String id = prompt("Asset ID: ");
        if (findRobot(id) != null) {
            System.out.println("That ID already exists.");
            return;
        }
        String name = prompt("Name: ");
        String model = prompt("Model: ");
        String serial = prompt("Serial number: ");
        String function = prompt("Primary function (e.g. Childcare, Cooking, Security): ");
        double fee = promptDouble("Rental fee: ");
        String facilityId = prompt("Facility ID: ");

        robots.add(new Robot(id, name, model, serial, "Available", function, fee, facilityId));
        System.out.println("Robot added.");
    }

    static void editRobot() {
        Robot r = findRobot(prompt("Asset ID to edit: "));
        if (r == null) {
            System.out.println("Robot not found.");
            return;
        }
        System.out.println("Current: " + r);
        System.out.println("(leave blank to keep the current value)");

        String input = prompt("Name: ");
        if (!input.isEmpty()) r.name = input;
        input = prompt("Model: ");
        if (!input.isEmpty()) r.model = input;
        input = prompt("Serial number: ");
        if (!input.isEmpty()) r.serialNumber = input;
        input = prompt("Status (Available/Rented/Maintenance/Inactive): ");
        if (!input.isEmpty()) r.status = input;
        input = prompt("Primary function: ");
        if (!input.isEmpty()) r.primaryFunction = input;
        input = prompt("Rental fee: ");
        if (!input.isEmpty()) r.rentalFee = Double.parseDouble(input);
        input = prompt("Facility ID: ");
        if (!input.isEmpty()) r.facilityId = input;

        System.out.println("Robot updated: " + r);
    }

    static void deleteRobot() {
        Robot r = findRobot(prompt("Asset ID to delete: "));
        if (r == null) {
            System.out.println("Robot not found.");
            return;
        }
        robots.remove(r);
        System.out.println("Robot deleted.");
    }

    static void searchRobots() {
        String term = prompt("Search by ID, name, model, or function: ").toLowerCase();
        boolean found = false;
        for (Robot r : robots) {
            if (r.assetId.toLowerCase().contains(term) || r.name.toLowerCase().contains(term)
                    || r.model.toLowerCase().contains(term) || r.primaryFunction.toLowerCase().contains(term)) {
                System.out.println(r);
                found = true;
            }
        }
        if (!found) System.out.println("No matching robots.");
    }

    static void listRobots() {
        if (robots.isEmpty()) {
            System.out.println("No robots yet.");
            return;
        }
        for (Robot r : robots) {
            System.out.println(r);
        }
    }

    // rentals

    static void rentRobot() {
        System.out.println("\nRent a Robot");
        prompt("Customer ID: ");
        prompt("Robot asset ID: ");
        prompt("Checkout date (YYYY-MM-DD): ");
        prompt("Due date (YYYY-MM-DD): ");
        prompt("Promo code (blank if none): ");
        System.out.println("Robot rented.");
    }

    static void returnRobot() {
        System.out.println("\nReturn a Robot");
        prompt("Customer ID: ");
        prompt("Robot asset ID: ");
        prompt("Return date (YYYY-MM-DD): ");
        prompt("Damage fee (0 if none): ");
        System.out.println("Robot returned.");
    }

    static void scheduleDelivery() {
        System.out.println("\nSchedule Delivery");
        prompt("Customer ID: ");
        prompt("Robot asset ID: ");
        prompt("Driverless car asset ID: ");
        prompt("Delivery date (YYYY-MM-DD): ");
        System.out.println("Delivery scheduled.");
    }

    static void schedulePickup() {
        System.out.println("\nSchedule Pickup");
        prompt("Customer ID: ");
        prompt("Robot asset ID: ");
        prompt("Driverless car asset ID: ");
        prompt("Pickup date (YYYY-MM-DD): ");
        System.out.println("Pickup scheduled.");
    }

    // reports (not implemented until later checkpoint)

    static void reportsMenu() {
        System.out.println("\nReports");
        System.out.println("1. Robots by manufacturer before a year");
        System.out.println("2. Robots rented by a member");
        System.out.println("3. Driverless cars with 2 or less units at a facility");
        System.out.println("4. Members within 10 miles served by a car");
        System.out.println("5. Unique pickup cars for an address");
        System.out.println("6. Member with the most rentals");
        prompt("Choose a report: ");
        System.out.println("Reports aren't implemented yet, coming in a later checkpoint.");
    }
}
