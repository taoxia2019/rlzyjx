package com.tcrl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.base.result.PageTableRequest;
import com.tcrl.base.result.Results;
import com.tcrl.entity.Department;
import com.tcrl.entity.Deptemployee;
import com.tcrl.entity.PerformanceResult;
import com.tcrl.entity.Users;
import com.tcrl.service.DepartmentService;
import com.tcrl.service.PerformanceResultService;
import com.tcrl.service.UsersService;
import com.tcrl.utils.DateUtils;
import com.tcrl.utils.GetSecurityUsername;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author taoxia
 * @since 2019-10-17
 */
@Controller
@RequestMapping("/performance")
public class PerformanceResultController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PerformanceResultService performanceResultService;

    @Autowired
    private UsersService usersService;

    @ResponseBody
    @RequestMapping("editField")
    public Results getResultField(Integer id,String field,String fieldValue){
        System.out.println(id+"--"+field+"---"+fieldValue);
        return performanceResultService.saveResultfieldValue(id,field,fieldValue);

    }

    @RequestMapping("/performance-fill")
    public String getFillPage() {

        return "performance/performance-fill";
    }

   /* @RequestMapping("/detailedrule-fill")
    public String getFillPage2() {

        return "performance/detailedrule-fill";
    }*/

    @RequestMapping("/fill")
    @ResponseBody
    public Results<PerformanceResult> getPerformanceResultList(PageTableRequest page) {
        page.countOffset();
        return performanceResultService.getList(page.getOffset(), page.getLimit());
    }

    /*@RequestMapping("/performance-result")
    public String getResultPage(Model model) {
        model.addAttribute("departments",departmentService.list(new QueryWrapper<Department>().ne("id", 1)));
        System.out.println(departmentService.list().stream().count()+"---------");
        return "performance/performance-result";
    }*/

    @RequestMapping("/findByDeptResult")
    @ResponseBody
    public  Results findByDeptResult(String kaoheyuefen,String beikaohedanwei){
        System.out.println(kaoheyuefen+"-----"+beikaohedanwei);
        if(null==beikaohedanwei&&null==kaoheyuefen) {
            QueryWrapper<PerformanceResult> performanceResultQueryWrapper = new QueryWrapper<>();
            performanceResultQueryWrapper.eq("kaoheyuefen", DateUtils.getMonth());
            performanceResultQueryWrapper.eq("beikaohedanwei", "铸轧分厂");
            List<PerformanceResult> performanceResults = performanceResultService.list(performanceResultQueryWrapper);
            int count = performanceResultService.count(performanceResultQueryWrapper);
            return Results.success(count,performanceResults);
        }else if("".equals(kaoheyuefen)&&null!=beikaohedanwei){
            QueryWrapper<PerformanceResult> performanceResultQueryWrapper = new QueryWrapper<>();
            performanceResultQueryWrapper.eq("kaoheyuefen", DateUtils.getMonth());
            performanceResultQueryWrapper.eq("beikaohedanwei", beikaohedanwei);
            List<PerformanceResult> performanceResults = performanceResultService.list(performanceResultQueryWrapper);
            int count = performanceResultService.count(performanceResultQueryWrapper);
            return Results.success(count,performanceResults);

        } else {
            QueryWrapper<PerformanceResult> performanceResultQueryWrapper = new QueryWrapper<>();
            performanceResultQueryWrapper.eq("kaoheyuefen", kaoheyuefen);
            performanceResultQueryWrapper.eq("beikaohedanwei", beikaohedanwei);
            List<PerformanceResult> performanceResults = performanceResultService.list(performanceResultQueryWrapper);
            int count = performanceResultService.count(performanceResultQueryWrapper);
            return Results.success(count,performanceResults);
        }



    }

    //下载备份数据填报表
    @RequestMapping("/export")
    @PreAuthorize("hasAuthority('kpi:performance:fill')")
    public void exportAll(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        List<PerformanceResult> list=new ArrayList<>();
        String dept = usersService.getOne(new QueryWrapper<Users>()
                .eq("name", GetSecurityUsername.getSecurityUsername()))
                .getDept();

        // 加載數據庫數據
        if("admin".equals(GetSecurityUsername.getSecurityUsername())) {
            list = performanceResultService.list();
        }else {

            list = performanceResultService.list(new QueryWrapper<PerformanceResult>().eq("kaohedanwei",dept));
        }
        // 建立工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立sheet表格
        HSSFSheet sheet = wb.createSheet(DateUtils.getMonth());
        //文件名稱
        String fileName = dept+"("+DateUtils.getMonth()+")"+"组织绩效数据填报表";
        // 表格行標題
        HSSFRow row = sheet.createRow(0);
        String[] header = {"序号", "部门分厂", "项目", "标准", "周期", "单位", "目标值", "实际值", "考核结果","备注"};
        // 創建表格
        HSSFCell cell = null;
        // 遍歷行標題
        for (int i = 0; i < header.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(header[i]);
        }
        // 設置行數據的起始位置
        int rowCount = 1;
        // 遍歷list存取的對象
        for (PerformanceResult p : list) {
            row = sheet.createRow(rowCount);
            row.createCell(0).setCellValue(rowCount);
            row.createCell(1).setCellValue(p.getBeikaohedanwei());
            row.createCell(2).setCellValue(p.getKaohexiangmu());
            row.createCell(3).setCellValue(p.getBeizhu());
            row.createCell(4).setCellValue(p.getZhouqi());
            row.createCell(5).setCellValue(p.getDanwei());
            row.createCell(6).setCellValue(p.getMubiaozhi());
            row.createCell(7).setCellValue(p.getShijizhi());
            row.createCell(8).setCellValue(p.getKaohejieguo());
            row.createCell(9).setCellValue(p.getBeizhu());

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

