package com.github.taccisum.freemarker.learn;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;

/**
 * @author tac - liaojf@cheegu.com
 * @since 2019/1/25
 */
public class HelloFreemarkerTest {
    @Test
    public void name() throws Exception {
        Configuration conf = new Configuration(Configuration.VERSION_2_3_28);
        conf.setDirectoryForTemplateLoading(new File(resource("ftl")));
        Template tpl = conf.getTemplate("entity.ftl");
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("table_name", "foo");
        dataMap.put("class_name", "FooEntity");
        dataMap.put("package", "ftl_out");
        tpl.process(dataMap, out(new File("src\\test\\java\\ftl_out\\FooEntity.java")));
    }

    private Writer out(File file) throws FileNotFoundException {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
    }

    private String resource(String path) {
        return this.getClass().getClassLoader().getResource(path).getPath();
    }
}
