package com.baizhi.common.dao;

import com.baizhi.common.entity.Banner;
import com.baizhi.common.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LuceneDao {

    //添加索引的方法
    public void addIndex(Banner banner) {

        IndexWriter indexWriter = LuceneUtil.getIndexWriter();

        try {
            Document document = new Document();

            document.add(new StringField("id", banner.getId(), Field.Store.YES));
            document.add(new TextField("name", banner.getName(), Field.Store.YES));
            document.add(new StringField("url", banner.getUrl(), Field.Store.YES));
            indexWriter.addDocument(document);

            LuceneUtil.commitIndexWriter(indexWriter);

        } catch (IOException e) {
            e.printStackTrace();

            LuceneUtil.rollbackIndexWriter(indexWriter);
        }


    }


    //删除索引的方法

    public void deleteIndex(String id) {

        IndexWriter indexWriter = LuceneUtil.getIndexWriter();

        try {
            indexWriter.deleteDocuments(new Term("id", id));

            LuceneUtil.commitIndexWriter(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();

            LuceneUtil.rollbackIndexWriter(indexWriter);
        }


    }


    //修改索引的方法

    public void updateIndex(Banner banner) {

        IndexWriter indexWriter = LuceneUtil.getIndexWriter();

        try {

            Document document = new Document();

            document.add(new StringField("id", banner.getId(), Field.Store.YES));
            document.add(new TextField("name", banner.getName(), Field.Store.YES));
            document.add(new StringField("url", banner.getUrl(), Field.Store.YES));

            indexWriter.updateDocument((new Term("id", banner.getId())), document);
            LuceneUtil.commitIndexWriter(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();

            LuceneUtil.rollbackIndexWriter(indexWriter);
        }
    }


    //查询所有的索引的方法

    public List<Banner> queryAllIndex(String value, Integer result) throws ParseException {

        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();

        Banner banner = null;

        List<Banner> banners = null;

        try {
            TopDocs topDocs = indexSearcher.search(new MultiFieldQueryParser(Version.LUCENE_44, new String[]{"id", "name", "url"}, new IKAnalyzer()).parse(value), result);

            ScoreDoc[] scoreDocs = topDocs.scoreDocs;

            banners = new ArrayList<Banner>();

            for (int i = 0; i < scoreDocs.length; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];

                int doc = scoreDoc.doc;

                Document document = indexSearcher.doc(doc);
                banner = new Banner();

                banner.setId(document.get("id"));
                banner.setName(document.get("name"));
                banner.setUrl(document.get("url"));

                banners.add(banner);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return banners;
    }
}
