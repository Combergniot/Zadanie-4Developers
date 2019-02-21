import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Operations operations = new Operations();

        System.out.println("Potrzebuję "
                + operations.getSumOfPackagesSize() +
                " dm2 papieru do opakowania wszystkich paczek");

    }
}
