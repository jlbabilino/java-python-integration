package jpyint;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.util.Scanner;

public class PythonManager {

    private static final String[] FILE_PATHS = {
    };

    private static final String MAIN_FILE_PATH = "/input_script.py";

    private final ProcessBuilder processBuilder;
    private final Process process;
    // private final BufferedReader reader;
    // private final BufferedWriter writer;

    private final File tempDir;

    public PythonManager() throws IOException, InterruptedException {
        tempDir = Files.createTempDirectory("python_scripts").toFile();

        // copy files
        for (String pathString : FILE_PATHS) {
            InputStream s = PythonManager.class.getResourceAsStream(pathString);
            File file = new File(tempDir, pathString);
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileOutputStream os = new FileOutputStream(file);
            s.transferTo(os); // copy file
            os.close();
        }
        InputStream s = PythonManager.class.getResourceAsStream(MAIN_FILE_PATH);
        File mainFile = new File(tempDir, MAIN_FILE_PATH);
        mainFile.getParentFile().mkdirs();
        mainFile.createNewFile();
        FileOutputStream os = new FileOutputStream(mainFile);
        s.transferTo(os); // copy file
        os.close();

        // start program
        processBuilder = new ProcessBuilder("python3", mainFile.getAbsolutePath());
        
        processBuilder.redirectOutput(Redirect.INHERIT);
        processBuilder.redirectInput(Redirect.INHERIT);

        process = processBuilder.start();
        System.out.println("Starting py: " + mainFile.getAbsolutePath());
        process.waitFor();
        System.out.println("Ending py");
        // scanner.close();


        // Scanner scanner = new Scanner(System.in);
        // while (scanner.hasNextLine()) {
        //     process.getOutputStream().write(scanner.nextLine().getBytes());
        // }
        // process.getOutputStream().close();
        // scanner.close();

        // reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        // writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
    }
}