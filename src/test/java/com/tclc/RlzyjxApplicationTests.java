package com.tclc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tcrl.App;
import com.tcrl.dao.ChanliangguagouMapper;
import com.tcrl.dao.DeptemployeeMapper;
import com.tcrl.dao.DetailedruleResultMapper;
import com.tcrl.dao.PerformanceInitMapper;
import com.tcrl.dao.PerformanceResultMapper;
import com.tcrl.dao.UsersMapper;
import com.tcrl.entity.Deptemployee;
import com.tcrl.entity.EmpChanliangguagou;
import com.tcrl.entity.PerformanceResult;
import com.tcrl.entity.Users;
import com.tcrl.utils.DateUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
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
	private ChanliangguagouMapper chanliangguagouMapper;

	@Autowired
	private DeptemployeeMapper deptemployeeMapper;


	@Test
	public void testConcat(){
		String otherStr="woaixizao";
		String newStr="tabuaixizao".concat(otherStr);

		System.out.println(newStr);
	}
	@Test
	void password(){
		String encode = new BCryptPasswordEncoder().encode("123456");
		System.out.println(encode);

	}


	@Test
		//初始化用户表
	void usersSet() {
		List<Users> users=new ArrayList<>();
		Users user=new Users();
		user.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user.setDept("人力资源部");
		user.setGangweimingcheng("绩效管理");
		user.setCreatetime(new Date());
		user.setUsername("admin");
		users.add(user);

		Users user1=new Users();
		user1.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user1.setDept("人力资源部");
		user1.setGangweimingcheng("人力资源部经理");
		user1.setCreatetime(new Date());
		user1.setUsername("熊垠");
		users.add(user1);

		Users user2=new Users();
		user2.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user2.setDept("人力资源部");
		user2.setGangweimingcheng("绩效管理");
		user2.setCreatetime(new Date());
		user2.setUsername("赵志平");
		users.add(user2);

		Users user3=new Users();
		user3.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user3.setDept("铸轧分厂");
		user3.setGangweimingcheng("工艺员");
		user3.setCreatetime(new Date());
		user3.setUsername("眭建华");
		users.add(user3);

		Users user4=new Users();
		user4.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user4.setDept("线材分厂");
		user4.setGangweimingcheng("党政干事");
		user4.setCreatetime(new Date());
		user4.setUsername("项建芳");
		users.add(user4);

		Users user5=new Users();
		user5.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user5.setDept("生产技术部");
		user5.setGangweimingcheng("统计管理");
		user5.setCreatetime(new Date());
		user5.setUsername("徐建蛟");
		users.add(user5);

		Users user6=new Users();
		user6.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user6.setDept("设备管理部");
		user6.setGangweimingcheng("设备管理");
		user6.setCreatetime(new Date());
		user6.setUsername("吕苗");
		users.add(user6);

		Users user7=new Users();
		user7.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user7.setDept("品质管理部");
		user7.setGangweimingcheng("品质管理部经理");
		user7.setCreatetime(new Date());
		user7.setUsername("眭勤");
		users.add(user7);

		Users user8=new Users();
		user8.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user8.setDept("物资供应部");
		user8.setGangweimingcheng("采购管理");
		user8.setCreatetime(new Date());
		user8.setUsername("王军平");
		users.add(user8);

		Users user9=new Users();
		user9.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user9.setDept("市场营销部");
		user9.setGangweimingcheng("市场营销部经理");
		user9.setCreatetime(new Date());
		user9.setUsername("周军");
		users.add(user9);

		Users user10=new Users();
		user10.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user10.setDept("总经理办");
		user10.setGangweimingcheng("总经理办主任");
		user10.setCreatetime(new Date());
		user10.setUsername("付伟平");
		users.add(user10);

		Users user11=new Users();
		user11.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user11.setDept("党群工作部");
		user11.setGangweimingcheng("组织干事");
		user11.setCreatetime(new Date());
		user11.setUsername("刘苗淼");
		users.add(user11);

		Users user12=new Users();
		user12.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user12.setDept("财务管理部");
		user12.setGangweimingcheng("财务管理部副经理");
		user12.setCreatetime(new Date());
		user12.setUsername("殷化峰");
		users.add(user12);

		Users user13=new Users();
		user13.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user13.setDept("风控内审部");
		user13.setGangweimingcheng("风控内审部经理");
		user13.setCreatetime(new Date());
		user13.setUsername("杨忠平");
		users.add(user13);

		users.forEach(u->{
			usersMapper.insert(u);
		});


	}

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

	@Test
	public void testSum(){
		List<EmpChanliangguagou> chanliangguagous = chanliangguagouMapper.
				selectList(new QueryWrapper<EmpChanliangguagou>().eq("kaoheyuefen", DateUtils.getMonth()));
		chanliangguagous.forEach(c->{
			c.setGuagoujine(1.0);
			chanliangguagouMapper.updateById(c);
		});

	}
	@Test
	public void testSum2(){
		String s="市场营销部";
		Double aDouble = chanliangguagouMapper.sumGuagoujineByKaoheyuefen(DateUtils.getMonth());
		System.out.println(aDouble);
	}

	@Test
	public void testSum3(){
		QueryWrapper<PerformanceResult> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("kaoheyuefen",DateUtils.getMonth());
		performanceResultMapper.selectList(queryWrapper).forEach(p->{
			if("分厂生产计划完成率".equals(p.getKaohexiangmu())){
				Double deptGuagoujine=chanliangguagouMapper.sumGuagoujineByDept(p.getBeikaohedanwei(),DateUtils.getMonth());
				System.out.println(p.getBeikaohedanwei()+"...."+deptGuagoujine);
                p.setKaohejieguo(deptGuagoujine);
                performanceResultMapper.updateById(p);
			}

		});
	}
	@Test
	public void mathTest(){
		performanceResultMapper.selectList(null).forEach(p->{
			if(p.getKaohejieguo()==null||"".equals(p.getKaohejieguo())) {
				p.setKaohejieguo(0.0);
				performanceResultMapper.updateById(p);
			}
		});
    }

}
