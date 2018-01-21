package com.baizhi.common.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PioUtils {


    /*
        将数据导出到excel表格的方法
     */
    public static void ExpoortToExcle(List data, Class clazz, String path) throws IllegalAccessException, IOException {

        //创建excle对象
        HSSFWorkbook wb = new HSSFWorkbook();

        //创建单元页对象
        HSSFSheet sheet = wb.createSheet();

        //创建行对象
        HSSFRow row = sheet.createRow(0);

        //获取对象的属性的集合
        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {

            //创建单元格对象
            HSSFCell cell = row.createCell(i);

            //创建第一行，并添加属性字段到单元格上面
            cell.setCellValue(fields[i].getName());
        }


        for (int i = 1; i <= data.size(); i++) {

            HSSFRow nextRow = sheet.createRow(i);

            //紧接着第一行的下面创建行
            Object object = data.get(i - 1);

            for (int j = 0; j < fields.length; j++) {

                //创建单元格
                HSSFCell nextCell = nextRow.createCell(j);

                //拿到单个的对象的所有的对象的属性的对象
                Field field = fields[j];

                //拆封装，设置为可访问
                field.setAccessible(true);

                if (field.getType() == Date.class) {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    String date = sdf.format(field.get(object));
                    System.out.println(date);

                    nextCell.setCellValue(date);
                } else {
                    //将单个的对象的单个属性的值分别的加入到单元格中
                    nextCell.setCellValue(field.get(object).toString());

                }

            }


        }


        //将该excle的表格输出到指定的文件位置
        wb.write(new File(path));

        //关流
        wb.close();

    }


    public static List importExcel(Class clasz, FileInputStream io) throws IllegalAccessException, IOException, InstantiationException, ParseException {

        //获得Excel表格对象
        HSSFWorkbook wb = new HSSFWorkbook(io);


        HSSFSheet sheet = wb.getSheetAt(0);

        ArrayList list = new ArrayList();

        Field[] fields = clasz.getDeclaredFields();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            // 吧row对象转换成User对象
            HSSFRow row = sheet.getRow(i);

            Object object = clasz.newInstance();

            for (int j = 0; j < fields.length; j++) {

                HSSFCell cell = row.getCell(j);


                Field field = fields[j];

                field.setAccessible(true);

                //判断对象属性类型 如果类型为Integer类型转换为Integer类型赋值
                //如果不是Integer类型转换为其他string类型赋值

                if (field.getType() == Integer.class) {

                    Double db = Double.parseDouble(cell.toString());

                    Integer in = db.intValue();
                    System.out.println(in.getClass());

                    field.set(object, in);

                } else if (field.getType() == Double.class) {

                    field.set(object, Double.parseDouble(cell.toString()));

                } else if (field.getType() == Date.class) {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


                    field.set(object, sdf.parse(cell.toString()));
                } else {

                    System.out.println("-------");
                    field.set(object, cell.toString());


                }


            }

            list.add(object);
        }


        return list;
    }


}
