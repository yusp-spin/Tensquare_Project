package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import javax.annotation.Resource;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao dao;

//    @Autowired
//    private IdWorker idWorker;

    public void save(Article article) {
//        article.setId(idWorker.nextId()+"");
        dao.save(article);
    }

    //or查询
    public Page<Article> findByKeyWord(String keyWord, int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        return dao.findByTitleOrContentLike(keyWord, keyWord, pageable);
    }
}
