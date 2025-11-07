package com.cosmos.origin.web;

import com.cosmos.origin.common.domain.dos.UserDO;
import com.cosmos.origin.common.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class OriginWebApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void insertTest() {
        // 构建数据库实体类
        UserDO userDO = UserDO.builder()
                .username("mchshenu")
                .password("123456")
                .build();

        userMapper.insert(userDO);
    }

}
