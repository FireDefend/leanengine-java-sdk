package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.AVUtils;

import cn.leancloud.EngineFunctionParamInfo;
import cn.leancloud.ResponseUtil;

public class FunctionsTest {

  @Test
  public void testFunctionCallResponse() {
    AVUser user = new AVUser();
    JSONObject results = new JSONObject();
    results.put("result", user);
    JSONObject filteredResult = JSON.parseObject(
        ResponseUtil.filterResponse(AVUtils.restfulCloudData(results)), JSONObject.class);

    assertFalse(filteredResult.getJSONObject("result").containsKey("className"));
  }

  @Test
  public void testParamsParse() {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("fromPeer", "123");
    params.put("convId", "456");
    params.put("content", "shit from Mars");
    params.put("timestamp", 123123123l);
    params.put("offlinePeers", Arrays.asList("12", "123"));

    String content = JSON.toJSONString(params);
    EngineFunctionParamInfo info = new EngineFunctionParamInfo(Map.class, "object");
    Map<String, Object> parsedParams = (Map<String, Object>) info.parseParams(content);
    assertEquals("shit from Mars", parsedParams.get("content"));
    assertEquals("123", parsedParams.get("fromPeer"));
  }
}
