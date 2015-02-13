package org.iiitb.sis.login;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import org.iiitb.sis.util.Constants;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import org.iiitb.sis.util.ConnectionPool;
import org.iiitb.sis.dto.User;

public class LoginAction extends ActionSupport implements SessionAware
{
	private static final long serialVersionUID = -7284487810665909293L;
	Connection con;
	private String uname;
	private int userid;
	private String upwd;
	private Map<String, Object> session;
	private int nid;
	private int sid;
	private String current_password;
	private String confirm_password;
	private String new_password;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	public String getCurrent_password() {
		return current_password;
	}
	public void setCurrent_password(String current_password) {
		this.current_password = current_password;
	}
	public String getNew_password() {
		return new_password;
	}
	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getUname()
	{
		return this.uname;
	}
	public void setUname(String uname)
	{
		this.uname = uname;
	}
	public String getUpwd()
	{
		return this.upwd;
	}
	public void setUpwd(String upwd)
	{
		this.upwd = upwd;
	}
	public Map<String, Object> getSession()
	{
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session)
	{
		this.session = session;
	}
	public String execute() throws NamingException, SQLException
	{
		clearFieldErrors();

		User user = (User) session.get("user");
		if (user != null)
		{
			return SUCCESS;
		}
		else
		{
			con=ConnectionPool.getConnection();
			PreparedStatement ps=con.prepareStatement(Constants.SELECT_USER);
			ps.setString(1,uname);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				if(upwd.equals(rs.getString(3)))
				{
					if(rs.getBoolean(5))
					{
						String utype=rs.getString(6);
						session.put("lastloggedon",rs.getTimestamp(4));
						session.put("userid",rs.getInt(1));
						session.put("usertype",utype);
						updateUserLastLoggedIn(rs.getInt(1));
					    ConnectionPool.freeConnection(con);
						switch(utype)
						{
							case "admin" : return "admin";
							case "faculty" : return "faculty";
							case "student" : return "student";
							default : {
								ConnectionPool.freeConnection(con);
								ServletActionContext.getResponse().addHeader("msg", Constants.ERR);
								return "error";
							}
						}
					}
					else
					{
						ServletActionContext.getResponse().addHeader("msg", Constants.USER_DISABLED);
					    ConnectionPool.freeConnection(con);
						return "error";
					}
				}
				else
				{
					ServletActionContext.getResponse().addHeader("msg", Constants.ERR_PWD);
				    ConnectionPool.freeConnection(con);
					return "error";
				}
			}
			else
			{
				ServletActionContext.getResponse().addHeader("msg", Constants.ERR_UNAME);
			    ConnectionPool.freeConnection(con);
				return "error";
			}
		}
	}
	private void updateUserLastLoggedIn(int userid) {
		try {
			PreparedStatement ps=con.prepareStatement(Constants.UPDATE_LASTLOGGEDIN);			
			ps.setTimestamp(1,new Timestamp(new java.util.Date().getTime()));
			ps.setInt(2, userid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	public String getUserImage()
	{
		PreparedStatement pstmt;
		try
		{
			con=ConnectionPool.getConnection();
			pstmt = con.prepareStatement(Constants.GET_IMAGE);
		    pstmt.setInt(1,Integer.parseInt(session.get("userid").toString()));
		    ResultSet rs = pstmt.executeQuery();
		    if(rs.next())
		    {
		    	HttpServletResponse res=ServletActionContext.getResponse();
		    	res.setContentType("image/jpeg");
		    	InputStream in=rs.getBinaryStream(1);
		    	OutputStream out=res.getOutputStream();
		    	byte[] buffer=new byte[1024];
		    	int len;
		    	while((len=in.read(buffer))!=-1)
		    	{
		    		out.write(buffer,0,len);
		    	}
		    }
		    ConnectionPool.freeConnection(con);
		    return NONE;
		} 
		catch (SQLException | IOException e) 
		{
			e.printStackTrace();
		    ConnectionPool.freeConnection(con);
		    return NONE;
		}
	}
	public String getNewsDetails()
	{
		PreparedStatement pstmt;
		try
		{
			con=ConnectionPool.getConnection();
			pstmt = con.prepareStatement(Constants.GET_NEWS_DETAILS);
		    pstmt.setInt(1,nid);
		    ResultSet rs = pstmt.executeQuery();
		    if(rs.next())
		    {
		    	HttpServletResponse res=ServletActionContext.getResponse();
		    	res.setHeader("newstitle", rs.getString(1));
		    	res.setHeader("newsdetails", rs.getString(2));
		    	res.setHeader("coursename", rs.getString(3));
		    	res.setHeader("facultyname", rs.getString(4));
			    ConnectionPool.freeConnection(con);
			    return "success";		    	
		    }
		    else
		    {
			    ConnectionPool.freeConnection(con);
		    	throw new Exception("News Not Found/Inactive contact Admin");
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		    ConnectionPool.freeConnection(con);
		    return "error";
		}
	}
	public String getMyProfile()
	{
		String utype=session.get("usertype").toString();
		int userid=Integer.parseInt(session.get("userid").toString());
		PreparedStatement pstmt;
		try
		{
			con=ConnectionPool.getConnection();
			switch(utype)
			{
				case "admin" :pstmt = con.prepareStatement(Constants.GET_ADMIN_PROFILE);break;
				case "faculty" :pstmt = con.prepareStatement(Constants.GET_FACULTY_PROFILE);break;
				case "student" :pstmt = con.prepareStatement(Constants.GET_STUDENT_PROFILE);break;
				default : {
					ConnectionPool.freeConnection(con);
					ServletActionContext.getResponse().addHeader("msg", Constants.ERR);
					return "error";
				}
			}
			pstmt.setInt(1,userid);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				HttpServletResponse res=ServletActionContext.getResponse();
				res.addHeader("uname", rs.getString(1));
				res.addHeader("name", rs.getString(2));
				res.addHeader("phno", rs.getString(3));
				res.addHeader("email", rs.getString(4));
				
				if(utype.equals("student"))
				{
					res.addHeader("dob", rs.getDate(5).toString());
					res.addHeader("lastdegree", rs.getString(6));
					res.addHeader("rollno", rs.getString(7));
				}
				else if(utype.equals("faculty"))
				{
					res.addHeader("doj", rs.getDate(5).toString());
				}
				else if(utype.equals("admin"))
				{
					res.addHeader("doj", rs.getDate(5).toString());
				}
			}
			else
			{
				ServletActionContext.getResponse().addHeader("msg", Constants.ERR_UNAME);
			    ConnectionPool.freeConnection(con);
			    return "error";
			}
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
	public String resetPassword()
	{
		System.out.println("hi");
		return "success";
	}
	public String getImage()
	{
		OutputStream out;
		try 
		{
			con=ConnectionPool.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select userimage from user where userid=? && userimage is not null");
			pstmt.setInt(1, sid);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				HttpServletResponse res=ServletActionContext.getResponse();
				res.setContentType("image/jpg");
				InputStream in=rs.getBinaryStream(1);
				out=res.getOutputStream();
				byte[] buffer=new byte[1024];
				int len;
				while((len=in.read(buffer))!=-1)
				{
					out.write(buffer,0,len);
				}
			}
			else
			{
				System.out.println("No Image Found");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		ConnectionPool.freeConnection(con);
	    return NONE;
	}
}
