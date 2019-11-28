package com.tcrl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.base.result.PageTableRequest;
import com.tcrl.entity.Chanliangguagou;

import com.tcrl.entity.Users;
import com.tcrl.service.ChanliangguagouService;
import com.tcrl.service.DepartmentService;
import com.tcrl.service.UsersService;
import com.tcrl.utils.DateUtils;
import com.tcrl.utils.GetSecurityUsername;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.tcrl.base.result.Results;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chanliangguagou")
@Slf4j
public class ChanliangguagouController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ChanliangguagouService chanliangguagouService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('emp:employee:query')")
    @ResponseBody
    public Results<Chanliangguagou> getPerformanceInit(){
        return chanliangguagouService.getAllDeptGuagoujineByPage();

    }

    //下载备份员工信息表
    @RequestMapping("/exportByDept")
    @PreAuthorize("hasAuthority('emp:employee:query')")
    public void exportAll(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        List<Chanliangguagou> list=new ArrayList<>();

        String dept = usersService.getOne(new QueryWrapper<Users>()
                .eq("name", GetSecurityUsername.getSecurityUsername()))
                .getDept();

        // 加載數據庫數據
        if("admin".equals(GetSecurityUsername.getSecurityUsername())) {
            list = chanliangguagouService.list();
        }else {

            list = chanliangguagouService.list(new QueryWrapper<Chanliangguagou>().eq("dept",dept));
        }
        // 建立工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立sheet表格
        HSSFSheet sheet = wb.createSheet("部门员工产量挂钩金额");
        //文件名稱
        String fileName = DateUtils.getMonth()+dept+"部门员工产量挂钩金额"+ LocalDate.now();
        // 表格行標題
        HSSFRow row = sheet.createRow(0);
        String[] header = {"ID","所在单位","姓名","岗位","挂钩单位","岗序","档次","岗位工资","绩效工资","挂钩金额"};
        // 創建表格
        HSSFCell cell = null;
        // 遍歷行標題
        for (int i = 0; i < header.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(header[i]);
        }
        ;
        // 設置行數據的起始位置
        int rowCount = 1;
        // 遍歷list存取的對象
        for (Chanliangguagou chanliangguagou : list) {
            row = sheet.createRow(rowCount);
            row.createCell(0).setCellValue(chanliangguagou.getId());
            row.createCell(1).setCellValue(chanliangguagou.getDept());
            row.createCell(2).setCellValue(chanliangguagou.getXingming());
            row.createCell(3).setCellValue(chanliangguagou.getGangwei());
            row.createCell(4).setCellValue(chanliangguagou.getGuagoudanwei());
            row.createCell(5).setCellValue(chanliangguagou.getGangxu());
            row.createCell(6).setCellValue(chanliangguagou.getDangci());
            row.createCell(7).setCellValue(chanliangguagou.getGangweigongzi());
            row.createCell(8).setCellValue(chanliangguagou.getJixiaogongzi());
            row.createCell(9).setCellValue(chanliangguagou.getGuagoujine());

            rowCount++;
        }

        OutputStream output = response.getOutputStream();
        response.reset();
        //解决火狐浏览器下载错误问题
        String s = request.getHeader("USER-AGENT").toLowerCase();
        if (s.indexOf("firefox") > 0) {
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + ".xls");
        } else {
            response.setHeader("Content-disposition", "attachment; filename="
                    + java.net.URLEncoder.encode(fileName, "utf-8") + ".xls");
        }
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
    }
}
