import java.util.ArrayList;

public class LibrarySystem {

    // This ArrayList to store all the books in memory the library
    // But in a real-world scenario, this data would be stored in a database or
    // files like CSV or JSON
    // because it would be lost when the program is closed.
    static ArrayList<Book> books = new ArrayList<Book>();

    // The division character to separate in columns for better readability
    static String division = " | ";

    // The main method is the entry point of the program
    public static void main(String[] args) {

        // Populate the library with some books
        popularLibraryWithBooks();

        // Declare the option variable to store the user's choice
        int option;

        do {

            System.out.println("\nLibrary Menu:");
            System.out.println("1. Display Library Books with Status");
            System.out.println("2. Add Books");
            System.out.println("3. Borrow Books");
            System.out.println("4. Return Books");
            System.out.println("0. Exit");
            System.out.println("Type q or quit to exit the program anytime.\n");

            option = getInputAsInteger("Enter option: ");

            switch (option) {
                case 1:
                    // Display all the books in the library
                    displayLibraryBooks(true);
                    break;
                case 2:
                    // Add a new book to the library
                    addNewBook();
                    break;
                case 3:
                    // Borrow a book from the library
                    borrowBook();
                    break;
                case 4:
                    // Return a book to the library
                    returnBook();
                    break;

                default:
                    printFailureMessage("Invalid option. Please try again.");
                    break;
            }

            System.out.print("Press Enter to back to the menu.");
            System.console().readLine();

        } while (option != 0);
    }

    /*
     * This method populates the library with some books.
     */
    static void popularLibraryWithBooks() {

        // Add some books to the library
        addBook("Java Programming", "John Doe", 5);
        addBook("Learning Python", "Alan Brown", 4);
        addBook("C# Essentials", "Jane Smith", 3);
        addBook("Java Programming", "Jane Smith", 5);
        addBook("Design Patterns", "Lisa White", 2);
        addBook("Algorithms 101", "Mark Green", 3);
        addBook("Clean Code Basics", "Sam Wilson", 6);
        addBook("Head First Patterns", "Tom Davis", 4);

        // simulate borrowing 5 books randomly

        int count = 0;
        while (count < 5) {

            int rndIndex = (int) (Math.random() * books.size());
            Book book = books.get(rndIndex);

            // check if the book is available
            if (book.isAvailable()) {
                // borrow the book
                book.borrowBook();
                count++;

                // simulate borrowing all copies of a book
                if (count == 1) {
                    book.borrowBook(book.getQuantityAvailable());
                }
            }
        }
    }

    /*
     * This method displays all the books in the library.
     * 
     * @param isIncludeTotalSummary A flag to include the total summary of the
     * library
     */
    static void displayLibraryBooks(boolean isIncludeTotalSummary) {

        System.out.println("Library Books:");

        displayHeader();

        // print a new line for better readability
        System.out.println("");

        int totalBooks = 0;
        int totalBorrowed = 0;
        int totalAvailable = 0;

        for (Book book : books) {

            displayBook(book);

            totalBooks += book.getQuantity();
            totalBorrowed += book.getQuantityBorrowed();
            totalAvailable += book.getQuantityAvailable();
        }

        // print a new line for better readability
        System.out.println("");

        // Check if the total summary is included
        if (isIncludeTotalSummary) {

            // Summary of total books, borrowed, and available
            System.out.println("Total Titles: " + books.size());
            System.out.println("Total Books: " + totalBooks);
            System.out.println("Total Borrowed: " + totalBorrowed);
            System.out.println("Total Available: " + totalAvailable);
        }
    }

    /*
     * This method displays the header of the library books table.
     */
    static void displayHeader() {

        // concatenate the header
        String header = division +
                padRight("Code", 5) + division +
                padRight("Title", 30) + division +
                padRight("Author", 30) + division +
                padRight("Quantity", 10) + division +
                padRight("Available", 10) + division +
                padRight("Borrowed", 10) + division;

        // print the header
        System.out.println(header);
    }

