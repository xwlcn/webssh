package online.webssh.beans;

import java.util.List;

public class PageBean<T> {
	
	private Integer pageNum;
	private Integer pageSize = 10;
	private Integer colNum = 5;
	private Integer totalSize;
	private Integer totalPage;
	private Integer start;
	private Integer end;
	private List<T> list;
	private String query;
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public Integer getColNum() {
		return colNum;
	}
	public void setColNum(Integer colNum) {
		this.colNum = colNum;
	}
	@Override
	public String toString() {
		return "PageBean [pageNum=" + pageNum + ", pageSize=" + pageSize + ", colNum=" + colNum + ", totalSize="
				+ totalSize + ", totalPage=" + totalPage + ", start=" + start + ", end=" + end + ", list=" + list
				+ ", query=" + query + "]";
	}
	
}
