package jpyint;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            PythonManager manager = new PythonManager();
        } catch (Exception e) {
            System.out.println("An error occured. Exiting.");
            System.out.println(e.getMessage());
        }
    }
}