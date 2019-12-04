package com.tcrl.service;

import com.tcrl.dao.ChanliangguagouMapper;
import com.tcrl.dao.DeptemployeeMapper;
import com.tcrl.dao.PerformanceInitMapper;
import com.tcrl.dao.PerformanceResultMapper;
import com.tcrl.entity.Deptemployee;
import com.tcrl.entity.Chanliangguagou;
import com.tcrl.entity.PerformanceInit;
import com.tcrl.entity.PerformanceResult;
import com.tcrl.utils.DateUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ScheduledService
 * @Description DOTO
 * @Author Administrator
 * @Date 2019/11/13 16:47
 * @Version 1.0
 */
@Service
public class ScheduledService {
    @Autowired
    private PerformanceResultMapper performanceResultMapper;
    @Autowired
    private PerformanceInitMapper performanceInitMapper;

    @Autowired
    private DeptemployeeMapper deptemployeeMapper;
    @Autowired
    private ChanliangguagouMapper chanliangguagouMapper;

    //定时备份方案
    @Scheduled(cron="0 0 18 * * ? ")   //@Scheduled(cron=" * * 0/1 * * ? ") 每小时一次
    public void back(){
        System.out.println("现在时间是"+new Date());
        Runtime runtime = Runtime.getRuntime();  //获取Runtime实例
        String user = "root";
        String password = "root";
        String database1 = "tcrljx"; // 需要备份的数据库名
        String table1 = "kpi_performance_result";
        String table2 = "kpi_performance_init";
        String table3 = "kpi_detailedrule_result";
        String table4 = "emp_deptemployee";
        String table5 = "emp_chanliangguagou";
        String table6 = "sys_department";
        String table7 = "sys_permission";
        String table8 = "sys_role";
        String table9 = "sys_role_permission";
        String table10 = "sys_user_role";
        String table11 = "sys_users";


        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String sdfDate = sdf.format(currentDate);
        String filepath = "d:\\time_" + sdfDate + ".sql"; // 备份的路径地址
        //执行命令
        String stmt = "mysqldump  -h localhost -u "+user+" -p"+password+" --databases "+database1+" --tables "+table1+" "+table2+" "+table3+" "+table4+" "+table5+" "+table6+" "+table7+" "+table8+" "+table9+" "+table10+" "+table11+" > "+filepath;

        System.out.println(stmt);
        try {
            String[] command = { "cmd", "/c", stmt};
            Process process = runtime.exec(command);
            InputStream input = process.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("本次备份结束。");
    }


    //初始化月度考核填报表
    @Scheduled(cron="0 0 0 1 * ?")
    public void test1(){
        //将所有数据存储到RESULT表
        List<PerformanceInit> performanceInits = performanceInitMapper.selectList(null);
        performanceInits.stream().forEach(perInit -> {
            PerformanceResult pResult = getPerformanceResult(perInit);
            performanceResultMapper.insert(pResult);
        });
    }

    //初始化月度产量挂钩表
    @Scheduled(cron="0 0 0 1 * ?")
    public void test2(){
        //将所有数据存储到RESULT表
        List<Deptemployee> deptemployees = deptemployeeMapper.selectList(null);
        deptemployees.stream().forEach(deptEmp -> {
            Chanliangguagou chanliangguagou = getChanliangguagou(deptEmp);
            chanliangguagouMapper.insert(chanliangguagou);
        });
    }

    //两个类对拷 PerformanceInit ->PerformanceResult
    private PerformanceResult getPerformanceResult(PerformanceInit perInit) {
        PerformanceResult pResult = new PerformanceResult();
        BeanUtils.copyProperties(perInit, pResult);
        pResult.setKaoheyuefen(DateUtils.getMonth());
        pResult.setKaohejieguo(0.0);
        pResult.setId(null);
        return pResult;
    }

    //两个类对拷 员工信息表与产量挂钩表
    private Chanliangguagou getChanliangguagou(Deptemployee deptEmp) {
        Chanliangguagou chanliangguagou = new Chanliangguagou();
        BeanUtils.copyProperties(deptEmp, chanliangguagou);
        chanliangguagou.setKaoheyuefen(DateUtils.getMonth());
        chanliangguagou.setId(null);
        return chanliangguagou;
    }

}
