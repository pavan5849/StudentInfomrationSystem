<div class="panel panel-primary" id="password_panel">
    <div class="panel-heading">
        <h4 align="center">Change Password</h4>
    </div>
  <div class="panel-body" align="center">
	<form class="form-horizontal" name="resetpassword">
	  <fieldset>
        <div class="form-group">
            <label for="current_password" class="control-label col-lg-6">Current Password</label>
            <div class="col-lg-6" align="left">
                <input type="password" name="current_password" id="current_password" data-msg-required="The input field is required." required="required">
            </div>
        </div>
        <div class="form-group">
            <label for="new_password" class="control-label col-lg-6">New Password</label>
            <div class="col-lg-6" align="left">
                <input type="password" name="new_password" id="new_password" data-msg-required="The input field is required." required="required">
            </div>
        </div>
        <div class="form-group">
            <label for="confirm_password" class="control-label col-lg-6">Confirm Password</label>
            <div class="col-lg-6" align="left">
                <input type="password" name="confirm_password" id="confirm_password" data-msg-required="The input field is required." required="required">
            </div>
        </div>
         <div class="form-group" align="center">
	        <input type="button" onclick="loaddescriptionpage()" class="btn-primary" value="Cancel">
	        <input type="button" onclick="resetpwd()" class="btn-success" value="Save">
	 	</div>
	 </fieldset>
    </form>
  </div>
</div>