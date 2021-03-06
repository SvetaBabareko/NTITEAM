package org.babareko.NTITEAM;

import lombok.SneakyThrows;
import org.babareko.NTITEAM.web.json.JsonUtil;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class TestUtil {
   public static String getContent(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }

    @SneakyThrows
    public static <T> T readFromJson(ResultActions action, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValue(getContent(action.andReturn()), clazz);
    }

    @SneakyThrows
    public static <T> T readFromJsonMvcResult(MvcResult result, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValue(getContent(result), clazz);
    }

    @SneakyThrows
    public static <T> List<T> readListFromJsonMvcResult(MvcResult result, Class<T> clazz)  {
        return JsonUtil.readValues(getContent(result), clazz);
    }

}
