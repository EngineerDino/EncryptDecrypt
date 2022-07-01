package encryptdecrypt;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FilePrinter implements printer {
    String path;

    public FilePrinter(String path) {
        this.path = path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    @Override
    public void print(String data) {
        File file = new File(getPath());
        try (PrintWriter printWriter = new PrintWriter(file)) {

            printWriter.println(data);

        } catch (IOException e) {
            System.out.printf("Error! Cannot write or create %s", getPath());
        }
    }
}


class StandardPrinter implements printer {

    @Override
    public void print(String data) {
        System.out.println(data);
    }
}
