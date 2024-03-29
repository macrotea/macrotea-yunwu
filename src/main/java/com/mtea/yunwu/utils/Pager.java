package com.mtea.yunwu.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mtea.yunwu.model.base.BaseModel;

/**
 * 分页器
 * @author macrotea@qq.com
 * @date 2012-12-10 下午11:42:39
 * @version 1.0
 * @note
 */
public final class Pager<T extends BaseModel> implements Serializable {
	
	private static final long serialVersionUID = -7742988063989166270L;
	
	//默认页大小
	public static int DEFAULT_PAGE_SIZE = 10;
	
	// 页码索引限制为5个
	private static int DEFALT_DIPSLAY_LIMIT = 10;

	// 前导限制
	private static int DEFALT_PREV_LIMIT = 4;

	// 后续限制
	private static int DEFALT_NEXT_LIMIT = DEFALT_DIPSLAY_LIMIT - DEFALT_PREV_LIMIT;
	
	private int firstPage;
    
    private int lastPage;
       
    private int nextPage;
    
    private int prevPage;
       
    private int currentPage;
    
    private int totalPage;
    
    private long rowCount;
    
    private int pageSize;
    
    private boolean hasNext;
    
    private boolean hasPrev;
    
    private boolean hasFirst;
    
    private boolean hasLast;
    
    private List<T> dataList;
    
	//页码索引集合
	private List<Integer> indexList;
    
    public Pager() {}

    public Pager(List<T> dataList,long rowCount,int currentPage) {
    	this(dataList,rowCount,DEFAULT_PAGE_SIZE,currentPage);
    }
    
    public Pager(List<T> dataList,long rowCount,int pageSize,int currentPage) {
    	this.dataList=dataList;
    	this.rowCount = rowCount;   
        this.currentPage = currentPage;   
        this.pageSize = pageSize;
        this.totalPage = new Long(this.rowCount % pageSize == 0 ? this.rowCount / pageSize : this.rowCount / pageSize + 1).intValue() ;      
        if(this.totalPage > 0){   
            this.hasFirst = true ;   
            this.firstPage = 1 ;   
        }   
        if(this.currentPage  > 1 ){   
            this.hasPrev = true ;   
            this.prevPage = this.currentPage - 1;   
        }
        if(this.totalPage > 0 && this.currentPage < this.totalPage){   
            this.hasNext = true;   
            this.nextPage = this.currentPage + 1 ;   
        }   
        if(this.totalPage > 0){   
            this.hasLast = true;   
            this.lastPage = new Long(this.totalPage).intValue();
        }
        
		/* handle indexList */
		indexList = new ArrayList<Integer>();
		if (this.totalPage >= this.currentPage && currentPage >= DEFALT_DIPSLAY_LIMIT) {
			// 处理中间情况:前2后3
			for (int i = currentPage - DEFALT_PREV_LIMIT; i < currentPage; i++) {
				indexList.add(i);
			}
			// 处理尾部情况
			if (totalPage - currentPage >= DEFALT_NEXT_LIMIT) {
				for (int i = currentPage; i <= currentPage + DEFALT_NEXT_LIMIT; i++) {
					indexList.add(i);
				}
			} else {
				for (int i = currentPage; i <= totalPage; i++) {
					indexList.add(i);
				}
			}
		} else {
			if (this.totalPage >= DEFALT_DIPSLAY_LIMIT) {
				for (int i = 1; i <= DEFALT_DIPSLAY_LIMIT; i++) {
					indexList.add(i);
				}
			} else {
				for (int i = 1; i <= totalPage; i++) {
					indexList.add(i);
				}
			}
		}
    }

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public long getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isHasNext() {
		return hasNext;
	}
	
	public boolean getHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isHasPrev() {
		return hasPrev;
	}
	
	public boolean getHasPrev() {
		return hasPrev;
	}

	public void setHasPrev(boolean hasPrev) {
		this.hasPrev = hasPrev;
	}

	public boolean isHasFirst() {
		return hasFirst;
	}
	
	public boolean getHasFirst() {
		return hasFirst;
	}

	public void setHasFirst(boolean hasFirst) {
		this.hasFirst = hasFirst;
	}

	public boolean isHasLast() {
		return hasLast;
	}
	
	public boolean getHasLast() {
		return hasLast;
	}

	public void setHasLast(boolean hasLast) {
		this.hasLast = hasLast;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public List<Integer> getIndexList() {
		return indexList;
	}

	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", firstPage=" + firstPage + ", hasFirst=" + hasFirst + ", hasLast=" + hasLast + ", hasNext="
				+ hasNext + ", hasPrev=" + hasPrev + ", lastPage=" + lastPage + ", nextPage=" + nextPage + ", pageSize=" + pageSize + ", prevPage="
				+ prevPage + ", rowCount=" + rowCount + ", totalPage=" + totalPage + ", dataList.size="+dataList.size() + "]";
	}
}
