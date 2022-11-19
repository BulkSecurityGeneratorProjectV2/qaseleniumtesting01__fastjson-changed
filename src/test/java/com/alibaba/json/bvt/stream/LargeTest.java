package com.alibaba.json.bvt.stream;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;

public class LargeTest extends TestCase {

    public void test_0() throws Exception {
        List<String> list = new ArrayList<String>(1000 * 1);
        for (int i = 0; i < 100 * 1; ++i) {
            list.add(Integer.toString(i));
        }
        File file = Files.createTempFile("fastjson-stream-large", "json").toFile();
        JSONWriter writer = new JSONWriter(new FileWriter(file));
        writer.startArray();
        writer.writeObject(list);
        writer.endArray();
        writer.close();

        System.out.println(FileUtils.readFileToString(file));

        JSONReader reader = new JSONReader(new FileReader(file));
        reader.startArray();
        reader.readObject();
        reader.endArray();
        reader.close();

    }
}
