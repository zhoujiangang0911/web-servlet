package cn.rjtraining.model;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable {

	private final int pageSize = 2; // ÿҳ�洢���ݶ�����
	private int pageNow = 1; // ��ǰ�ǵڼ�ҳ
	private int pageCount; // �ܹ��ж���ҳ��
	private long rowCount; // �ܹ��ж���������
	private List datas; // �洢���ݵ�list
	private int maxPage = 50; // ÿһ��ѭ���ܴ����ҳ

	/**
	 * �Ƿ������һҳ
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
	 * ������һҳ
	 * 
	 * @return
	 */
	public int getLastPage() {
		return (int) Math.ceil(rowCount / (1.0 * pageSize));
	}

	/**
	 * ������һҳ
	 * 
	 * @return
	 */
	public int getFirstPage() {
		return 1;
	}

	/**
	 * �Ƿ���ǰһҳ
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
	 * ��õ�ǰҳ
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
	 * �����һҳ
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
	 * ���ǰһҳ
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
	 * ������ǰҳҳ��
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
	 * ÿ����������ʾmaxPageҳ���õ���ʼ��ҳ��
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
	 * �õ�����ҳ��
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
