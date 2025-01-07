package org.pocket.web.server;

import java.io.IOException;
import java.sql.SQLException;

public interface Edit {

    public String readFile(String filename) throws IOException, SQLException;

    public boolean writeFile(String filename, String content) throws IOException, SQLException;

    public void createNewFile(String filename) throws IOException, SQLException;

}
