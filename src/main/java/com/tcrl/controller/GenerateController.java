package com.tcrl.controller;

/*import com.sxbang.friday.dto.BeanField;
import com.sxbang.friday.dto.GenerateDetail;
import com.sxbang.friday.dto.GenerateInput;
import com.sxbang.friday.service.GenerateService;*/
import com.tcrl.base.result.Results;
import com.tcrl.dto.BeanField;
import com.tcrl.dto.GenerateDetail;
import com.tcrl.dto.GenerateInput;
import com.tcrl.service.GenerateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 代码生成接口
 *
 */
@Api(tags = "代码生成")
@RestController
@RequestMapping("/generate")
public class GenerateController {

	@Autowired
	private GenerateService generateService;

	@ApiOperation("根据表名显示表信息")
	@GetMapping(params = { "tableName" })
	public GenerateDetail generateByTableName(String tableName) {
		GenerateDetail detail = new GenerateDetail();
		detail.setBeanName(generateService.upperFirstChar(tableName));
		List<BeanField> fields = generateService.listBeanField(tableName);
		detail.setFields(fields);

		return detail;
	}

	@ApiOperation("生成代码")
	@PostMapping(value = "/save")
	@ResponseBody
	public Results save(@RequestBody GenerateInput input) {

		generateService.saveCode(input);
		return Results.success();
	}

}
