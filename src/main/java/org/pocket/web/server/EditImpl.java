package org.pocket.web.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.*;

@Component
public class EditImpl implements Edit {

    @Value("${file.url}")
    private String fileDir;

    @Value("${database.url}")
    private String databaseUrl;


    @Override
    public String readFile(String filename) throws IOException, SQLException {
        //实际读文件
        File file = new File(fileDir + File.separator + filename);
        if (!file.exists()) {
            throw new IOException("文件 " + filename + " 不存在，请先创建文件！");
        }
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
    public boolean writeFile(String filename, String content) throws IOException, SQLException {
        //实际写文件
        File file = new File(fileDir + File.separator + filename);
        if (!file.exists()) {
            throw new IOException("文件 " + filename + " 不存在，请先创建文件！");
        }
        try (FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8, true)) {
            writer.write(content);
            System.out.println("内容已成功写入文件 " + filename);
        } catch (IOException e) {
            System.err.println("写入文件时发生错误：" + e.getMessage());
            throw e;
        }

        //数据库写文件
        Connection conn = DriverManager.getConnection(databaseUrl);

        String checkSQL = "SELECT COUNT(*) FROM files WHERE file_name = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkSQL);
        checkStmt.setString(1, filename);
        ResultSet resultSet = checkStmt.executeQuery();
        if (resultSet.next() && resultSet.getInt(1) == 0) {
            System.out.println(filename+"文件不存在，需先创建文件");
            return false;
        } else {
            String writeSQL = "UPDATE files SET file_data = ? WHERE file_name = ?";
            PreparedStatement writeStmt = conn.prepareStatement(writeSQL);
            writeStmt.setString(1,content);
            writeStmt.setString(2,filename);
            writeStmt.executeUpdate();
            System.out.println(filename+"文件内容添加成功");
            return true;
        }

    }

    @Override
    public void createNewFile(String filename) throws IOException, SQLException {
        //实际创建文件
        File file = new File(fileDir+File.separator+filename);
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("电脑中已成功创建文件 " + filename);
        }else{
            System.out.println("文件 " + filename + " 已在电脑中存在");
        }

        //数据库中创建文件
        Connection conn = DriverManager.getConnection(databaseUrl);
        String checkSQL = "SELECT COUNT(*) FROM files WHERE file_name = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkSQL);
        checkStmt.setString(1, filename);
        ResultSet resultSet = checkStmt.executeQuery();
        if (resultSet.next() && resultSet.getInt(1) == 0) {
            String insertSQL = "INSERT INTO files (file_name) VALUES (?)";
            PreparedStatement prestmt = conn.prepareStatement(insertSQL);
            prestmt.setString(1, filename);
            prestmt.executeUpdate();
            System.out.println("数据库中成功插入文件 " + filename);
        } else {
            System.out.println("文件 " + filename + " 已在数据库中存在");
        }

    }
}
