package com.tcrl.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;


public class ExcelUtil {
	
	public static void main(String[] args) throws IOException {
	}

	
	/**
	 * 格式化单元格返回其内容 格式化成string返回。
	 * 
	 * @param cell
	 * @return
	 */
	public static String formatCell(HSSFCell cell) {
		if (cell == null) {
			return "";
		} else {
			if (cell.getCellType() == CellType.BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else {
				return String.valueOf(cell.getStringCellValue());
			}
		}
	}
	
	
	/**
	 * 
	 * 返回 日期  date  2018/3/28 14:18:00  这种类型的可以。
	 */
	public static Date formatDate(HSSFCell cell) throws ParseException{
		
		String str = cell.toString();
		
		Date date = null;
		if (cell.getCellType() == CellType.STRING) {
			date  = new SimpleDateFormat("yyyy-MM-dd").parse(str);
		} else if (cell.getCellType() == NUMERIC) {
			date = cell.getDateCellValue();
		}
		
		return date;
	}
	
	
	/**
	 * 返回 BigDecimal 数据
	 */
	public static BigDecimal formatBigDecimal(HSSFCell cell) throws ParseException{
		String str = ExcelUtil.formatCell(cell);
		BigDecimal num = new BigDecimal(str.trim());
		return num;
	}

	//检查表格中数据格式，转换为String类型
	public static String getCellValues(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// 判断数据的类型
		switch (cell.getCellType()) {
			case NUMERIC: // 数字
				//short s = cell.getCellStyle().getDataFormat();
				if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
					SimpleDateFormat sdf = null;
					// 验证short值
					if (cell.getCellStyle().getDataFormat() == 14) {
						sdf = new SimpleDateFormat("yyyy/MM/dd");
					} else if (cell.getCellStyle().getDataFormat() == 21) {
						sdf = new SimpleDateFormat("HH:mm:ss");
					} else if (cell.getCellStyle().getDataFormat() == 22) {
						sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					} else {
						throw new RuntimeException("日期格式错误!!!");
					}
					Date date = cell.getDateCellValue();
					cellValue = sdf.format(date);
				} else if (cell.getCellStyle().getDataFormat() == 0) {//处理数值格式
					cell.setCellType(CellType.STRING);
					cellValue = String.valueOf(cell.getRichStringCellValue().getString());
				}
				break;
			case STRING: // 字符串
				cellValue = String.valueOf(cell.getStringCellValue());
				break;
			case BOOLEAN: // Boolean
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case FORMULA: // 公式
				cellValue = String.valueOf(cell.getCellFormula());
				break;
			case BLANK: // 空值
				cellValue = null;
				break;
			case ERROR: // 故障
				cellValue = "非法字符";
				break;
			default:
				cellValue = "未知类型";
				break;
		}
		return cellValue;
	}



}


