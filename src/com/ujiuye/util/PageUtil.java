package com.ujiuye.util;

public class PageUtil {

    // 页码
    private int page;
    // 每页条数
    private int rows;
    // 偏移量
    private int index;
    // 总条数
    private int countRows;
    // 总页数
    private int countPage;
    // 上一页
    private int prevPage;
    // 下一页
    private int nextPage;

    public PageUtil(String page, int rows, int countRows) {
        this.rows = rows;
        this.countRows = countRows;
        init_countPage();
        init_page(page);
        init_index();
        init_prevPage();
        init_nextPage();
    }

    // 初始化下一页
    private void init_nextPage() {
        if (this.page == this.countPage){
            this.nextPage = this.countPage;
        } else {
            this.nextPage = this.page + 1;
        }
    }

    // 初始化上一页
    private void init_prevPage() {
        if (this.page == 1){
            this.prevPage = 1;
        } else {
            this.prevPage = this.page - 1;
        }
    }

    // 初始化偏移量
    private void init_index() {
        this.index = (this.page - 1) * this.rows;
    }

    // 页码初始化
    private void init_page(String page) {
        if (null == page || "".equals(page)){
            this.page = 1;
        } else {
            this.page = Integer.parseInt(page);
        }
    }

    // 初始化总页数
    private void init_countPage() {
        this.countPage = (int)Math.ceil((this.countRows * 1.0) / this.rows);
    }

    public int getPage() {
        return page;
    }

    public int getRows() {
        return rows;
    }

    public int getIndex() {
        return index;
    }

    public int getCountRows() {
        return countRows;
    }

    public int getCountPage() {
        return countPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }
}
