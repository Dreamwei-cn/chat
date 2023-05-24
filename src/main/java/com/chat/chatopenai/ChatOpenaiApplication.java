package com.chat.chatopenai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mengwei
 * date: 2023年5月13日16:58:49
 *  调用openAi 工具类
 */
@SpringBootApplication
@MapperScan("com.chat.chatopenai.mapper*")
public class ChatOpenaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatOpenaiApplication.class, args);
	}

}
