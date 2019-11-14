package com.tcrl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.base.result.PageTableRequest;
import com.tcrl.base.result.Results;
import com.tcrl.entity.Department;
import com.tcrl.entity.Deptemployee;
import com.tcrl.entity.Users;
import com.tcrl.service.DepartmentService;
import com.tcrl.service.DeptemployeeService;
import com.tcrl.service.UsersService;
import com.tcrl.utils.ExcelUtil;
import com.tcrl.utils.GetSecurityUsername;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author taoxia
 * @since 2019-11-12
 */
@Controller
@RequestMapping("/deptemployee")
public class DeptemployeeController {
    @Autowired
    private DeptemployeeService deptemployeeService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('emp:employee:query')")
    @ResponseBody
    public Results<Deptemployee> getPerformanceInit(PageTableRequest page){
        page.countOffset();
        return deptemployeeService.getAllDeptemployeeByPage(page.getOffset(),page.getLimit());

    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('emp:employee:add')")
    public String addUser(Model model){
        model.addAttribute("deptemployee",new Deptemployee());
        model.addAttribute("departments",departmentService.list(new QueryWrapper<Department>().notIn("id", 1,2,3,4)));

        return "employee/deptemployee-add";
    }

    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('emp:employee:add')")
    public Results<Deptemployee> saveUser(Deptemployee deptemployee) {
        boolean b = deptemployeeService.save(deptemployee);
        if(b=true){
            return Results.success();
        } else {
            return Results.failure();
        }
    }

    //跳转编辑页面
    @GetMapping("/edit")
    @PreAuthorize("hasAuthority('emp:employee:edit')")
    public String editUser(Model model,Deptemployee deptemployee){
        Deptemployee deptEmp = deptemployeeService.getById(deptemployee.getId());
        //获取部门start
        List<Department> depts1 = departmentService.
                list(new QueryWrapper<Department>().notIn("id", 1,2,3,4));
        for (Department dept : depts1) {
            if(dept.getDeptName().equals(deptEmp.getDept())){
                dept.setFlag(true);
                break;
            }
        }
        //获取部门end

        model.addAttribute("deptemployee",deptEmp);
        model.addAttribute("departments",depts1);
        return "employee/deptemployee-edit";
    }

    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('emp:employee:edit')")
    public Results<Deptemployee> update(Deptemployee deptemployee){


       return deptemployeeService.updateDeptemployee(deptemployee);

    }

    //删除
    @GetMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('emp:employee:del')")
    public Results deleteUser(Deptemployee deptemployee){
        boolean b = deptemployeeService.removeById(deptemployee.getId());
        if(b==true){
            return Results.success();
        }else {
            return Results.failure();
        }
    }

    //初始化表
    @PostMapping("/init")
    @PreAuthorize("hasAuthority('emp:employee:init')")
    @ResponseBody
    public Results importExcel2(MultipartFile file, Model model) throws IOException {
        if(deptemployeeService.list()!=null) {
            deptemployeeService.remove(new QueryWrapper<Deptemployee>().isNotNull("id"));
        }
        // 獲取流對象
        // MultipartFile 的filename必須和input的name屬性名稱一致
        InputStream is = file.getInputStream();
        // 導入流對象
        HSSFWorkbook wb = new HSSFWorkbook(is);
        // 獲取sheet表的名稱
        //HSSFSheet sheet = wb.getSheet("部门人员信息");
        HSSFSheet sheet = wb.getSheetAt(0);

        HSSFCell cell1 = sheet.getRow(0).getCell(0);

        // 獲取有數據的最後一行
        int lastRowNum = sheet.getLastRowNum();

        List<Deptemployee> deptEmpList=new ArrayList<>();
        // 除掉標題行,進行遍歷
        for (int i = 1; i <= lastRowNum; i++) {
            if (0 == sheet.getRow(i).getCell(0).toString().length()) {
                break;
            }
            // 獲取pojo對象
            Deptemployee deptEmp = new Deptemployee();
            //ID
            deptEmp.setId(Integer.parseInt(ExcelUtil.getCellValues(sheet.getRow(i).getCell(0))));
            //所在部门
            deptEmp.setDept(ExcelUtil.getCellValues(sheet.getRow(i).getCell(1)));
            //姓名
            deptEmp.setXingming(ExcelUtil.getCellValues(sheet.getRow(i).getCell(2)));
            //岗位
            deptEmp.setGangwei(ExcelUtil.getCellValues(sheet.getRow(i).getCell(3)));
            //挂钩单位
            deptEmp.setGuagoudanwei(ExcelUtil.getCellValues(sheet.getRow(i).getCell(4)));
            //岗序
            deptEmp.setGangxu(ExcelUtil.getCellValues(sheet.getRow(i).getCell(5)));
            //档次
            int i1 = Integer.parseInt(ExcelUtil.getCellValues(sheet.getRow(i).getCell(6)));

            deptEmp.setDangci(i1);
            //岗位工资
            deptEmp.setGangweigongzi(Double.parseDouble(ExcelUtil.getCellValues(sheet.getRow(i).getCell(7))));

            // 绩效工资
            deptEmp.setJixiaogongzi(Double.parseDouble(ExcelUtil.getCellValues(sheet.getRow(i).getCell(8))));

            //挂钩金额
            //deptEmp.setGuagoujine(Double.parseDouble(ExcelUtil.getCellValues(sheet.getRow(i).getCell(9))));

            deptemployeeService.save(deptEmp);
            //deptEmpList.add(deptEmp);
        }
        return Results.success("上传成功");
    }

    //下载备份员工信息表
    @RequestMapping("/exportall")
    @PreAuthorize("hasAuthority('emp:employee:query')")
    public void exportAll(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        List<Deptemployee> list=new ArrayList<>();

        // 加載數據庫數據
        if("admin".equals(GetSecurityUsername.getSecurityUsername())) {
             list = deptemployeeService.list();
        }else {
            String dept = usersService.getOne(new QueryWrapper<Users>()
                    .eq("name", GetSecurityUsername.getSecurityUsername()))
                    .getDept();
             list = deptemployeeService.list(new QueryWrapper<Deptemployee>().eq("dept",dept));
        }
        // 建立工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立sheet表格
        HSSFSheet sheet = wb.createSheet("部门员工基础信息");
        //文件名稱
        String fileName = "部门员工基础信息"+ LocalDate.now();
        // 表格行標題
        HSSFRow row = sheet.createRow(0);
        String[] header = {"ID","所在单位","姓名","岗位","挂钩单位","岗序","档次","岗位工资","绩效工资"};
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
        for (Deptemployee deptEmp : list) {
            row = sheet.createRow(rowCount);
            row.createCell(0).setCellValue(deptEmp.getId());
            row.createCell(1).setCellValue(deptEmp.getDept());
            row.createCell(2).setCellValue(deptEmp.getXingming());
            row.createCell(3).setCellValue(deptEmp.getGangwei());
            row.createCell(4).setCellValue(deptEmp.getGuagoudanwei());
            row.createCell(5).setCellValue(deptEmp.getGangxu());
            row.createCell(6).setCellValue(deptEmp.getDangci());
            row.createCell(7).setCellValue(deptEmp.getGangweigongzi());
            row.createCell(8).setCellValue(deptEmp.getJixiaogongzi());

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

