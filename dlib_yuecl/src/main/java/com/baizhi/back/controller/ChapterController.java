package com.baizhi.back.controller;

import com.baizhi.back.service.BookService;
import com.baizhi.back.service.ChapterService;
import com.baizhi.common.entity.Chapter;
import com.baizhi.common.entity.PageShow;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("chap")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @Resource
    private BookService bookService;


    //上传章节
    @RequestMapping(value = "addChap", method = RequestMethod.POST)
    public void addChap(HttpServletRequest request, @RequestParam("file") MultipartFile file, Chapter chapter) throws Exception {

        //获取包含书籍名的文件夹的路径
        String filePath = request.getRealPath("back/static/file/books");

        //获取书名
        String bookName = bookService.queryOne(chapter.getBookId()).getName();

        //关于该书名的文件夹的路径
        String bookPath = filePath + "/" + bookName;

        //创建文件对象
        File myFile = new File(bookPath);

        //如果文件夹已经存在
        if (myFile.exists()) {

            //如果该文件是一个文件夹
            if (myFile.isDirectory()) {

                //获取文件名,拼接上章节名
                String fileName = chapter.getName() + file.getOriginalFilename();

                //上传的路径名
                String lastFileName = bookPath + "/" + fileName;

                String path = "/dlib/back/static/file/books/" + bookName + "/" + fileName;

                //存入上传的章节的文件url路径
                chapter.setUrl(path);

                file.transferTo(new File(bookPath, fileName));

                chapterService.saveChapter(chapter);

            } else {  //存在同名而非文件夹的文件


                bookPath = bookPath + "1";

                File file1 = new File(bookPath);

                file1.mkdir(); //创建文件夹

                //获取文件名,拼接上章节名
                String fileName = chapter.getName() + file.getOriginalFilename();

                //上传的路径名
                String lastFileName = bookPath + "/" + fileName;

                String path = "/dlib/back/static/file/books/" + bookName + "/" + fileName;

                //存入上传的章节的文件url路径
                chapter.setUrl(path);

                file.transferTo(new File(bookPath, fileName));
                chapterService.saveChapter(chapter);
            }

        } else {  //如果文件夹不存在

            File file2 = new File(bookPath);

            file2.mkdir();

            //获取文件名,拼接上章节名
            String fileName = chapter.getName() + file.getOriginalFilename();

            //上传的路径名
            String lastFileName = bookPath + "/" + fileName;

            String path = "/dlib/back/static/file/books/" + bookName + "/" + fileName;

            //存入上传的章节的文件url路径
            chapter.setUrl(path);

            file.transferTo(new File(bookPath, fileName));

            chapterService.saveChapter(chapter);

        }
    }


    @RequestMapping("allChapter")
    @ResponseBody
    public PageShow allChapter(String page, String rows) {

        if (page == null && rows == null) {

            page = "1";
            rows = "3";
        }

        Integer page1 = new Integer(page);
        Integer rows1 = new Integer(rows);

        List<Chapter> chaps = chapterService.queryAll(page1, rows1);

        Integer count = chapterService.queryCount();

        PageShow pageShow = new PageShow();
        pageShow.setTotal(count);
        pageShow.setRows(chaps);

        return pageShow;
    }


    @RequestMapping("delChap")
    @ResponseBody
    public String delChap(String id) {

        chapterService.removeChapter(id);

        return "删除成功";

    }


    @ResponseBody
    @RequestMapping("singleChap")
    public Chapter singleChap(String id) {

        return chapterService.queryOne(id);

    }

    @RequestMapping("updateChapter")
    @ResponseBody
    public String updateChapter(Chapter chapter) {

        System.out.println(chapter);
        chapterService.modifyChapter(chapter);

        return "修改成功";
    }


    @RequestMapping("getChapsByBookId")
    @ResponseBody
    public PageShow getChapsByBookId(String bookId, String page, String rows) {


        if (page == null && rows == null) {

            page = "1";
            rows = "3";
        }

        Integer pagee = new Integer(page);
        Integer rowss = new Integer(rows);

        List<Chapter> chaps = chapterService.modifyByBookId(bookId, pagee, rowss);

        Integer count = chapterService.queryCountByBookId();
        PageShow pageShow = new PageShow();
        pageShow.setTotal(count);
        pageShow.setRows(chaps);

        return pageShow;
    }

}
