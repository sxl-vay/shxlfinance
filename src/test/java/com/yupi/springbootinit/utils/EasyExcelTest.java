package com.yupi.springbootinit.utils;

import cn.hutool.core.date.ChineseDate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

/**
 * EasyExcel 测试
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@SpringBootTest
public class EasyExcelTest {

    @Test
    public void doImport() throws FileNotFoundException, Exception {
        SimpleDateFormat simpleDateFormat = TimeUtils.getSimpleDateFormat();

        ChineseDate chineseDate = new ChineseDate(simpleDateFormat.parse("1993-01-06 00:00:00"));
    System.out.println("chineseDate = " + chineseDate);

    }

}