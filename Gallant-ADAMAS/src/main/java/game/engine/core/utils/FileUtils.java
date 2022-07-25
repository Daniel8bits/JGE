package game.engine.core.utils;

import java.io.*;
import java.util.Objects;

public class FileUtils {

    public static String getFileAsString(String path){
        return getFileAsString(path, false);
    }
    public static String getFileAsString(String path, boolean external){

        StringBuilder file = new StringBuilder();

        try(
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(
                                external ?
                                        loadExternalFile(path) :
                                        loadFile(path)
                        )
                )
        ){

            String line;
            while( (line = bufferedReader.readLine()) != null )
                file.append(line).append("\n");

        }catch (IOException e) {
            System.err.println("[ ERROR ] On Loading Resource:     " + path);
        }

        return file.toString();
    }

    public static InputStream loadFile(String path) throws IOException {
        return Objects.requireNonNull(FileUtils.class.getResource(path)).openStream();
    }

    public static InputStream loadExternalFile(String path) throws FileNotFoundException {
        return new FileInputStream(path);
    }
}
