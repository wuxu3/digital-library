package com.baizhi.front.controller;

import com.baizhi.common.entity.Chapter;
import com.baizhi.front.service.ChapterFrontService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Controller
@RequestMapping("chapf")
public class ChapterFrontController {

    @Resource
    private ChapterFrontService chapterFrontService;

    @RequestMapping("chapsByBokId")
    @ResponseBody
    public List<Chapter> getAllChapsByBookId(String bookId) {

        List<Chapter> chaps = chapterFrontService.queryChapsByBookId(bookId);

        return chaps;

    }


    /**
     * 书籍所有章节的下载管理
     *
     * @param bookId   接收需要下载的书籍的id
     * @param request  请求参数
     * @param response 响应参数
     * @throws Exception
     */
    @RequestMapping("chapDownload")
    public void chapDownload(String bookId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Chapter> chaps = chapterFrontService.queryChapsByBookId(bookId);

        for (Chapter chap : chaps) {
            String url = chap.getUrl();

            //获取文件输入流
            FileInputStream fin = new FileInputStream(new File(url));

            String fileName = url.split("/", url.lastIndexOf("/"))[1];

            //获取文件的响应类型
            String fileType = request.getSession().getServletContext().getMimeType(fileName);

            response.setContentType(fileType);

            response.setHeader("content-disposition", "attachment;fileName=" + fileName);

            //获取输出响应流
            ServletOutputStream out = response.getOutputStream();

            //文件下载
            IOUtils.copy(fin, out);

            //关流
            IOUtils.closeQuietly(fin);
            IOUtils.closeQuietly(out);

        }

    }


}
