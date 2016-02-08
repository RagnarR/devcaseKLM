package com.klm.dev.exercises.devcase01.util;

import com.klm.dev.exercises.devcase01.config.ApplicationConfiguration;
import com.klm.dev.exercises.devcase01.utils.RemoteCallUtil;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {ApplicationConfiguration.class})
@WebAppConfiguration
public class RemoteCallUtilTest {

    @Test
    public void testBuildUriWithParams() {

        String baseServiceUri = "http://test/";

        Map<String, String> params = new HashMap<>();

        params.put("param1", "param1value");
        params.put("param2", "param2value");
        params.put("param3", "param2value");

        String destinationWithParams = RemoteCallUtil.buildUriWithParams(baseServiceUri, params);

        Assert.assertTrue(destinationWithParams.startsWith(baseServiceUri + "?"));
        Assert.assertNotEquals(new StringTokenizer(destinationWithParams, "=").countTokens(), 3);
        Assert.assertFalse(destinationWithParams.endsWith("&"));
    }

}
