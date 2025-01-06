package org.pocket.web.server;

import org.springframework.beans.factory.annotation.Value;

import java.io.*;

public class editImpl implements edit{

    @Value("file.url")
    private  String fileDir;

    @Override
    public String readFile(String filename) throws IOException {
        File file = new File(fileDir, filename);


        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString();
    }

    @Override
    public void writeFile(String filename, String content) throws IOException {

    }

    @Override
    public String createNewFile(String content) throws IOException {
        return null;
    }
}
