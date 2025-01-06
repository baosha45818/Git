package org.pocket.web.server;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface edit {

    public String readFile(String filename) throws IOException;

    public void writeFile(String filename, String content) throws IOException;

    public String createNewFile(String content) throws IOException;

}
