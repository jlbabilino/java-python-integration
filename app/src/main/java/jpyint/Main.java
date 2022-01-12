package jpyint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            return;
        }
        try {
            InputStream s = Main.class.getResourceAsStream("/script.py");
            File file = Files.createTempFile("script", ".py").toFile();
            FileOutputStream os = new FileOutputStream(file);
            s.transferTo(os);
            os.close();
            ProcessBuilder builder = new ProcessBuilder("python3", file.getAbsolutePath(), args[0]);
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}