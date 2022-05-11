import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        //dodawanie post-cod'ow
        Container container = new Container("00-331");
        container.addElement("10-300");
        container.addElement("20-300");
        container.addElement("30-600");
        container.addElement("40-500");
        container.addElement("50-560");
        container.addElement("60-800");
        container.addElement("70-800");
        container.addElement("80-800");

        //ponizej inny element niz w pliku (90-800)
        container.addElement("98-800");

        //przy dodawaniu duplikatu poleci wyjątek
        // container.addElement("80-800");

        container.printElements();
        System.out.println();

        //przy próbie dodania poleci wyjątek z powodu złego formatu kodu
        // container.addElement("710-800");

        StringContainerManager scm = new StringContainerManager();

        //usuwanie po indeksie
        scm.removeElement(3, container, 0);
        container.printElements();
        System.out.println();

        //usuwanie po post-code
        scm.removeElement("60-800", container);
        container.printElements();

        // tworzenie i dodawanie do pliku
        //container.writeToFile();

        //wczytywanie zawartosci pliku + porównywanie
        // - w przypadku różnic wyprintuje gdzie i co się nie zgadza - brak różnic = brak printowania
        //zapis do pliku (.writeToFile) musi byc zakomentowane
        container.compareQueueAndFile();
    }
}