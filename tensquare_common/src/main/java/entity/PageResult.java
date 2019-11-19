package entity;

import java.util.List;

public class PageResult<T>  {
    // 总页数
    private Long totalPage;
    // 查询的数据
    private List<T> data;

    /**
     * 控构造器
     */
    public PageResult() {
    }

    public PageResult(Long totalPage, List<T> data) {
        super();
        this.totalPage = totalPage;
        this.data = data;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