    static void displayBook(Book book) {

        // get the code of the book
        String code = books.indexOf(book) + 1 + "";

        // concatenate the line
        String line = division +
                padRight(code, 5) + division +
                padRight(book.getTitle(), 30) + division +
                padRight(book.getAuthor(), 30) + division +
                padRight(String.valueOf(book.getQuantity()), 10) + division +
                padRight(String.valueOf(book.getQuantityAvailable()), 10) + division +
                padRight(String.valueOf(book.getQuantityBorrowed()), 10) + division;

        // Here for better readability, we use colors to indicate the status of the book
        // Green for available without borrowed copies
        // Yellow for borrowed copies
        // Red for fully borrowed copies

        if (book.isFullyBorrowed()) {
            printFailureMessage(line);
        } else if (book.isBorrowed()) {
            printWarningMessage(line);
        } else {
            printSuccessMessage(line);
        }
    }

    /*
     * This method adds a new book to the library.
     */
    static void addNewBook() {

        String title = getInput("Enter the title of the book: ");
        String author = getInput("Enter the author of the book: ");
        int quantity = getInputAsInteger("Enter the quantity of the book: ");

        addBook(title, author, quantity);
    }

    /*
     * This method adds a book to the library.
     */
    static void addBook(String title, String author, int quantity) {

        // As a best practice, each argument is validated before proceeding

        // Check if the title or author is null or empty
        if (title == null || title.isEmpty() || author == null || author.isEmpty()) {
            printWarningMessage("Title and author cannot be null or empty.");
            return;
        }

        // Check if the quantity is less than or equal to 0
        if (quantity <= 0) {
            printWarningMessage("Quantity invalid, must be greater than 0.");
            return;
        }

        // transverse the list of books
        for (Book book : books) {

            // check if the current book has the same title and author
            if (book.getTitle().equalsIgnoreCase(title) &&
                    book.getAuthor().equalsIgnoreCase(author)) {

                // add the quantity to the book and get the updated quantity
                int quantityUpdated = book.addQuantity(quantity);

                // print the warning message that the book already exists
                printWarningMessage(author + "'s " + title + " already exists in the library");

                // print the success message that the quantity has been updated
                printSuccessMessage(author + "'s " + title + " quantity has been updated to " + quantityUpdated);
                return;
            }
        }

        // create a new book object and add it to the list of books
        Book newBook = new Book(title, author, quantity);
        books.add(newBook);

        // print the success message that the book has been added
        printSuccessMessage(author + "'s " + title + " is newly added to the library");
    }

    /*
     * This method searches for a book in the library and borrows it.
     */
    static void borrowBook() {

        // Display all the books in the library
        System.out.println("Borrow Book:");
        System.out.println("");

        Book book = searchBook();
        borrowBook(book);
    }

    /*
     * This method borrows a book from the library.
     * 
     * @param book The book to borrow
     */
    static void borrowBook(Book book) {

        // Check if the book is null
        if (book == null) {
            printFailureMessage("Book was not found.");
            return;
        }

        // Check if the book is fully borrowed
        if (book.isFullyBorrowed()) {
            printFailureMessage(book.getTitle() + " is fully borrowed.");
            return;
        }

        // Check if the book is not available
        int quantityToBorrow = getInputAsInteger("Enter the quantity to borrow: ");
        while (quantityToBorrow <= 0 || quantityToBorrow > book.getQuantityAvailable()) {

            // Check if the quantity is less than or equal to 0
            if (quantityToBorrow <= 0) {
                printFailureMessage("Quantity invalid, must be greater than 0. Please try again.");
            } else if (quantityToBorrow > book.getQuantityAvailable()) {
                printFailureMessage(" Quantity invalid, must be less than or equal to " +
                        book.getQuantityAvailable() + ". Please try again.");
            }
            quantityToBorrow = getInputAsInteger("Enter the quantity to borrow: ");
        }

        book.borrowBook(quantityToBorrow);
        printSuccessMessage(
                "The book " + book.getTitleAndAuthor() + ", " + quantityToBorrow + " copies has been borrowed.");

    }

    /*
     * This method searches for a book in the library and returns it.
     */
    static void returnBook() {

        System.out.println("Return Book:");
        System.out.println("");

        Book book = searchBook();
        returnBook(book);
    }

