package org.pocket.web.server;

import org.springframework.web.bind.annotation.*;

/**
 * @author lyh
 * @version 1.0.0
 * @date 2025-01-06 上午9:53:37
 */
@RestController(value = "/about")
@RequestMapping("/about")
public class Rest {
    //123123

    @GetMapping("/info")
    public String info(String infoDto) {
        if(infoDto == null || infoDto.isEmpty()){
            infoDto = "{\"info\":\"\"}";
        }
        return infoDto;
    }

}
