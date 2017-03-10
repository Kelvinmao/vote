package struts;

import db.fun.*;
import db.pojo.*;

import java.util.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ProjectInfoSearch
{
    
    
    /**
     * pageSizeΪÿҳ��ʾ�ļ�¼��
     * pageΪ��ǰ��ʾ����ҳ
     */
 
    public PageBean getPageBean(int pageSize, int page, String search)
    {
        PageBean pageBean = new PageBean();
        
        List temp = new FunProjectData().ProjectDataSearch(search);
        
        int allRows = temp.size();
        
        int totalPage = pageBean.getTotalPages(pageSize, allRows);
        
        int currentPage = pageBean.getCurPage(page);
        
        List list = new FunProjectData().ProjectDataSearch((currentPage-1)*pageSize,pageSize,search);
        
        int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
      
        pageBean.setList(list);
        pageBean.setAllRows(allRows);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPage);
        
        return pageBean;
    }
 
}