package com.baizhi.common.scheduled;

import com.baizhi.back.service.UserService;
import com.baizhi.common.entity.User;
import com.baizhi.common.util.PioUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Component
public class MyScheduled {

    @Resource
    private UserService userService;

    //定时导出用户的信息
    @Scheduled(cron = "0 0/10 8-20 * * ?")
    public void exportInfo() throws Exception {

/*

        RequestAttributes attribs = RequestContextHolder.getRequestAttributes();
        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest request = ((ServletRequestAttributes) attribs).getRequest();
*/

        List<User> list = userService.queryUsers();

        //String realPath =  request.getSession().getServletContext().getRealPath("/back/static/file/userInfo");

        String fileName = UUID.randomUUID().toString() + ".xsl";

        String allPath = "D:\\MyIdeaProject\\dlib_yuecl\\src\\main\\webapp\\back\\static\\file\\userInfo/" + fileName;

        PioUtils.ExpoortToExcle(list, User.class, allPath);

        System.out.println("备份了一次 ");
        //}


    }
}
