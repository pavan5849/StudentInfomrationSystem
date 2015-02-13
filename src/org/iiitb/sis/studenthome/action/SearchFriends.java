package org.iiitb.sis.studenthome.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.iiitb.sis.util.ConnectionPool;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SearchFriends  extends ActionSupport implements ModelDriven<ArrayList<Friend>>
{
	private static final long serialVersionUID = -4551355238964539368L;
	private ArrayList<Friend> fr=new ArrayList<Friend>();
	
	public ArrayList<Friend> getFr() {
		return fr;
	}
	public void setFr(ArrayList<Friend> fr) {
		this.fr = fr;
	}
	public void setFriendintoList(Friend friend) {
		this.fr.add(friend);
	}
	public String getFriends()
	{
		String fname=ServletActionContext.getRequest().getHeader("friendname");
		String frollno=ServletActionContext.getRequest().getHeader("friendrollno");
		if(fname==null)
			fname="";
		if(frollno==null)
			frollno="";
		PreparedStatement pstmt;
		Connection con=null;
		int count=0;
		try
		{
			con=ConnectionPool.getConnection();
			pstmt = con.prepareStatement("select sname,rollno from student where  (sname like '%"+fname+"%' && rollno like '%"+frollno+"%') && sid in (select userid from user where  utype='student' && isactive=1)");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				count++;
				Friend f=new Friend();
				f.setFname(rs.getString(1));
				f.setFrollno(rs.getString(2));
				setFriendintoList(f);
			}
			ServletActionContext.getResponse().addHeader("count", Integer.toString(count));
		    ConnectionPool.freeConnection(con);
		    return "success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		    ConnectionPool.freeConnection(con);
		    return "error";
		}

	}
	@Override
	public ArrayList<Friend> getModel() {
		return fr;
	}
}
