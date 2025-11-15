import java.util.Scanner;

class Expenses {
    private String date;
    private String category;
    private String description;
    private double amount;

    public Expenses(String date, String category, String description, double amount){
        this.date = date;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    public String getDate() { return date; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
}

// Abstraction: interface for viewing expenses
interface ExpenseViewer {
    void view(Expenses[] list, int count);
}

// Polymorphism: concrete class implementing ExpenseViewer
class ConsoleExpenseViewer implements ExpenseViewer {
    @Override
    public void view(Expenses[] list, int count) {
        System.out.println("===========================");
        System.out.println("       VIEW EXPENSES       ");
        System.out.println("===========================");

        if (count == 0) {
            System.out.println("No expenses recorded yet.");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println("Expense #" + (i + 1));
            System.out.println("Date: " + list[i].getDate());
            System.out.println("Category: " + list[i].getCategory());
            System.out.println("Description: " + list[i].getDescription());
            System.out.println("Amount: " + list[i].getAmount());
            System.out.println("----------------------------");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Expenses[] expenseList = new Expenses[50];
        int count = 0;
        int choice;

        // Polymorphism: interface reference to concrete implementation
        ExpenseViewer expenseViewer = new ConsoleExpenseViewer();

        do {
            System.out.println("\n");
            System.out.println("=====================================");
            System.out.println("     PERSONAL EXPENSES TRACKER");
            System.out.println("=====================================");
            System.out.println("1. Add Expenses");
            System.out.println("2. View Expenses");
            System.out.println("3. Total Expenses");
            System.out.println("4. Filter By Category");
            System.out.println("5. Exit Program.");
            System.out.println("-------------------------------------");
            System.out.println("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        count = addexpenses(scanner, expenseList, count); 
                        break;
                    case 2:
                        // Use abstraction + polymorphism
                        expenseViewer.view(expenseList, count);
                        break;
                    case 3:
                        System.out.println("Total Expenses not implemented yet.");
                        break;
                    case 4:
                        System.out.println("Filter By Category not implemented yet.");
                        break;
                    case 5:
                        System.out.println("Exiting program.");
                        break;
                    default:
                        System.out.println("Invalid input. Choose 1-5 options only.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = 0;
            }
        } while (choice != 5);
        scanner.close();
    }

    public static int addexpenses(Scanner scanner, Expenses[] expenseList, int count){
        if (count>=expenseList.length){
            System.out.println("cannot add more.");
            return count;
        }

        System.out.println("===========================");
        System.out.println("       ADD EXPENSES        ");
        System.out.println("===========================");

        System.out.print("Date: ");
        String Date = scanner.nextLine();

        System.out.print("Category: ");
        String Category = scanner.nextLine();

        System.out.print("Description: ");
        String Description = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = 0;

        try{
            amount =Double.parseDouble(scanner.nextLine());
        }catch (NumberFormatException e){
            System.out.println("Invalid input. Please try again.");
            return count;
        }
        System.out.println("----------------------------");

        expenseList[count] = new Expenses(Date, Category, Description, amount);
        count++;
        System.out.println("Saved successfully.");

        return count;
    }
}
