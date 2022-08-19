package pres.krpc.example.customer.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pres.krpc.exampe.ExampeService;
import pres.krpc.exampe.dto.RequestDTO;
import pres.krpc.exampe.dto.ResponseDTO;
import pres.krpc.spring.annotation.KrpcResource;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * krpc
 * 2022/8/3 11:34
 *
 * @author wangsicheng
 * @since
 **/
@RestController
@Slf4j
public class TestController {

    @KrpcResource
    private ExampeService exampeService;


    /**
     * curl --location --request POST 'http://127.0.0.1:8081/test' --header 'Connection: keep-alive' --header 'Content-Type: application/json' --data-raw '{ "num" : 4 }'
     **/
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(@RequestBody RequestDTO requestDTO) {
        requestDTO.setDate(new Date());
        Date date = exampeService.doRun(requestDTO).getDate();
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return myFmt.format(date);
    }

}
