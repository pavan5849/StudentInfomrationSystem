package org.iiitb.sis.util;

public interface Constants {
	public String SELECT_USER="select * from user where uname=?";
	public String ERR_UNAME="Invalid Username/ User Does not exists";
	public String ERR_PWD="Invalid Password";
	public String ERR="Error!!!, Contact Admin";
	public String USER_DISABLED="User Disabled, Contact Admin";
	public String UPDATE_LASTLOGGEDIN = "update user set lastloggedin=? where userid=?";
	public String GET_IMAGE = "select userimage from user where userid=?";
	public String GET_NEWS = "select nid,ntitle from news where cid=1 || cid in (select cid from enrollment where sid=?) && isactive=1";
	public String GET_NEWS_DETAILS = "select ntitle,ndetails,cname,fname from news n,courses c,faculty f where nid=? && n.fid=f.fid && n.cid=c.cid && n.isactive=1";
	public String GET_ADMIN_PROFILE = "select uname,fname,phno,email,doj from faculty,user where utype='admin' && isactive=1 && userid=? && fid=userid";
	public String GET_FACULTY_PROFILE = "select uname,fname,phno,email,doj from faculty,user where utype='faculty' && isactive=1 && userid=? && fid=userid";
	public String GET_STUDENT_PROFILE = "select uname,sname,phno,email,dob,lastdegree,rollno from student,user where utype='student' && isactive=1 && userid=? && sid=userid";
	//public String GET_SEARCH_FRIENDS = "select sname,rollno from student where sname like '%?%' || rollno like '%?%'";
	public String GET_FRIEND_PROFILE = "select sname,phno,email,dob,lastdegree,rollno,sid from student where rollno=?";
	public String GET_COURSE_DETAIL = "select cid,cname,credits,csyllabus	 from courses where ccode=?";
}
