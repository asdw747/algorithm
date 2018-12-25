package mars.finance;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class UpdateTool {

    public static void main(String [] args) {
        System.out.println("-------------------------begin-------------------------");
//        method1();

        File file = new File("/home/mi/Projects/mifi-ca/mifi-pt-admin/src/main/java/com/xiaomi/miui/admin/dsp/controllers");
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
              System.out.println(tempList[i].getPath());
//              if ("/home/mi/Projects/mifi-ca/mifi-pt-admin/src/main/java/com/xiaomi/miui/admin/market/controllers/MarketEventController.java".equals(tempList[i].getPath())) {
//
//              }
                method1(tempList[i].getPath());

            }
        }


    }

    public static void method1(String pathname) {
//        String pathname = "/home/mi/Projects/mifi-ca/mifi-pt-admin/src/main/java/com/xiaomi/miui/admin/market/controllers/CouponController.java";

        try {
            FileReader reader = new FileReader(pathname);
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

            StringBuilder sb = new StringBuilder();
            String line;
            //网友推荐更加简洁的写法
            boolean isBeginMethod = false;

            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                if (!StringUtils.isBlank(line)) {
                    if (line.contains("net.paoding.rose.web.")) {
                        continue;
                    }
                }

                if (line.contains("@Path")) {
                    String [] strings = line.split("\"");
                    sb.append("@Controller");
                    sb.append(System.getProperty("line.separator"));
                    sb.append("@RequestMapping(value = \"").append(strings[1]).append("\")");
                    sb.append(System.getProperty("line.separator"));

                    continue;
                }

                if (line.contains("@Get(")) {
                    String [] strings = line.split("\"");
                    sb.append("    @RequestMapping(value = \"").append(strings[1]).append("\", method = RequestMethod.GET)");
                    sb.append(System.getProperty("line.separator"));
                    isBeginMethod=true;
                    continue;
                }

                if (line.contains("@Post(")) {
                    String [] strings = line.split("\"");
                    sb.append("    @RequestMapping(value = \"").append(strings[1]).append("\", method = RequestMethod.POST)");
                    sb.append(System.getProperty("line.separator"));
                    isBeginMethod=true;
                    continue;
                }

                if (line.contains("@Put(")) {
                    String [] strings = line.split("\"");
                    sb.append("    @RequestMapping(value = \"").append(strings[1]).append("\", method = RequestMethod.PUT)");
                    sb.append(System.getProperty("line.separator"));
                    isBeginMethod=true;
                    continue;
                }

                if (line.contains("@Delete(")) {
                    String [] strings = line.split("\"");
                    sb.append("    @RequestMapping(value = \"").append(strings[1]).append("\", method = RequestMethod.DELETE)");
                    sb.append(System.getProperty("line.separator"));
                    isBeginMethod=true;
                    continue;
                }

                if (line.contains("@HttpFeatures(")) {
                    sb.append("    @ResponseBody");
                    sb.append(System.getProperty("line.separator"));
                    continue;
                }

                if ((line.contains("{") && line.contains("public ")) || line.contains("@Param(")) {
                    String lineA = line.replace("@Param", "@RequestParam");

                    String res = "";
                    if (lineA.contains("Invocation")) {
                        String line1 = lineA.replace("Invocation", "Model");
                        String line2 = line1.replace("inv", "model");
                        String line3 = line2.replace("invocation", "model");

                        String [] strings = line3.split("model");
                        res = strings[0] + "model" + ", HttpServletRequest request" + strings[1];
                    } else {
                        res = lineA;
                    }

                    if (isBeginMethod && res.contains("@RequestParam") && (res.contains("public String") || res.contains("public   String"))) {
                        sb.append(res);
                        sb.append(System.getProperty("line.separator"));


                        int paramStart = res.indexOf("@RequestParam");
                        String line1 = res.substring(paramStart);

                        String [] roughParams = line1.split(",");
                        for (String roughParam : roughParams) {
                            if (roughParam.contains("@RequestParam")) {
                                String [] s1 = roughParam.split("\"");
                                String key = s1[1];

                                String [] s2 = roughParam.split(" ");
                                String value = s2[s2.length - 1];

                                sb.append("        model.addAttribute(\"").append(key).append("\", ").append(value).append(");");
                                sb.append(System.getProperty("line.separator"));
                            }
                        }

                        continue;
                    } else {
                        sb.append(res);
                        sb.append(System.getProperty("line.separator"));

                        continue;
                    }
                }

                if (line.contains("inv.getRequest()")) {
                    String line1 = line.replace("inv.getRequest()", "request");
                    sb.append(line1);
                    sb.append(System.getProperty("line.separator"));

                    continue;
                }

                if (line.contains("inv.getModel(\"loginUser\")") || line.contains("getContextUser(inv)")) {
                    sb.append("        User loginUser = userService.getLoginUser(request);");
                    sb.append(System.getProperty("line.separator"));

                    continue;
                }

                if ((line.contains("inv.") || (line.contains("inv ") || line.contains("invocation") || line.contains("Invocation")) && !line.contains("getRequest()"))) {
                    String line1 = line.replace("inv", "model");
                    String line2 = line1.replace("Invocation", "Model");
                    String line3 = line2.replace("invocation", "model");
                    String line4 = line3.replace("addModel", "addAttribute");

                    sb.append(line4);
                    sb.append(System.getProperty("line.separator"));

                    continue;
                }

                if (isBeginMethod && (line.contains("return \"") || line.contains("return  \""))) {
                    sb.append("        model.addAttribute(\"contextPath\", request.getContextPath());");
                    sb.append(System.getProperty("line.separator"));
                    sb.append("        model.addAttribute(\"V\", new VmUtils());");
                    sb.append(System.getProperty("line.separator"));
                    sb.append(line);
                    sb.append(System.getProperty("line.separator"));

                    isBeginMethod = false;

                    continue;
                }

                //TODO 添加 V、Context
                sb.append(line);
                sb.append(System.getProperty("line.separator"));

                if (line.contains("package com.xiaomi.miui.admin")) {
                    sb.append(System.getProperty("line.separator"));
                    sb.append("import com.xiaomi.miui.admin.common.service.UserService;");
                    sb.append(System.getProperty("line.separator"));
                    sb.append("import com.xiaomi.miui.admin.common.util.VmUtils;");
                    sb.append(System.getProperty("line.separator"));
                    sb.append("import org.springframework.stereotype.Controller;");
                    sb.append(System.getProperty("line.separator"));
                    sb.append("import org.springframework.ui.Model;");
                    sb.append(System.getProperty("line.separator"));
                    sb.append("import org.springframework.web.bind.annotation.RequestMapping;");
                    sb.append(System.getProperty("line.separator"));
                    sb.append("import org.springframework.web.bind.annotation.RequestMethod;");
                    sb.append(System.getProperty("line.separator"));
                    sb.append("import org.springframework.web.bind.annotation.RequestParam;");
                    sb.append(System.getProperty("line.separator"));
                    sb.append("import org.springframework.web.bind.annotation.ResponseBody;");
                    sb.append(System.getProperty("line.separator"));
                    sb.append("import javax.servlet.http.HttpServletRequest;");
                    sb.append(System.getProperty("line.separator"));
                }
            }

            System.out.println(sb.toString());


            FileOutputStream fos = new FileOutputStream(pathname);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(sb.toString().toCharArray());
            pw.flush();
            pw.close();

            reader.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
