package com.tclc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tcrl.App;
import com.tcrl.base.result.Results;
import com.tcrl.dao.PerformanceInitMapper;
import com.tcrl.entity.PerformanceInit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = App.class)
class RlzyjxApplicationTests {
	@Autowired
	private PerformanceInitMapper performanceInitMapper;

	@Test
	void contextLoads() {
		Page<PerformanceInit> page = new Page<>(1, 10);
		IPage<PerformanceInit> performanceInitIPage = performanceInitMapper.selectPage(page,null);
		Integer integer = performanceInitMapper.selectCount(null);
		System.out.println(integer);
		System.out.println(performanceInitIPage.getCurrent());
		System.out.println(performanceInitIPage.getPages());
		System.out.println(performanceInitIPage.getSize());
		System.out.println(performanceInitIPage.getTotal());
		System.out.println("----");

		performanceInitIPage.getRecords().forEach(y-> System.out.println(y.getKaohedanwei()));
	}

}
