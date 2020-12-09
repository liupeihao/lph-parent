package com.lph.common.util.convert;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import lombok.extern.slf4j.Slf4j;



@Slf4j
public class XmlConvertUtils {

    /**
     *
     * @param clazz
     *            类的字节码文件
     * @param xml
     *            传入的XML字符串
     * @return XML字符串转实体类
     */
    public static <T> T parseFromXml(Class<T> clazz, String xml) {
        XStream xStream = new XStream(new DomDriver());
        xStream.processAnnotations(clazz);
        T t = (T) xStream.fromXML(xml);
        return t;
    }

    /**
     *
     *
     * @param obj
     *            实体类
     * @return 实体类转XML字符串
     */
    public static String beanToXml(Object obj) {
        XStream xStream = new XStream(new DomDriver());
        // 扫描@XStream注解
        xStream.processAnnotations(obj.getClass());
        return xStream.toXML(obj).replaceAll("\\_+", "_");//正则过滤双下划线转为单下划线
    }



}
