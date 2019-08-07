package com.bjs.java8.part12;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

/**
 * @Description 请设计一个 NextWorkingDay 类，该类实现了 TemporalAdjuster 接口，
 *              能够计算明天的日期，同时过滤掉周六和周日这些节假日。格式如下所示：
 *                  date = date.with(new NextWorkingDay());
 *              如果当天的星期介于周一至周五之间，日期向后移动一天；如果当天是周六或者周日，则返回下一个周一
 * @Author BianJiashuai
 */
public class NextWorkingDay implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int rollDay = 1;
        if (dayOfWeek == DayOfWeek.FRIDAY) {
            rollDay = 3;
        } else if (dayOfWeek == DayOfWeek.SATURDAY) {
            rollDay = 3;
        }
        return temporal.plus(rollDay, ChronoUnit.DAYS);
    }

}
