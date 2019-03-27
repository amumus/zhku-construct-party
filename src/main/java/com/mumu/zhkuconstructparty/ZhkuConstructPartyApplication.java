package com.mumu.zhkuconstructparty;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan( basePackages = {"com.mumu.zhkuconstructparty.biz.autoCode.mapper","com.mumu.zhkuconstructparty.biz.mapper"})
public class ZhkuConstructPartyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhkuConstructPartyApplication.class, args);
	}

}

