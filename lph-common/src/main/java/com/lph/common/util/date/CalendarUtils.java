package com.lph.common.util.date;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: CalendarUtils
 * @Description: 日期日历工具类
 * @Author: songxinyu
 * @Date: 2020/9/18 17:38
 **/
@Slf4j
public class CalendarUtils {

    // 法律规定的放假日期
    // todo 此处写死了。需要定时入库再读库，政府每年都会在12月份左右公布，无法提前预知
    // todo db数据转list,根据年份读取
    private final List<String> lawHolidays = Arrays.asList("2020-10-01", "2020-10-02", "2020-10-03", "2020-10-04",
        "2020-10-05", "2020-10-06", "2020-10-07", "2020-10-08");
    // 由于放假需要额外工作的周末
    // todo db数据转list,根据年份读取
    private final List<String> extraWorkdays = Arrays.asList("2020-09-27", "2020-10-10");

    /**
     * 判断是否是法定假日
     **/
    public boolean isLawHoliday(String calendar){
        CalendarUtils.isValidDate(calendar);
        if (lawHolidays.contains(calendar)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是周末
     **/
    public boolean isWeekends(String calendar){
        CalendarUtils.isValidDate(calendar);
        // 先将字符串类型的日期转换为Calendar类型
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.YYYY_MM_DD);
        Date date = null;
        try {
            date = sdf.parse(calendar);
        } catch (ParseException e) {
            log.error("日期转换异常，param：{}",calendar);
            return false;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || ca.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    /**
     * 判断是否是需要额外补班的周末
     **/
    public boolean isExtraWorkday(String calendar){
        CalendarUtils.isValidDate(calendar);
        if (extraWorkdays.contains(calendar)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是休息日（包含法定节假日和不需要补班的周末）
     **/
    public boolean isHoliday(String calendar){
        CalendarUtils.isValidDate(calendar);
        // 首先法定节假日必定是休息日
        if (this.isLawHoliday(calendar)) {
            return true;
        }
        // 排除法定节假日外的非周末必定是工作日
        if (!this.isWeekends(calendar)) {
            return false;
        }
        // 所有周末中只有非补班的才是休息日
        if (this.isExtraWorkday(calendar)) {
            return false;
        }
        return true;
    }

    /**
     * 校验字符串是否为指定的日期格式,含逻辑严格校验,2007/02/30返回false
     **/
    private static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy-MM-dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(DateUtils.YYYY_MM_DD);
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 校验传入日期是否为最近五个工作日
     * @param dateStr 日期字符串
     * @return boolean
     * @throws Exception
     */
    public boolean isWorkDay(String dateStr){
        SimpleDateFormat formatter = new SimpleDateFormat(DateUtils.YYYY_MM_DD);
        int count = 5;
        Date date = new Date();
        String calendar;
        Map<String, String> effectiveDate = new HashMap<>();
        while (count > 0) {
            calendar = formatter.format(date);
            effectiveDate.put(calendar, calendar);
            if (!this.isHoliday(calendar)) {
                count--;
            }
            date = addDate(date);
        }
        Set<String> keySet = effectiveDate.keySet();
        return keySet.contains(dateStr) && !isHoliday(dateStr);
    }

    /**
     * 日期加一天
     * @param date 日期
     * @return Date
     */
    private Date addDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1); // 把日期往后增加一天,整数 往后推,负数往前移动
        return calendar.getTime(); // 这个时间就是日期往后推一天的结果
    }

    public static void main(String[] args) throws Exception {
        String reserveDate = "2020-09-27";
        CalendarUtils cc = new CalendarUtils();
        // System.out.println("输入的calendar是否是指定要求的格式:" + CalendarUtils.isValidDate(calendar));
        // System.out.println("是否是法定节假日：" + cc.isLawHoliday(calendar));
        // System.out.println("是否是周末：" + cc.isWeekends(calendar));
        // System.out.println("是否是需要额外补班的周末：" + cc.isExtraWorkday(calendar));
        // System.out.println("是否是休息日：" + cc.isHoliday(calendar));
        System.out.println("是否为有效预约日期 = " + cc.isWorkDay(reserveDate));

    }
}