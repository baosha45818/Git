package org.pocket.web.server;

import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author lyh
 * @version 1.0.0
 * @date 2025-01-06 上午9:53:37
 */
@RestController(value = "/about")
@RequestMapping("/about")
public class Rest {

    @GetMapping("/info")
    public String info(String infoDto) throws SQLException {

            if (infoDto == null || infoDto.isEmpty()) {
                infoDto = "{\"info\":\"\"}";
            }
            return infoDto;

    }
}