package com.baizhi.common.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * 操作索引库工具类
 */
public class LuceneUtil {

    private static Directory directory;
    private static IndexWriterConfig config;
    private static Analyzer analyzer;
    private static Version version;

    static {
        try {
            version = Version.LUCENE_44;
            directory = FSDirectory.open(new File("D:/index"));
            analyzer = new IKAnalyzer();
            config = new IndexWriterConfig(version, analyzer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取IndexWriter对象的方法
     *
     * @return
     */
    public static IndexWriter getIndexWriter() {
        IndexWriter indexWriter = null;
        try {
            indexWriter = new IndexWriter(directory, config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexWriter;
    }


    /**
     * 获取IndexSearcher对象
     *
     * @return
     */
    public static IndexSearcher getIndexSearcher() {
        IndexSearcher indexSearcher = null;
        //创建indexSearcher
        try {
            DirectoryReader reader = DirectoryReader.open(directory);
            indexSearcher = new IndexSearcher(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexSearcher;
    }


    /**
     * 提交indexWriter
     */
    public static void commitIndexWriter(IndexWriter indexWriter) {
        if (indexWriter != null) {
            try {
                indexWriter.commit();
                indexWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 回滚indexWriter
     *
     * @param indexWriter
     */
    public static void rollbackIndexWriter(IndexWriter indexWriter) {
        if (indexWriter != null) {
            try {
                indexWriter.rollback();
                indexWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
