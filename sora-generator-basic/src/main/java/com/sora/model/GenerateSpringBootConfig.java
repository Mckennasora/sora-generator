package com.sora.model;

import lombok.Data;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @description
 * @author sora
 */
@Data
public class GenerateSpringBootConfig {
    private String projectName = "springboot-template";
    private String projectNameUnderLine;
    private String projectNameBigCamel;
    private String destPath = "D:\\";
    private String author = "sora";
    private String groupId = "com.yyh";
    private boolean isKnife4jConfig;
    private boolean isCorsConfig;

    public void setProjectName(String projectName){
        this.projectName = projectName;
        String[] words = projectName.split("-");
        this.projectNameUnderLine =  projectName.replace("-","_");
        this.projectNameBigCamel = Arrays.stream(words).map(word -> {
            String firstLetter = word.substring(0, 1);
            String restLetter = word.substring(1);
            return firstLetter.toUpperCase() + restLetter;
        }).collect(Collectors.joining());
    }

//    public static void main(String[] args) {
//        GenerateSpringBootConfig generateSpringBootConfig = new GenerateSpringBootConfig();
//        System.out.println("springBootTemplateConfig = " + generateSpringBootConfig);
//        generateSpringBootConfig.setProjectName("spring-fuck-you");
//        System.out.println("springBootTemplateConfig = " + generateSpringBootConfig);
//    }
}
