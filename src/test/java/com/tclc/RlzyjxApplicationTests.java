package com.tclc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tcrl.App;
import com.tcrl.base.result.Results;
import com.tcrl.dao.PerformanceInitMapper;
import com.tcrl.dao.PerformanceResultMapper;
import com.tcrl.entity.PerformanceInit;
import com.tcrl.entity.PerformanceResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest(classes = App.class)
class RlzyjxApplicationTests {
	@Autowired
	private PerformanceInitMapper performanceInitMapper;
	@Autowired
    private PerformanceResultMapper performanceResultMapper;


	@Test
	//输入status状态码，与ID一致
	void contextLoads() {

		performanceInitMapper.selectList(null).stream()
				.forEach(per->{
					per.setStatus(per.getId().intValue());
					performanceInitMapper.updateById(per);
				});



    }

}
