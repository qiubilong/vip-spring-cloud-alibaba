package com.tuling.mall.sentineldemo.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Fox
 */
@Component
@Slf4j
public class MyRequestOriginParser implements RequestOriginParser {
    /**
     * 通过request获取来源标识，交给授权规则进行匹配
     */
    @Override
    public String parseOrigin(HttpServletRequest request) {
        // 标识字段名称可以自定义   serviceName = order
        String cachedOrigin = request.getParameter("sourceApp");
       /* if (StringUtils.isBlank(cachedOrigin)) {
            log.info("parseOrigin cachedOrigin={}",cachedOrigin);
            throw new IllegalArgumentException("sourceApp参数为空");

        }*/
        //log.info("parseOrigin1 cachedOrigin={}",cachedOrigin);
        return cachedOrigin;
    }
}
