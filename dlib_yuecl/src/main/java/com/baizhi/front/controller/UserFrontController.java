package com.baizhi.front.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baizhi.common.entity.Result;
import com.baizhi.common.entity.User;
import com.baizhi.common.util.RandomStrUtil;
import com.baizhi.front.service.UserFrontService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

@Controller
@RequestMapping("/userf")
public class UserFrontController {

    @Resource
    private UserFrontService userFrontService;

    /**
     * @param user 前台用户登录输入的账号和密码
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public Result login(User user) {

        Result result = new Result();

        try {
            User userDB = userFrontService.userLogin(user);

            if (userDB != null) {
                if (!userDB.getUserSatus().equals("1")) {
                    throw new RuntimeException("你的账户已被冻结");

                } else {
                    result.setSuccess(true);
                    result.setMsg("登录成功");

                }


            }
        } catch (Exception e) {

            e.printStackTrace();

            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }

        return result;

    }


    /**
     * @param user  前台输入的注册信息
     * @param phone 前台用户输入手机号
     * @param code  前台输入的手机的验证码
     * @return
     */
    @RequestMapping("userRegister")
    @ResponseBody
    public Result userRegister(User user, String phone, String code) {


        System.out.println(user + " " + phone + " " + code);
        Result result = new Result();

        try {

            Jedis jedis = new Jedis("192.168.129.129", 6379);

            String s = jedis.get(phone);

            String message = "";

            if (s != null) {

                //判断用户输入的手机验证码和发送的验证码是否一致
                if (s.equals(code)) {

                    userFrontService.userRegister(user);

                    result.setSuccess(true);
                    result.setMsg("注册成功");

                }

            } else {

                throw new RuntimeException("您的验证码已超时");

            }


        } catch (Exception e) {
            e.printStackTrace();

            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }

        return result;
    }


    /**
     * @param phone 前台输入用户的手机号，点击发送短信
     * @return
     * @throws Exception
     */
    @RequestMapping("msgcode")
    @ResponseBody
    public String getMsgCode(String phone) throws Exception {

        Jedis jedis = new Jedis("192.168.129.129", 6379);

        System.out.println(phone);

        //设置超时时间-可自行调整

        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化ascClient需要的几个参数

        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）


        final String accessKeyId = "LTAIDuzxVelPBzJO";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "3j7uSQbIs6r8kkaqYWNwsFujgVBAio";//你的accessKeySecret，参考本文档步骤2

        //初始化ascClient,暂时不支持多region（请勿修改）

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象

        SendSmsRequest request = new SendSmsRequest();

        //使用post提交

        request.setMethod(MethodType.POST);

        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phone);

        //必填:短信签名-可在短信控制台中找到
        request.setSignName("岳成粒");

        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_109500172");

        //生成随机的字符串，并存入redis的缓存

        String validateCode = RandomStrUtil.getString(4);

        System.out.println(validateCode);

        jedis.set(phone, validateCode);

        //设置存活时间120秒
        jedis.setex(phone, 300, validateCode);

        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\"" + validateCode + "\"}");

        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //请求失败这里会抛ClientException异常

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
            System.out.println(sendSmsResponse.getCode());
        }


        return "OK";

    }


    /**
     * 用户修改密码的方法
     *
     * @param user 包含需要参数 用户的id和新的密码
     * @return
     */
    @RequestMapping("updatePassword")
    @ResponseBody
    public Result updatePassword(User user) {

        Result result = new Result();

        try {
            userFrontService.modifyPwd(user);
            result.setSuccess(true);
            result.setMsg("修改密码成功");

        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }

        return result;
    }
}
