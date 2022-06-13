package com.bme.syx.cloud.third;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * description:
 * <p></p>
 *
 * @author huanghao
 * @since 2021/2/27 14:01
 * Copyright: 2021, BME (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Slf4j
public class JacksonJsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    // 日起格式化
    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";
    static {
        // 设置属性命名策略,SNAKE_CASE-返回的Json驼峰式转下划线，Json body下划线传到后端自动转驼峰式
//        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        // 全局设置日期格式
        objectMapper.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));
        // 当地时区
        objectMapper.setLocale(Locale.CHINA);
        // 全局时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        //忽略空Bean转Json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));
        //忽略 在Json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

    }

    private JacksonJsonUtil() {
    }

    public static <T> String obj2Json(T entity) {
        String Json = null;

        try {
            Json = objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException var3) {
            var3.printStackTrace();
        }

        return Json;
    }

    public static <T> String obj2Str(T entity) {
        return obj2Json(entity);
    }

    public static <T> byte[] obj2JsonBytes(T entity) {
        try {
            return objectMapper.writeValueAsBytes(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> JsonNode obj2Node(T entity) {
        return objectMapper.valueToTree(entity);
    }

    public static <T> boolean write2JsonFile(String filepath, T entity) {
        File file = new File(filepath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException var4) {
                var4.printStackTrace();
                return false;
            }
        }

        return write2JsonFile(new File(filepath), entity);
    }

    public static <T> boolean write2JsonFile(File file, T entity) {
        try {
            objectMapper.writeValue(file, entity);
            return true;
        } catch (IOException var3) {
            log.error("File not exists");
            var3.printStackTrace();
            return false;
        }
    }

    public static <T> T json2Obj(String Json, Class<T> type) {
        try {
            return objectMapper.readValue(Json, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T str2Obj(String Json, Class<T> type) {
        return json2Obj(Json, type);
    }

    public static Map<String, Object> json2Map(String Json) {
        try {
            return (Map)objectMapper.readValue(Json, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> Map<String, T> json2Map(String Json, Class<T> type) {
        try {
            return objectMapper.readValue(Json, new TypeReference<Map<String, T>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T map2Obj(Map map, Class<T> type) {
        return objectMapper.convertValue(map, type);
    }

    public static <T> T parseJson(File Json, Class<T> type){
        try {
            return objectMapper.readValue(Json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T parseJson(URL url, Class<T> type) {
        try {
            return objectMapper.readValue(url, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> Json2List(String Json, Class<T> T) {
        CollectionType type = objectMapper.getTypeFactory().constructCollectionType(List.class, T);
        try {
            return objectMapper.readValue(Json, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> str2List(String Json, Class<T> T) {
        return Json2List(Json, T);
    }

    public static JsonNode Json2Node(String Json) {
        try {
            return objectMapper.readTree(Json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonNode str2Node(String Json) {
        return Json2Node(Json);
    }

    public static ObjectNode objectNode() {
        return JsonNodeFactory.instance.objectNode();
    }

    public static boolean isJsonString(String Json) {
        try {
            objectMapper.readTree(Json);
            return true;
        } catch (Exception var2) {
            if (log.isDebugEnabled()) {
                log.debug("check input string is Json format;Json : " + Json + " ; exception;" + var2.getMessage());
            }

            return false;
        }
    }

    public static ArrayNode arrayNode() {
        return JsonNodeFactory.instance.arrayNode();
    }



}
