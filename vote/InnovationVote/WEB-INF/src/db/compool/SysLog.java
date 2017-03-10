/**
 * StatementManager.java
 * 
 * Copyright (c) 2015,北京邮电大学科技创新大本营
 * All rights reserved.
 * 
 * @author LiuHaiYang
 * @version 1.0
 * Date:2015-3-25
 */
package db.compool;

import java.io.IOException;

import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;


/**
 * 记录系统日志(使用log4j),缺省级别为info
 */
public class SysLog
{
    private static Logger logger = null;
    private static String pattern = null;
    private static String file = null;
    private static RollingFileAppender appender = null;
    private static Layout layout = null;

    //进行初始化
    static
    {
        pattern = "%-d %5p [%t] (%C(%F):%L) - %m%n";
        file = new PropertiesLoader().getLlHome() + "/logs/cms.log";
        layout = new PatternLayout(pattern);
        try
        {
            appender = new RollingFileAppender(layout, file);
            appender.setEncoding("GBK");
            appender.setMaxFileSize("100MB");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    
    public static Logger getLogger(Class objectClass)
    {
        logger = Logger.getLogger(objectClass);
        logger.addAppender(appender);
        return logger;
    }

    public static void write(Class objectClass, String msg, String level)
    {
        write(objectClass.getName(), msg, level);
    }

    public static void write(String className, String msg, String level)
    {
        logger = Logger.getLogger(className);
        logger.addAppender(appender);

        Level _level = Level.toLevel(level, Level.INFO);
        switch(_level.toInt())
        {
            case Level.DEBUG_INT:
                logger.debug(msg);
                break;
            case Level.INFO_INT:
                logger.info(msg);
                break;
            case Level.WARN_INT:
                logger.warn(msg);
                break;
            case Level.ERROR_INT:
                logger.error(msg);
                break;
            case Level.FATAL_INT:
                logger.fatal(msg);
                break;
        }
    }

    public static void write(Class objectClass, String msg)
    {
        write(objectClass, msg, "info");
    }

    public static void write(String className, String msg)
    {
        write(className, msg);
    }

}
