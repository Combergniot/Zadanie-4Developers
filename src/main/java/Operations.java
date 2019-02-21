import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Operations {

    protected int proxyPort = 8080;
    protected String proxyHost = "10.51.55.34";
    protected static final String ADDRESS = "https://raw.githubusercontent.com/SamouczekProgramisty/StrefaZadaniowaSamouka/master/test_input.txt";
    public List<String> cuboidParameters = new ArrayList<>();
    public List<Cuboid> cuboidList = new ArrayList<>();
    public List<Integer> packageSizeList = new ArrayList<>();

    public int calculateCuboidField(Cuboid cuboid) {
        return 2 * (cuboid.getA() * cuboid.getB()) + 2 * (cuboid.getB() * cuboid.getH()) + 2 * (cuboid.getA() * cuboid.getH());
    }

    public int getSizeTheSmallestWall(Cuboid cuboid) {
        int firstWall = cuboid.getA() * cuboid.getB();
        int secondWall = cuboid.getB() * cuboid.getH();
        int thirdWall = cuboid.getA() * cuboid.getH();

        if (firstWall <= secondWall && firstWall <= thirdWall) {
            return firstWall;
        }
        if (secondWall <= firstWall && secondWall <= thirdWall) {
            return secondWall;
        } else
            return thirdWall;
    }

    private URLConnection connectWith(String link) throws IOException {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        URLConnection connection = new URL(link).openConnection(proxy);
        return connection;
    }

    public List<String> collectData() throws IOException {
        Scanner scanner = new Scanner(connectWith(ADDRESS).getInputStream());
        while (scanner.hasNextLine()) {
            String inputLine = scanner.nextLine();
            cuboidParameters.add(inputLine);
        }
        scanner.close();
        return cuboidParameters;
    }

    public List<Cuboid> createCuboidList() throws IOException {
        collectData();
        for (int i = 0; i < cuboidParameters.size(); i++) {
            String[] parts = cuboidParameters.get(i).split("x");
            Cuboid cuboid = new Cuboid(0, 0, 0);
            cuboid.setA(Integer.parseInt(parts[0]));
            cuboid.setB(Integer.parseInt(parts[1]));
            cuboid.setH(Integer.parseInt(parts[2]));
            cuboidList.add(cuboid);
        }
        return cuboidList;
    }

    public List<Integer> createPackageSizeList() throws IOException {
        createCuboidList();
        for (int i = 0; i <cuboidList.size(); i++){
            int packageSize = getSizeTheSmallestWall(cuboidList.get(i)) +
            calculateCuboidField(cuboidList.get(i));
            packageSizeList.add(packageSize);
        }
//        System.out.println(packageSizeList);
        return packageSizeList;
    }

    public int getSumOfPackagesSize() throws IOException {
        int sum = createPackageSizeList()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        return sum;
    }





}