    /*
     * This method returns a book to the library.
     * 
     * @param book The book to return
     */
    static void returnBook(Book book) {

        if (book == null) {
            printFailureMessage("Book was not found.");
            return;
        }

        if (!book.isBorrowed()) {
            printFailureMessage(book.getTitle() + " is not borrowed.");
            return;
        }

        int quantityToReturn = getInputAsInteger("Enter the quantity to return: ");
        // Keep prompting the user until a valid quantity is entered
        while (quantityToReturn <= 0 || quantityToReturn > book.getQuantityBorrowed()) {

            if (quantityToReturn <= 0) {
                printFailureMessage("Quantity invalid, must be greater than 0. Please try again.");
            } else if (quantityToReturn > book.getQuantityBorrowed()) {
                printFailureMessage("Quantity invalid, must be less than or equal to " +
                        book.getQuantityBorrowed() + ". Please try again.");
            }

            quantityToReturn = getInputAsInteger("Enter the quantity to return: ");
        }

        // Return the book
        book.returnBook(quantityToReturn);

        printSuccessMessage(
                "The book " + book.getTitleAndAuthor() + ", " + quantityToReturn + " copies has been returned.");

    }

    /*
     * This method searches for a book in the library.
     * 
     * @return The book found or null if not found
     */
    static Book searchBook() {

        displayLibraryBooks(false);
        // Get the code or title/author of the book to borrow
        String codeOrTitle = getInput("Enter the code, or title to search for the book: ");

        if (codeOrTitle == null || codeOrTitle.isEmpty()) {
            printFailureMessage("Code, title or author cannot be  empty.");
            return null;
        }

        // Check if the searchOrCode is a number
        Book book = tryFindBookByCode(codeOrTitle);

        if (book != null)
            return book;

        // Search for the book by title or author
        return searchBookByTitle(codeOrTitle);
    }

    /*
     * This method searches for a book in the library by title or author.
     */
    static Book searchBookByTitle(String title) {

        // Create a list to store the found books
        ArrayList<Book> foundBooks = new ArrayList<Book>();
        for (Book book : books) {

            if (book.getTitle().equalsIgnoreCase(title)) {
                foundBooks.add(book);
            }
        }

        if (foundBooks.isEmpty()) {
            printFailureMessage("No book found with the title " + title);
            return null;
        }

        if (foundBooks.size() == 1) {
            return foundBooks.get(0);
        }

        // Here, multiple books are found with the same title

        System.out.println("Multiple books found with the title " + title);

        // Display the books with the same title
        displayHeader();

        for (Book book : foundBooks) {
            displayBook(book);
        }

        String codeOrAuthor = getInput("Enter the code or author's name to choose the book: ");
        Book book = tryFindBookByCode(codeOrAuthor);

        if (book != null)
            return book;

        if (codeOrAuthor == null || codeOrAuthor.isEmpty()) {
            printFailureMessage("Code or author cannot be empty.");
            return null;
        }

        for (Book bookItem : books) {

            if (bookItem.getAuthor().equalsIgnoreCase(codeOrAuthor)) {
                return bookItem;
            }
        }

        // No book found with the author
        printFailureMessage("No book found with the author " + codeOrAuthor);

        return null;
    }

    /*
     * This method tries to find a book in the library by code.
     * 
     * @param code The code of the book to find
     */
    static Book tryFindBookByCode(String code) {

        // The index of the book is the code - 1
        int index = tryParseInt(code) - 1;
        if (index >= 0 && index < books.size()) {
            return books.get(index);
        }
        return null;
    }

    /*
     * This method tries to parse the input string to an integer.
     * 
     * @param value The string value to parse
     * 
     * @return The integer value of the input or -1 if the input is not a number.
     */
    static int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /*
     * This method pads the input string with spaces on the right side until the
     */
    static String padRight(String input, int length) {
        return padRight(input, length, ' ');
    }

    /*
     * This method pads the input string with the specified character on the right
     */
    static String padRight(String input, int length, char padChar) {

        StringBuilder sb = new StringBuilder(input);

        // Append padChar until the desired length is reached
        while (sb.length() < length) {
            sb.append(padChar);
        }

        return sb.toString();
    }

