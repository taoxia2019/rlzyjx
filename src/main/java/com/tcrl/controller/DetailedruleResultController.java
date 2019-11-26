package com.tcrl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.xpath.internal.SourceTree;
import com.tcrl.base.result.Results;
import com.tcrl.entity.Department;
import com.tcrl.entity.DetailedruleResult;
import com.tcrl.entity.Users;
import com.tcrl.service.DepartmentService;
import com.tcrl.service.DetailedruleResultService;
import com.tcrl.service.UsersService;
import com.tcrl.utils.DateUtils;
import com.tcrl.utils.GetSecurityUsername;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author taoxia
 * @since 2019-10-23
 */
@Controller
@RequestMapping("/detailedruleResult")
public class DetailedruleResultController {

    @Autowired
    private DetailedruleResultService detailedruleResultService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private DepartmentService departmentService;



    @RequestMapping("/add")
    @ResponseBody
    //kpi:performance:query
    @PreAuthorize("hasAuthority('kpi:performance:add')")
    public Results addDetailedruleResult(DetailedruleResult drr){

        //获取目前登录用户

        String username = GetSecurityUsername.getSecurityUsername();
        Users username1 = usersService.getOne(new QueryWrapper<Users>().eq("username", username));
        //设置值

        drr.setKaohedanwei(username1.getDept());

        boolean save = detailedruleResultService.save(drr);
        if(save==true){
            return Results.success();
        }else {
            return Results.failure();
        }
    }

    @RequestMapping("/getResult")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:query')")
    public Results getDetailedruleResult(){
        //获取目前登录用户

        String  username = GetSecurityUsername.getSecurityUsername();
        Users username1 = usersService.getOne(new QueryWrapper<Users>().eq("username", username));

        QueryWrapper<DetailedruleResult> byKaohebumen = new QueryWrapper<DetailedruleResult>();
        byKaohebumen.eq("kaohedanwei", username1.getDept());
        byKaohebumen.eq("kaoheyuefen", DateUtils.getMonth());
        int count = detailedruleResultService.count(byKaohebumen);
        List<DetailedruleResult> results = detailedruleResultService.list(byKaohebumen);
        List<DetailedruleResult> resultsSortById = results.stream()
                .sorted(Comparator.comparing(DetailedruleResult::getId).reversed())
                .collect(Collectors.toList());
        if(results!=null){
            return Results.success(count,resultsSortById);
        }else {
            return Results.failure();
        }
    }

    //跳转编辑页面
    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('kpi:performance:edit')")
    public String editUser(Model model,DetailedruleResult detailedruleResult){

        DetailedruleResult byId = detailedruleResultService.getById(detailedruleResult.getId());
        //获取部门start
        List<Department> depts = departmentService.
                list(new QueryWrapper<Department>().ne("id", 1));
        for (Department dept : depts) {
            if(dept.getDeptName().equals(byId.getBeikaohedanwei())){
                dept.setFlag(true);
                break;
            }
        }
        //获取部门end
        model.addAttribute("detailedruleResult",byId);
        model.addAttribute("departments",depts);
        return "/performance/detailedruleResult-edit";
    }

    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:edit')")
    public Results updateUser(DetailedruleResult detailedruleResult){
        String beikaohedanweiId = detailedruleResult.getBeikaohedanwei();
        String deptName = departmentService.getById(Integer.parseInt(beikaohedanweiId)).getDeptName();
        detailedruleResult.setBeikaohedanwei(deptName);
        boolean b = detailedruleResultService.updateById(detailedruleResult);
        if(b==true){
            return Results.success();
        }else {
            return Results.failure();
        }
    }

    //删除
    @GetMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:del')")
    public Results deleteUser(DetailedruleResult detailedruleResult){
        boolean b = detailedruleResultService.removeById(detailedruleResult.getId());
        if(b==true){
            return Results.success();
        }else {
            return Results.failure();
        }
    }

