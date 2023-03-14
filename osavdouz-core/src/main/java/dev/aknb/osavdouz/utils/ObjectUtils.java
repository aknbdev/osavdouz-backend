package dev.aknb.osavdouz.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ObjectUtils {

    public static String convertToJson(Object object) throws JsonProcessingException {

        if (object == null) {
            return null;
        }
        return new ObjectMapper()
                .writeValueAsString(object);
    }
}
