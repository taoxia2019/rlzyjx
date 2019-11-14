package com.tclc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tcrl.App;
import com.tcrl.base.result.Results;
import com.tcrl.dao.DeptemployeeMapper;
import com.tcrl.dao.DetailedruleResultMapper;
import com.tcrl.dao.PerformanceInitMapper;
import com.tcrl.dao.PerformanceResultMapper;
import com.tcrl.dao.UsersMapper;
import com.tcrl.entity.Deptemployee;
import com.tcrl.entity.PerformanceInit;
import com.tcrl.entity.PerformanceResult;
import com.tcrl.utils.GetSecurityUsername;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest(classes = App.class)
class RlzyjxApplicationTests {
	@Autowired
	private PerformanceInitMapper performanceInitMapper;
	@Autowired
    private PerformanceResultMapper performanceResultMapper;
	@Autowired
	private DetailedruleResultMapper detailedruleResultMapper;


	@Autowired
	private UsersMapper usersMapper;

	@Autowired
	private DeptemployeeMapper deptemployeeMapper;



	@Test
	//输入status状态码，与ID一致
	void contextLoads() {
		System.out.println(detailedruleResultMapper.selectById(4).getKaohejine());

	}

	@Test

	void contextLoads1() {
		String str="08";
		String str1="/";
		Double d=null;
		//boolean number = NumberUtils.isNumber(str);
		boolean number = NumberUtils.isCreatable(str);
		boolean number1 = NumberUtils.isCreatable(str1);
		System.out.println(number+"++++++++++++"+str);
		System.out.println(number1+"----"+str1);

		/*double v = Double.parseDouble(str);
		double v1 = Double.parseDouble(str1);*/
		//System.out.println(v1);


	}

	@Test
	public void test32(){
		List<Deptemployee> list=new ArrayList<>();
		Integer count;
		Integer offset=0;
		Integer limit=10;
		IPage<Deptemployee> page=new Page<>(offset,limit);
		//String securityUsername = GetSecurityUsername.getSecurityUsername();
		String securityUsername = "wangjiu";
		// 加載數據庫數據
		if("admin".equals(securityUsername)) {

			Long aLong = deptemployeeMapper.countAllDeptemployee();
			System.out.println(aLong);
			deptemployeeMapper.getallDeptemployeeByPage(offset,limit).stream().forEach(deptemy-> System.out.println(deptemy.getXingming()));


		}else {
			String dept = usersMapper.getUser(securityUsername).getDept();
			Long aLong = deptemployeeMapper.countAllDeptemployeeByDept(dept);
			System.out.println(aLong);
			deptemployeeMapper.getallDeptemployeeByDeptByPage(dept,offset,limit).stream().forEach(deptemy-> System.out.println(deptemy.getXingming()));

		}
	}

}
