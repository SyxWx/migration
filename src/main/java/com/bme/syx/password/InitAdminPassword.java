package com.bme.syx.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InitAdminPassword {


    @Autowired
    private InitAdminPasswordMapper initAdminPasswordMapper;
    private static final String SUFFIX = "`1qazx";
    private static final String Bme = "Bme@";



    //@PostConstruct
    private void queryList(){
        List<AdminPassword> passwordList = new ArrayList<AdminPassword>();
        passwordList = initAdminPasswordMapper.queryPassword();
        SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter1  = new SimpleDateFormat("MMdd");

        String  dString = formatter.format(new Date());
        String  dPassword = formatter1.format(new Date());



        for (AdminPassword ap:passwordList) {
            // 生成0-100的随机数
            int customerAdd = (int)(Math.random()*100);
            int dPasswordAdd = (int)(Math.random()*100);

            ap.toString();
            //if("pingangang_admin".equals(ap.getAccount())){
                //客户号增加随机数
                int customerId  = ap.getCustomerId() + customerAdd;
                //生成密码
                String password = Bme+dPassword+dPasswordAdd+"@"+customerId;
                //MD5加密生成密码
                String md5Password = md5(new StringBuilder(password).append(SUFFIX).toString());

                ap.setPasswordmw(password);
                ap.setPassword(md5Password);
                ap.setUpdate_time(dString);

                initAdminPasswordMapper.updatePassword(ap);
            //}
        }
    }

    /**
     *
     * update sys_user suc
     * 	join sys_user_password sup on
     *  sup.account = suc.account
     *  set suc.`password` =sup.`password`;
     *
     *
     */



    private static String md5(String str) {
        String password = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            password = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;
    }

}
