package cn.rjtraining.model;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable {

	private final int pageSize = 2; // 每页存储数据多少条
	private int pageNow = 1; // 当前是第几页
	private int pageCount; // 总共有多少页？
	private long rowCount; // 总共有多少条数据
	private List datas; // 存储数据的list
	private int maxPage = 50; // 每一个循环能存多少页

	/**
	 * 是否存在下一页
	 * 
	 * @return
	 */
	public boolean isHasNextPage() {
		if (pageNow + 1 < getLastPage() && pageNow + 1 >= 1) {
			return true;
		}
		return false;
	}

	/**
	 * 获得最后一页
	 * 
	 * @return
	 */
	public int getLastPage() {
		return (int) Math.ceil(rowCount / (1.0 * pageSize));
	}

	/**
	 * 调到第一页
	 * 
	 * @return
	 */
	public int getFirstPage() {
		return 1;
	}

	/**
	 * 是否还有前一页
	 * 
	 * @return
	 */
	public boolean isHasPreviousPage() {
		if (pageNow - 1 >= getFirstPage() && pageNow - 1 <= getLastPage()) {
			return true;
		}
		return false;
	}

	/**
	 * 获得当前页
	 * 
	 * @return
	 */
	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int rowCount) {
		this.pageCount = (int) Math.ceil(rowCount / (1.0 * pageSize));
	}

	public long getRowCount() {
		return rowCount;
	}

	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
	}

	public List getDatas() {
		return datas;
	}

	public void setDatas(List datas) {
		this.datas = datas;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 获得下一页
	 * 
	 * @return
	 */
	public int getNextPage() {
		if (isHasNextPage()) {
			return pageNow + 1;
		}
		return getLastPage();
	}

	/**
	 * 获得前一页
	 * 
	 * @return
	 */
	public int getPreviousPage() {
		if (isHasPreviousPage()) {
			return pageNow - 1;
		}
		return getFirstPage();
	}

	/**
	 * 修正当前页页吗
	 */
	public void correctPageNow() {
		if (pageNow > getLastPage()) {
			pageNow = getLastPage();
		}
		if (pageNow < 1) {
			pageNow = 1;
		}
	}

	/**
	 * 每个窗口能显示maxPage页，得到开始的页码
	 * 
	 * @return
	 */
	public int getStartPage() {
		if (getLastPage() < maxPage || getPageNow() <= (maxPage - 1) / 2) {
			return 1;
		}
		if (getPageNow() >= (getLastPage() - maxPage / 2)) {
			return getLastPage() - (maxPage - 1);
		}
		return pageNow - (maxPage - 1) / 2;

	}

	/**
	 * 得到结束页码
	 * 
	 * @return
	 */
	public int getEndPage() {
		if (getLastPage() < maxPage) {
			return getLastPage();
		}
		if (getPageNow() <= (maxPage - 1) / 2) {
			return maxPage;
		}
		if (getPageNow() >= (getLastPage() - maxPage / 2)) {
			return getLastPage();
		}
		return pageNow + maxPage / 2;
	}

}
