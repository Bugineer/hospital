package com.qf.common.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: json工具类
 * @Auther: yup
 * @Date: 2020/11/18 9:54
 */
public class JsonUtil {

    public static JsonConfig processorJson(JsonConfig jsonConfig) {
        jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
        jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor());
        jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor());
        return jsonConfig;
    }

    public static JsonConfig processorJson(JsonConfig jsonConfig, String format) {
        jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(format));
        jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor(format));
        jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(format));
        return jsonConfig;
    }

}

class DateJsonValueProcessor implements JsonValueProcessor{

    private String format = "yyyy-MM-dd";

    public DateJsonValueProcessor(){}

    public DateJsonValueProcessor(String format){
        this.format = format;
    }

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return null;
    }

    @Override
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if(o == null) {
            return "";
        }
        if(o.getClass() == Date.class || o.getClass() == java.sql.Date.class || o.getClass() == java.sql.Timestamp.class) {
            return sdf.format(o);
        }
        return o;
    }
}
