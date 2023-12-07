package pers.wmx.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

/**
 * @author wangmingxin03
 * Created on 2023-12-07
 */
public class GroovyShellHelper {
    private static final Logger logger = LoggerFactory.getLogger(GroovyShellHelper.class);

    private static final Map<String, Script> scriptMap = new HashMap<>();

    @Nullable
    public static Object evaluate(String scriptStr) {
        // 使用md5, 避免每次都编译groovy
        String scriptFp = DigestUtils.md5Hex(scriptStr);
        Script script = scriptMap.computeIfAbsent(scriptFp, fp -> new GroovyShell().parse(scriptStr));

        Binding binding = new Binding();
        script.setBinding(binding);
        return script.run();
    }

    public static void testGroovy(){
        GroovyShell groovyShell = new GroovyShell();
        groovyShell.evaluate("println 'hello Groovy shell.'");
    }

    public void testGroovy2() throws IOException {
        // 注意这里创建 groovy.lang.Binding
        Binding binding = new Binding();
        // 设置 args 参数到 Binding 中的 variable 成员中
        binding.setVariable("args", new String[]{"arg0", "arg1"});

        // 执行 Groovy 脚本
        GroovyShell shell = new GroovyShell(GroovyShellHelper.class.getClassLoader(), binding);
        File file = new File("src/main/java/pers/wmx/utils/test.groovy");
        shell.evaluate(file);
    }

    public static void main(String[] args) {
        testGroovy();
    }

}
