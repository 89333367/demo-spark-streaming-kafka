package sunyu.demo.test;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.setting.dialect.Props;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    static Log log = LogFactory.get();
    static Props props = new Props("application.properties");

    public static void main(String[] args) {
        FastAutoGenerator.create(props.getStr("spring.datasource.dynamic.datasource.mysql.url"), props.getStr("spring.datasource.dynamic.datasource.mysql.username"), props.getStr("spring.datasource.dynamic.datasource.mysql.password"))
                .globalConfig(builder -> builder
                        .author("SunYu")
                        .outputDir("src/main/java")
                        .commentDate("yyyy-MM-dd")
                        .disableOpenDir() // 禁止打开输出目录
                )
                .packageConfig(builder ->
                        builder.parent("sunyu.demo") // 设置父包名
                                .pathInfo(Collections.singletonMap(OutputFile.xml, "src/main/resources/mapper")) //设置xml映射文件输出位置
                )
                .strategyConfig(builder -> builder
                        .addInclude("tw_nrv_redundant")

                        .entityBuilder()
                        .enableFileOverride()
                        .enableTableFieldAnnotation() // 启用字段注解

                        .serviceBuilder()
                        //.enableFileOverride()
                        .formatServiceFileName("%sService")

                        .mapperBuilder()
                        //.enableFileOverride()

                        .controllerBuilder()
                        //.enableFileOverride()
                        .enableRestStyle() // 启用 REST 风格
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
