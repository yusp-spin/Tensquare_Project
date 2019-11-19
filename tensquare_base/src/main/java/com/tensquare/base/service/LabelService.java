package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import utils.IdWorker;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class LabelService {
    @Resource
    private LabelDao labelDao;
    @Resource(name = "idWorker")
    private IdWorker idWorker;

    /**
     * 查询所有
     * @return
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public Label findById(String id) {
        return labelDao.findById(id).orElse(null);
    }

    public void save(Label label) {
        // 根据雪花算法生成分布式id
        label.setId(String.valueOf(idWorker.nextId()));
        labelDao.save(label);
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label){
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //new一个集合存放所有条件
                List<Predicate> list=new LinkedList<>();
                if (!StringUtils.isEmpty(label.getLabelname())) {
                    Predicate labelname = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(labelname);
                }
                if (!StringUtils.isEmpty(label.getState())) {
                    Predicate state = cb.like(root.get("state").as(String.class), label.getState());
                    list.add(state);
                }
                Predicate[] array = new Predicate[list.size()];
                array = list.toArray(array);
                return cb.and(array);//and相当于where labelname like "%小明%" and state ="1"
            }
        });
    }

    //查询条件和分页
    public Page<Label> pageQuery(Label label,int page,int size){
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //new一个集合存放所有条件
                List<Predicate> list=new LinkedList<>();
                if (!StringUtils.isEmpty(label.getLabelname())) {
                    Predicate labelname = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(labelname);
                }
                if (!StringUtils.isEmpty(label.getState())) {
                    Predicate state = cb.like(root.get("state").as(String.class), label.getState());
                    list.add(state);
                }
                Predicate[] array = new Predicate[list.size()];
                array = list.toArray(array);
                return cb.and(array);//and相当于where labelname like "%小明%" and state ="1"
            }
        },PageRequest.of(page-1,size));
    }

}
