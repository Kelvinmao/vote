package struts;

import db.fun.*;
import db.pojo.*;

import java.util.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ProjectVote
{
    
    
    /**
     * pageSizeΪÿҳ��ʾ�ļ�¼��
     * pageΪ��ǰ��ʾ����ҳ
     */

    public PageBean getPageBean(int pageSize, int page)
    {
        PageBean pageBean = new PageBean();
        
        List temp = new FunProjectData().showProjectVote();
        
        int allRows = temp.size();
        
        int totalPage = pageBean.getTotalPages(pageSize, allRows);
        
        int currentPage = pageBean.getCurPage(page);
        
        List list = new FunProjectData().showProjectVote((currentPage-1)*pageSize,pageSize);
        
        int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
      
        pageBean.setList(list);
        pageBean.setAllRows(allRows);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPage);
        
        return pageBean;
    }
}