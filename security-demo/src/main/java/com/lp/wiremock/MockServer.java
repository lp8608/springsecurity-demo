package com.lp.wiremock;/**
 * @Description: 类描述
 * @author LIPENGAK
 * @date 2018/5/5 0005 17:49
 */

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018/5/5 0005 17:49
 */
public class MockServer {

    public static void main(String[] args) throws  Exception{
        configureFor(9999);
        removeAllMappings();
        mock("/order/1","1");
        mock("/order/2","2");
    }

    private static void mock(String url,String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mockresponse/" + file + ".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(),"utf-8").toArray());
        stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
    }
}
