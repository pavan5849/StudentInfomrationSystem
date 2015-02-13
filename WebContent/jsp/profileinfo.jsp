    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <!-- <a class="navbar-brand" href="#">Project name</a> 
          <img src="./getuserimage" width="100" height="100" />-->
   
        </div>
        <div id="navbar" class="navbar-collapse collapse">
        	<div class="container-fluid">
        		<div class="row-fluid">
        			<div class="col-lg-1" align="left">
        				<!-- <img style="padding-top:5px;" src=./images/pavan.jpg height="70px" width="70px"> -->
        				 <img style="padding-top:5px;" src="./getimage" height="70px" width="70px" />
        			</div>
        			<div class="col-lg-3"></div>
			        <div class="col-lg-3">
			        	<h1 align="center"><span class="label" style="color:#337ab7"><em>STUDENT INFORMATION SYSTEM</em></span></h1>
			        </div>
			        <div class="col-lg-2"></div>
			        <div class="col-lg-3" align="right">
			        	<div class="container-fluid">
			        		<div class="row-fluid">
		     					<label style="padding-top:15px;color:#9d9d9d;">Welcome <%= session.getAttribute("userid") %></label>
		        				<a href="./logout" style="color:#fff;">Logout</a>
			        		</div>
			        		<div class="row-fluid">
			        			<label style="padding-top:15px;color:#9d9d9d;" >Last Logged in <%= session.getAttribute("lastloggedon") %></label>
			        		</div>
			        	</div>
			        </div>
	        	</div>
	        </div>
        </div>
      </div>
    </nav>
