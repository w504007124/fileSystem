package com.wh.file.utils.fdfs;//package com.wh.lawliet.utils.fdfs;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Data
//@Deprecated
public class FastDFSConfig {

    @Value("${fastdfs.connect_timeout}")
    private Integer connectTimeout;

    @Value("${fastdfs.network_timeout}")
    private Integer networkTimeout;

    @Value("${fastdfs.charset}")
    private String charset;

    @Value("${fastdfs.tracker_server}")
    private String tracker_server;


//    @Value("${connect_timeout}")
//    private Integer connectTimeout;
//
//    @Value("${network_timeout}")
//    private Integer networkTimeout;
//
//    @Value("${charset}")
//    private String charset;
//
//    @Value("${tracker_server}")
//    private String tracker_server;

}