    @RequestMapping("/findByDeptResult")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:query')")
    public  Results findByDeptResult(String kaoheyuefen,String beikaohedanwei){
        System.out.println("===========");
        System.out.println(kaoheyuefen+"-----"+beikaohedanwei);

        if(null==beikaohedanwei&&null==kaoheyuefen) {
            QueryWrapper<DetailedruleResult> performanceResultQueryWrapper = new QueryWrapper<>();
            performanceResultQueryWrapper.eq("kaoheyuefen", DateUtils.getMonth());
            performanceResultQueryWrapper.eq("beikaohedanwei", "铸轧分厂");
            List<DetailedruleResult> detailedruleResults = detailedruleResultService.list(performanceResultQueryWrapper);
            int count = detailedruleResultService.count(performanceResultQueryWrapper);
            return Results.success(count,detailedruleResults);
        }else if("".equals(kaoheyuefen)&&null!=beikaohedanwei){
            QueryWrapper<DetailedruleResult> performanceResultQueryWrapper = new QueryWrapper<>();
            performanceResultQueryWrapper.eq("kaoheyuefen", DateUtils.getMonth());
            performanceResultQueryWrapper.eq("beikaohedanwei", beikaohedanwei);
            List<DetailedruleResult> detailedruleResults = detailedruleResultService.list(performanceResultQueryWrapper);
            int count = detailedruleResultService.count(performanceResultQueryWrapper);
            return Results.success(count,detailedruleResults);

        } else {
            QueryWrapper<DetailedruleResult> performanceResultQueryWrapper = new QueryWrapper<>();
            performanceResultQueryWrapper.eq("kaoheyuefen", kaoheyuefen);
            performanceResultQueryWrapper.eq("beikaohedanwei", beikaohedanwei);
            List<DetailedruleResult> detailedruleResults = detailedruleResultService.list(performanceResultQueryWrapper);
            int count = detailedruleResultService.count(performanceResultQueryWrapper);
            return Results.success(count,detailedruleResults);
        }



    }

    //模板方式
    @RequestMapping("/export")
    @PreAuthorize("hasAuthority('kpi:performance:query')")
    public void exportAll(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        List<DetailedruleResult> list=new ArrayList<>();
        String dept = usersService.getOne(new QueryWrapper<Users>()
                .eq("username", GetSecurityUsername.getSecurityUsername()))
                .getDept();

        // 加載數據庫數據
        if("admin".equals(GetSecurityUsername.getSecurityUsername())) {
            list = detailedruleResultService.list();
        }else {

            list = detailedruleResultService.list(new QueryWrapper<DetailedruleResult>().eq("kaohedanwei",dept));
        }
        File file= ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+"static/excel/detailedruleResult_template.xls");
        InputStream in=new FileInputStream(file);
        // 建立工作簿
        HSSFWorkbook wb = new HSSFWorkbook(in);
        // 建立sheet表格
        HSSFSheet sheet = wb.getSheetAt(0);
        //文件名稱
        String fileName = dept+"其他专项考核填报表("+DateUtils.getMonth()+")";
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
            row.createCell(2).setCellValue(list.get(i).getXiangmumingcheng());
            row.getCell(2).setCellStyle(context_style);

            row.createCell(3).setCellValue(list.get(i).getKaoheshiyou());
            row.getCell(3).setCellStyle(context_style);
            row.createCell(4).setCellValue(list.get(i).getKaohejine());
            row.getCell(4).setCellStyle(context_style);
            row.createCell(5).setCellValue("");
            row.getCell(5).setCellStyle(context_style);

        }
        //实现表格尾部审核签名和时间
        rowCount=rowCount+list.size();
        HSSFRow row1 = sheet.createRow(rowCount);
        row1.setHeightInPoints((short)25);
        row1.createCell(0).setCellValue("主管领导：");
        row1.createCell(3).setCellValue("部门负责人：");
        row1.createCell(4).setCellValue("时间："+ LocalDate.now());

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

