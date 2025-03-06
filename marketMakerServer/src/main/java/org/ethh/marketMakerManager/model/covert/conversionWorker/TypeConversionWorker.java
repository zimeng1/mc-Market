package org.ethh.marketMakerManager.model.covert.conversionWorker;

import com.alibaba.fastjson2.JSONObject;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author wangyifei
 */
@Component
@Named("TypeConversionWorker")
public class TypeConversionWorker {
    /**
     * 对象转json字符串
     *
     * @param obj
     * @return
     */
    @Named("toJsonString")
    public String toJsonString(Object obj) {
        if (Objects.isNull(obj)) {
            return null;
        }
        return JSONObject.toJSONString(obj);
    }

    @Named("toJsonObject")
    public JSONObject toJsonObject(String jsonStr) {
        if (Objects.isNull(jsonStr)) {
            return null;
        }
        return JSONObject.parseObject(jsonStr);
    }
}
