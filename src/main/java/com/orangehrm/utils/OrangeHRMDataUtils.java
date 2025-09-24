package com.orangehrm.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrangeHRMDataUtils {
    public static void main(String[] args) {
        // 1. 用 List 存储 OrangeHRM 测试账号（每组账号用字符串拼接，格式：用户名,密码,预期结果）
        List<String> loginData = new ArrayList<>();
        loginData.add("Admin,admin123,登录成功");
        loginData.add("Admin,ddddddd,登录失败");
        loginData.add("invaliduser,dddddd,登录失败");
        // 遍历 List，打印测试数据
        System.out.println("====================");
        for (int i = 0; i < loginData.size(); i++) {
            String[] data = loginData.get(i).split(",");
            String username = data[0];
            String password = data[1];
            String expectResult = data[2];
            System.out.println("case" + (i + 1) + "username: " + username + "password: " + password + "expectResult: " + expectResult);
        }
        // 2. 用 Map 存储 OrangeHRM 登录页元素定位（键=元素名，值=定位方式:表达式）
        // 遍历 Map，打印元素定位
        System.out.println("====================");
        Map<String, String> loginPage = new HashMap<>();
        loginPage.put("username", "name:username");
        loginPage.put("password", "name:password");
        loginPage.put("login", "xpath: //button[@type=\"submit\"]");
        System.out.println("\n=== OrangeHRM 登录页元素定位 ===");
        for (Map.Entry<String, String> entry : loginPage.entrySet()) {
            String elementName = entry.getKey();
            String locator = entry.getValue();
            System.out.println("元素名：" + elementName + "，定位表达式：" + locator);
        }
    }

}
