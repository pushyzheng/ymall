package site.pushy.ymall.common.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pushy
 * @since 2019/1/1 16:45
 */
public class RespUtil {

    private static String toJsonString(boolean success, String message, Object data) {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("message", message);
        jsonMap.put("success", success);
        jsonMap.put("result", data);
        return JSONObject.toJSONString(jsonMap, SerializerFeature.DisableCircularReferenceDetect);
    }

    public static String success(Object data) {
        return toJsonString(true, "", data);
    }

    public static String success() {
        return toJsonString(true, "", "请求成功");
    }

    public static String error(String message) {
        return toJsonString(false, message, null);
    }

}
