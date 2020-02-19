package assignment;

public class Main {

    public static void main(String[] args) {
        new NamePrinter().printNames();
    }
}

class NamePrinter {
    /**
     * Prints the names of the group members separated by spaces.
     */
    public void printNames() {
        String separator = ",";

        String[] names = {
                "Dhruv Patel",
                "Name 2",
                "Name 3"
        };

        System.out.println(String.join(separator, names));
    }
}