    /*
     * This method reads the input from the user and returns it as an integer.
     * 
     * @param message The message to display to the user.
     * 
     * @return The integer value of the input or -1 if the input is not a number.
     */
    static int getInputAsInteger(String message) {

        try {
            return Integer.parseInt(getInput(message));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /*
     * This method reads the input from the user and returns it as a string.
     * It also checks if the user wants to quit the program.
     * 
     * @param message The message to display to the user.
     * 
     * @return The string value of the input.
     */
    static String getInput(String message) {

        System.out.print(message);
        String input = System.console().readLine();

        if (input.equalsIgnoreCase("q") ||
                input.equalsIgnoreCase("quit")) {
            System.out.println("Thank you for using the Library System.");
            System.out.println("Exiting the program... Goodbye!");
            System.exit(0);
        }
        return input;
    }

    /*
     * This method prints the success message to the console.
     * 
     * @param message The message to be printed in green color
     */
    static void printSuccessMessage(String message) {
        System.out.println("\u001B[32m" + message + "\u001B[0m");
    }

    /*
     * This method prints the warning message to the console.
     * 
     * @param message The message to be printed in yellow color
     */
    static void printWarningMessage(String message) {
        System.out.println("\u001B[33m" + message + "\u001B[0m");
    }

    /*
     * This method prints the failure message to the console.
     * 
     * @param message The message to be printed in red color
     */
    static void printFailureMessage(String message) {
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }

}

/*
 * This class represents a book in the library with information about its title,
 * author, year of publication, and availability.
 */
class Book {

    // Fields representing the book's title, author, and quantities
    private String title;
    private String author;
    private int quantity;
    private int quantityBorrowed;

    /*
     * Constructor for creating a new Book object with specified title, author,
     * year of publication, and quantity.
     */
    public Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.quantityBorrowed = 0;
    }

    /*
     * Returns the title of the book.
     */
    public String getTitle() {
        return this.title;
    }

    /*
     * Returns the title and author of the book.
     */
    public String getTitleAndAuthor() {
        return this.title + " by " + this.author;
    }

    /*
     * Returns the author of the book.
     */
    public String getAuthor() {
        return this.author;
    }

    /*
     * Returns the total quantity of the book.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /*
     * Returns the quantity of the book that is currently borrowed.
     */

    public int getQuantityBorrowed() {
        return this.quantityBorrowed;
    }

    /*
     * Return the quantity of the book that is currently available for borrowing.
     */
    public int getQuantityAvailable() {
        return this.quantity - this.quantityBorrowed;
    }

    /*
     * Checks if the book is currently available for borrowing.
     * 
     * @return true if the book is available, false otherwise
     */
    public boolean isAvailable() {
        return this.quantity > this.quantityBorrowed;
    }

    /*
     * Checks if at least one copy of the book is currently borrowed.
     * 
     * @return true if any is borrowed, false otherwise
     */
    public boolean isBorrowed() {
        return this.quantityBorrowed > 0;
    }

    /*
     * Checks if all copies of the book are currently
     * borrowed.
     * 
     * @return true if the book is fully borrowed, false otherwise
     */
    public boolean isFullyBorrowed() {
        // This is business logic
        return this.quantityBorrowed == this.quantity;
    }

    /*
     * Adds the specified quantity to the book's total quantity.
     * 
     * @param quantity the quantity to add to the book's total
     * 
     * @return the updated total quantity of the book
     */
    public int addQuantity(int quantity) {
        this.quantity += quantity;
        return this.quantity;
    }

    /*
     * Attempts to borrow a book if copies are available.
     * 
     * @return true if the book is successfully borrowed, false otherwise
     */
    public boolean borrowBook() {
        if (this.quantityBorrowed < this.quantity) {
            this.quantityBorrowed++;
            return true;
        }
        return false;
    }

    /*
     * Attempts to borrow a specified quantity of the book if copies are available.
     * 
     * @param quantity the quantity of the book to borrow
     * 
     * @return true if the book is successfully borrowed, false otherwise
     */
    public boolean borrowBook(int quantity) {

        if (this.quantityBorrowed + quantity <= this.quantity) {
            this.quantityBorrowed += quantity;
            return true;
        }
        return false;
    }

    /*
     * Returns the book if it is successfully returned.
     * 
     * @param quantity the quantity of the book to return
     * 
     * @return true if the book is successfully returned, false otherwise
     */
    public boolean returnBook(int quantity) {
        if (this.quantityBorrowed >= quantity) {
            this.quantityBorrowed -= quantity;
            return true;
        }
        return false;
    }
}
