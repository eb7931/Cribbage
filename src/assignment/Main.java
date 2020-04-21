package assignment;

enum Phase{
	DRAW, PEGGING, SHOW;
}

public class Main {

    public static void main(String[] args) {
        new NamePrinter().printNames();
    }
}

class NamePrinter {
    /**
     * Prints the names of the group members separated by dashes.
     */
    public void printNames() {
        String separator = "-";

        String[] names = {
                "Dhruv Patel",
                "Sumanth Billanuka",
                "Ethan Britt"
        };

        System.out.println(String.join(separator, names));
    }
}
