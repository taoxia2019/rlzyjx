package com.tcrl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.base.result.PageTableRequest;
import com.tcrl.base.result.Results;
import com.tcrl.entity.Department;

import com.tcrl.entity.PerformanceInit;
import com.tcrl.service.DepartmentService;
import com.tcrl.service.PerformanceInitService;
import com.tcrl.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author taoxia
 * @since 2019-10-17
 */
@Controller
@RequestMapping("/performanceInit")
public class PerformanceInitController {

    @Autowired
    private PerformanceInitService performanceInitService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:init')")
    public Results<PerformanceInit> getPerformanceInit(PageTableRequest page){
        page.countOffset();
        return performanceInitService.getAllPerformanceInitByPage(page.getOffset(),page.getLimit());
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('kpi:performance:init')")
    public String addUser(Model model){
        model.addAttribute(new PerformanceInit());
        model.addAttribute("departments",departmentService.list(new QueryWrapper<Department>().ne("id", 1)));

        return "performance/performance-add";
    }

    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:init')")
    public Results<PerformanceInit> saveUser(PerformanceInit performanceInit){

        return performanceInitService.savePerformanceInits(performanceInit);
    }

    //跳转编辑页面
    @GetMapping("/edit")
    @PreAuthorize("hasAuthority('kpi:performance:init')")
    public String editUser(Model model,PerformanceInit performanceInit1){
        PerformanceInit p = performanceInitService.getById(performanceInit1.getId());
        //获取被考核部门start
        List<Department> depts1 = departmentService.
                list(new QueryWrapper<Department>().ne("id", 1));
        for (Department dept : depts1) {
            if(dept.getDeptName().equals(p.getBeikaohedanwei())){
                dept.setFlag(true);
                break;
            }
        }
        //获取部门end
        //获取考核部门start
        List<Department> depts2 = departmentService.
                list(new QueryWrapper<Department>().ne("id", 1));
        for (Department dept1 : depts2) {
            if(dept1.getDeptName().equals(p.getKaohedanwei())){
                dept1.setFlag(true);
                break;
            }
        }
        //获取部门end
        model.addAttribute("performanceInit",p);
        model.addAttribute("departments1",depts1);
        model.addAttribute("departments2",depts2);
        return "performance/performance-edit";
    }

    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:init')")
    public Results<PerformanceInit> updateUser(PerformanceInit performanceInit){

        return performanceInitService.updatePerformanceInit(performanceInit);
    }

    //删除
    @GetMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:init')")
    public Results deleteUser(PerformanceInit performanceInit){
        boolean b = performanceInitService.removeById(performanceInit.getId());
        if(b==true){
            return Results.success();
        }else {
            return Results.failure();
        }
    }

    //初始化表
    @PostMapping("/init")
    @PreAuthorize("hasAuthority('kpi:performance:init')")
    @ResponseBody
    public Results importExcel2(MultipartFile file, Model model) throws IOException {
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

        List<PerformanceInit> initList=new ArrayList<>();
        // 除掉標題行,進行遍歷
        for (int i = 1; i <= lastRowNum; i++) {
            if (0 == sheet.getRow(i).getCell(0).toString().length()) {
                break;
            }
            // 獲取pojo對象
            PerformanceInit pInit = new PerformanceInit();
            //ID
            pInit.setId(Long.parseLong(ExcelUtil.getCellValues(sheet.getRow(i).getCell(0))));
            //被考核部门
            pInit.setBeikaohedanwei(ExcelUtil.getCellValues(sheet.getRow(i).getCell(2)));
            //考核项目
            pInit.setKaohexiangmu(ExcelUtil.getCellValues(sheet.getRow(i).getCell(3)));
            //标准
            pInit.setBiaozhun(ExcelUtil.getCellValues(sheet.getRow(i).getCell(4)));
            //周期
            pInit.setZhouqi(ExcelUtil.getCellValues(sheet.getRow(i).getCell(5)));
            //单位
            pInit.setDanwei(ExcelUtil.getCellValues(sheet.getRow(i).getCell(6)));
            //目标值
            /*if(sheet.getRow(i).getCell(7)==null||ExcelUtil.getCellValues(sheet.getRow(i).getCell(7))==""){
                pInit.setMubiaozhi(0.0);
            }else {
                System.out.println(sheet.getRow(i).getCell(7));
                Double i1 = Double.parseDouble(ExcelUtil.getCellValues(sheet.getRow(i).getCell(7)));
                pInit.setMubiaozhi(i1);
            }*/

            //实际值
            //pInit.setShijizhi(0.0);
            // 绩效工资考核情况
            //pInit.setKaohejieguo(0.0);
            //操作符
            pInit.setCaozuofu(ExcelUtil.getCellValues(sheet.getRow(i).getCell(10)));
            //考核单位
            pInit.setKaohedanwei(ExcelUtil.getCellValues(sheet.getRow(i).getCell(11)));
            //备注
            //pInit.setBeizhu(ExcelUtil.getCellValues(sheet.getRow(i).getCell(12)));
            //状态码
            pInit.setStatus(Integer.parseInt(ExcelUtil.getCellValues(sheet.getRow(i).getCell(0))));
            performanceInitService.save(pInit);
        }
        return Results.success("上传成功");
    }

}

