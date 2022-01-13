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

public class PyWrapper {

    private static final String[] SUPPLEMENTARY_FILE_PATHS = {
        "/util/palindrome.py"
    };

    private static final String MAIN_FILE_PATH = "/input_script.py";

    private final ProcessBuilder processBuilder;
    private Process process;

    private final File tempDir;

    public PyWrapper() throws IOException, InterruptedException {
        tempDir = Files.createTempDirectory("python_scripts").toFile();

        // copy files
        for (String pathString : SUPPLEMENTARY_FILE_PATHS) {
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
    }

    public void excecute() throws IOException, InterruptedException {
        if (process == null) {
            process = processBuilder.start();
        }
        process.waitFor();
    }
}