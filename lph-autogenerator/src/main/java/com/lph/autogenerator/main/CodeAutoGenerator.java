package com.lph.autogenerator.main;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.sql.SQLException;

/**
 * 代码自动成圣
 */
public class CodeAutoGenerator  {

    static String projectPath = "D:\\workspace\\lph-parent\\lph-spike";
    static String databaseUrl="jdbc:mysql://47.104.225.53:10005/lph?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai";
    static String databaseUsername="root";
    static String databasePassword="abc123456";
    static String author="LPH";
    static String parentPackage="com.lph.code";
    //要生成的表
    static String[] tables = {"user"};//{"sys_user","sys_role"}


    public static void main(String[] args) {
        generatorCode();
    }

    public static void generatorCode(){
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true) // 是否支持AR模式
                .setAuthor(author) // 作者
                .setOutputDir(projectPath+"/src/main/java") // 生成路径
                .setFileOverride(true)  // 文件覆盖
                .setIdType(IdType.AUTO) // 主键策略
                .setOpen(false)//生成后打开文件夹
                .setServiceName("%sService")  // 设置生成的service接口的名字的首字母是否为I
                .setMapperName("%sMapper")
                .setEnableCache(false);// XML 二级缓存

        //2. 数据源配置
        DataSourceConfig  dsConfig  = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)  // 设置数据库类型
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl(databaseUrl)
                .setUsername(databaseUsername)
                .setPassword(databasePassword);

        //3. 策略配置globalConfiguration中
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) //全局大写命名
//                .setDbColumnUnderline(true)  // 指定表名 字段名是否使用下划线
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                .setInclude(tables) // 生成的表
                .setSuperMapperClass("com.baomidou.mybatisplus.core.com.lph.item.service.impl.mapper.BaseMapper");

        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent(parentPackage)
                .setMapper("com.lph.spike.dao")//com.lph.spike.dao
                .setService("com.lph.item.service.impl.service")//servcie
                .setController("com.lph.spike.controller")//com.lph.spike.controller
                .setEntity("com.lph.item.service.impl.entity")
                .setXml("com.lph.item.service.impl.mapper");//com.lph.item.service.impl.mapper.xml


        //5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templatesMybatis/controller.java.vm");
        tc.setService("/templatesMybatis/service.java.vm");
        tc.setServiceImpl("/templatesMybatis/serviceImpl.java.vm");
        tc.setEntity("/templatesMybatis/entity.java.vm");
        tc.setMapper("/templatesMybatis/dao.java.vm");
        tc.setXml("/templatesMybatis/mapper.xml.vm");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        ag.setTemplate(tc);
        //6. 执行
        ag.execute();
    }
}
