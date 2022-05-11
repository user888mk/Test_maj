import java.io.*;
import java.util.Scanner;

public class Container {

    private String postCode;
    private Container nextElement;
    private int index = 0;

    public Container(String postCode) {
        this.index = 0;
        this.postCode = postCode;
    }

    public Container(String postCode, int index) {
        this.postCode = postCode;
        nextElement = null;
        this.index = index;
    }

    public Container getNextElement() {
        return nextElement;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setNextElement(Container nextElement) {
        this.nextElement = nextElement;
    }

    public int getIndex() {
        return index;
    }

    public void addElement(String postCode) {
        boolean postCodeExiste = false;
        Container containerCopy = this;

        while (true) {
            if (postCode.equals(containerCopy.postCode)) {
                postCodeExiste = true;
            }
            if (containerCopy.nextElement != null) {
                containerCopy = containerCopy.nextElement;
            } else break;
        }
        if (!postCodeExiste) {
            if (postCode.matches("\\d{2}-\\d{3}")) {
                Container container = new Container(postCode, index + 1);
                if (nextElement == null) {
                    nextElement = container;
                } else {
                    nextElement.addElement(postCode);
                }
            } else {
                throw new InvalidStringContainerPatternException("BadValue");
            }
        }
        if (postCodeExiste) {
            throw new DuplicatedNotAllowed("Duplicatted not allowed");
        }
    }

    public void printElements() {

        if (nextElement != null) {
            System.out.println(postCode + "," + index);
            nextElement.printElements();
        } else System.out.println(postCode + "," + index);
    }

    public boolean compareQueueAndFile() {
        File file = new File("C:\\Users\\48724\\intelliJ_workspace\\Test_maj\\src\\main\\resources\\postalCodes.txt");
        Container container = this;
        boolean result = true;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                String[] split = line.split(" ");
                String postCode = split[0];
                int index = Integer.parseInt(split[1]);
                if (!postCode.equals(container.postCode) || index != container.index) {
                    result = false;
                    System.out.println("Brak zgodności danych");
                    System.out.println("file postcode " + postCode + "\t\t file index " + index);
                    System.out.println("container postcode " + container.postCode + "\t container index " + container.index);
                    break;
                }
                container = container.nextElement;
            }
            scanner.close();
            return result;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile() {
        try {
            Container container = this;
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\48724\\intelliJ_workspace\\Test_maj\\src\\main\\resources\\postalCodes.txt"));
            while (true) {
                bw.write(container.postCode + " " + container.index + "\n");
                container = container.nextElement;
                if (container == null) break;
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "postCode: " + postCode + " index: " + index;
    }
}