package com.tuling.mall.sentineldemo.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.tuling.common.utils.R;
import com.tuling.mall.sentineldemo.entity.UserEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Fox
 */
public interface UserService {

    UserEntity getById(Integer id);
}
