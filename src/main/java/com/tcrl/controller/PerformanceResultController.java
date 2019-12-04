package com.tcrl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.xpath.internal.SourceTree;
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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    @PreAuthorize("hasAuthority('kpi:performance:edit')")
    public Results getResultField(Integer id,String field,String fieldValue){

        return performanceResultService.saveResultfieldValue(id,field,fieldValue);

    }

    @RequestMapping("/performance-fill")
    @PreAuthorize("hasAuthority('kpi:performance:query')")
    public String getFillPage() {

        return "performance/performance-fill";
    }


    @RequestMapping("/fill")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:add')")
    public Results<PerformanceResult> getPerformanceResultList(PageTableRequest page) {
        page.countOffset();
        return performanceResultService.getList(page.getOffset(), page.getLimit());
    }


    @RequestMapping("/findByDeptResult")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:query')")
    public  Results findByDeptResult(String kaoheyuefen,String beikaohedanwei){
        System.out.println(kaoheyuefen+"-----"+beikaohedanwei);
        String dept = usersService.getOne(new QueryWrapper<Users>()
                .eq("username", GetSecurityUsername.getSecurityUsername()))
                .getDept();
        if("admin".equals(GetSecurityUsername.getSecurityUsername())) {
            if (null == beikaohedanwei && null == kaoheyuefen) {
                QueryWrapper<PerformanceResult> performanceResultQueryWrapper = new QueryWrapper<>();
                performanceResultQueryWrapper.eq("kaoheyuefen", DateUtils.getMonth());
                performanceResultQueryWrapper.eq("beikaohedanwei", "铸轧分厂");
                List<PerformanceResult> performanceResults = performanceResultService.list(performanceResultQueryWrapper);
                int count = performanceResultService.count(performanceResultQueryWrapper);
                return Results.success(count, performanceResults);
            } else if ("".equals(kaoheyuefen) && null != beikaohedanwei) {
                QueryWrapper<PerformanceResult> performanceResultQueryWrapper = new QueryWrapper<>();
                performanceResultQueryWrapper.eq("kaoheyuefen", DateUtils.getMonth());
                performanceResultQueryWrapper.eq("beikaohedanwei", beikaohedanwei);
                List<PerformanceResult> performanceResults = performanceResultService.list(performanceResultQueryWrapper);
                int count = performanceResultService.count(performanceResultQueryWrapper);
                return Results.success(count, performanceResults);

            } else {
                QueryWrapper<PerformanceResult> performanceResultQueryWrapper = new QueryWrapper<>();
                performanceResultQueryWrapper.eq("kaoheyuefen", kaoheyuefen);
                performanceResultQueryWrapper.eq("beikaohedanwei", beikaohedanwei);
                List<PerformanceResult> performanceResults = performanceResultService.list(performanceResultQueryWrapper);
                int count = performanceResultService.count(performanceResultQueryWrapper);
                return Results.success(count, performanceResults);
            }
        }else {
            if (null == beikaohedanwei ||"".equals(kaoheyuefen)) {
                QueryWrapper<PerformanceResult> performanceResultQueryWrapper = new QueryWrapper<>();
                performanceResultQueryWrapper.eq("kaoheyuefen", DateUtils.getMonth());
                performanceResultQueryWrapper.eq("beikaohedanwei", dept);
                List<PerformanceResult> performanceResults = performanceResultService.list(performanceResultQueryWrapper);
                int count = performanceResultService.count(performanceResultQueryWrapper);
                return Results.success(count, performanceResults);
            } else {
                QueryWrapper<PerformanceResult> performanceResultQueryWrapper = new QueryWrapper<>();
                performanceResultQueryWrapper.eq("kaoheyuefen", kaoheyuefen);
                performanceResultQueryWrapper.eq("beikaohedanwei", dept);
                List<PerformanceResult> performanceResults = performanceResultService.list(performanceResultQueryWrapper);
                int count = performanceResultService.count(performanceResultQueryWrapper);
                return Results.success(count, performanceResults);
            }

        }
    }

    //下载备份数据填报表,无模板模式
    @RequestMapping("/exportMonthResult")
    @PreAuthorize("hasAuthority('kpi:performance:query')")
    public void exportMonthResult(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        List<PerformanceResult> list=new ArrayList<>();
        String dept = usersService.getOne(new QueryWrapper<Users>()
                .eq("username", GetSecurityUsername.getSecurityUsername()))
                .getDept();

        // 加載數據庫數據
        if("admin".equals(GetSecurityUsername.getSecurityUsername())) {
            list = performanceResultService.list(new QueryWrapper<PerformanceResult>().eq("kaoheyuefen",DateUtils.getMonth()));
        }else {

            list = performanceResultService.list(new QueryWrapper<PerformanceResult>().eq("beikaohedanwei",dept).eq("kaoheyuefen",DateUtils.getMonth()));
        }
        // 建立工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立sheet表格
        HSSFSheet sheet = wb.createSheet(DateUtils.getMonth());
        //文件名稱
        String fileName = dept+"组织绩效数据考核结果("+DateUtils.getMonth()+")";
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
            if(p.getMubiaozhi()==null) {
                row.createCell(6).setCellValue("");
            }else {
                row.createCell(6).setCellValue(p.getMubiaozhi());
            }
            if(p.getShijizhi()==null) {
                row.createCell(7).setCellValue("");
            }else {
                row.createCell(7).setCellValue(p.getShijizhi());
            }
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

   //模板方式
   @RequestMapping("/export")
   @PreAuthorize("hasAuthority('kpi:performance:query')")
   public void exportAll(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
       List<PerformanceResult> list=new ArrayList<>();
       String dept = usersService.getOne(new QueryWrapper<Users>()
               .eq("username", GetSecurityUsername.getSecurityUsername()))
               .getDept();

       // 加載數據庫數據
       if("admin".equals(GetSecurityUsername.getSecurityUsername())) {
           list = performanceResultService.list(new QueryWrapper<PerformanceResult>().eq("kaoheyuefen",DateUtils.getMonth()));
       }else {

           list = performanceResultService.list(new QueryWrapper<PerformanceResult>().eq("kaohedanwei",dept).eq("kaoheyuefen",DateUtils.getMonth()));
       }
       File file= ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+"static/excel/performaceresult_template.xls");
       InputStream in=new FileInputStream(file);
       // 建立工作簿
       HSSFWorkbook wb = new HSSFWorkbook(in);
       // 建立sheet表格
       HSSFSheet sheet = wb.getSheetAt(0);
       //文件名稱
       String fileName = dept+"组织绩效数据填报表("+DateUtils.getMonth()+")";
       // 表格行標題
       HSSFRow row=sheet.getRow(0);

       row.getCell(0).setCellValue(fileName);

       // 設置行數據的起始位置
       int rowCount = 2;
       //设置样式
       HSSFFont font = wb.createFont();
       font.setFontHeightInPoints((short) 9);
       HSSFCellStyle context_style =wb.createCellStyle();
       context_style.setAlignment(HorizontalAlignment.CENTER);
       context_style.setBorderBottom(BorderStyle.THIN);
       context_style.setBorderLeft(BorderStyle.THIN);
       context_style.setBorderRight(BorderStyle.THIN);
       context_style.setBorderTop(BorderStyle.THIN);

       context_style.setFont(font);
       // 遍歷list存取的對象
       for (int i=0;i<10;i++) {
           row = sheet.createRow(rowCount+i);
            row.setHeightInPoints((short)25);
           row.createCell(0).setCellValue(i+1);
           row.getCell(0).setCellStyle(context_style);

           row.createCell(1).setCellValue(list.get(i).getBeikaohedanwei());
           row.getCell(1).setCellStyle(context_style);
           row.createCell(2).setCellValue(list.get(i).getKaohexiangmu());
           row.getCell(2).setCellStyle(context_style);

           row.createCell(3).setCellValue(list.get(i).getBiaozhun());
           row.getCell(3).setCellStyle(context_style);
           row.createCell(4).setCellValue(list.get(i).getZhouqi());
           row.getCell(4).setCellStyle(context_style);

           row.createCell(5).setCellValue(list.get(i).getDanwei());
           row.getCell(5).setCellStyle(context_style);
           if(list.get(i).getMubiaozhi()==null) {
               row.createCell(6).setCellValue("/");
           }else {
               row.createCell(6).setCellValue(list.get(i).getMubiaozhi());
           }
           row.getCell(6).setCellStyle(context_style);

           if(list.get(i).getShijizhi()==null) {
               row.createCell(7).setCellValue("/");
           }else {
               row.createCell(7).setCellValue(list.get(i).getShijizhi());
           }

           row.getCell(7).setCellStyle(context_style);
           row.createCell(8).setCellValue(list.get(i).getKaohejieguo());
           row.getCell(8).setCellStyle(context_style);
           row.createCell(9).setCellValue(list.get(i).getBeizhu());
           row.getCell(9).setCellStyle(context_style);

       }
       //实现表格尾部审核签名和时间
       rowCount=rowCount+list.size();
       HSSFRow row1 = sheet.createRow(rowCount);
       row1.setHeightInPoints((short)25);
       row1.createCell(0).setCellValue("主管领导：");
       row1.createCell(3).setCellValue("部门负责人：");
       row1.createCell(8).setCellValue("时间：");
       row1.createCell(9).setCellValue(""+LocalDate.now());

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

