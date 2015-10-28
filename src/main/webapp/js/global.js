//login success
var useraction_login_success = 0x010101;
//login failed
var useraction_login_failed = 0x010102;
//login verifycode error
var useraction_login_verifycode_error = 0x010103;

//register success
var useraction_register_success = 0x010201;
//register failed
var useraction_register_failed = 0x010202;
//register input error
var useraction_register_input_error = 0x010203;
//register verifycode error
var useraction_register_verifycode_error = 0x010204;
//register email ok
var useraction_register_email_ok = 0x010205;
//register email exist
var useraction_register_email_exist = 0x010206;
//register email is null
var useraction_register_email_null = 0x010207;
//register nick ok
var useraction_register_nick_ok = 0x010208;
//register nick exist
var useraction_register_nick_exist = 0x010209;
//register nick is null
var useraction_register_nick_null = 0x01020A;

//update password success
var useraction_updatepassword_success = 0x010301;
//update password failed
var useraction_updatepassword_failed = 0x010302;
//update password, old password incorrect
var useraction_updatepassword_oldpwd_error = 0x010303;
	
//add machine success
var machineaction_addmachine_success = 0x020101;
//add machine failed
var machineaction_addmachine_failed = 0x020102;
//add machine,input error
var machineaction_addmachine_input_error = 0x020103;
//delete machines success
var machineaction_deletemachines_success = 0x020201;
//delete machines failed
var machineaction_deletemachines_failed = 0x020202;
//update machine success
var machineaction_updatemachine_success = 0x020301;
//update machine failed
var machineaction_updatemachine_failed = 0x020302;
//connect sftp failed
var machineaction_connectsftp_failed = 0x020401;
//connect sftp sys error
var machineaction_connectsftp_sys_error = 0x020402;
//execute command error
var machineaction_execcommand_error = 0x020501;
	
//add note success
var noteaction_addnote_success= 0x030101;
//add note failed
var noteaction_addnote_failed = 0x030102;
//add note, input error
var noteaction_addnote_input_error = 0x030103;

//delete notes success
var noteaction_deletenotes_success = 0x030201;
//delete notes failed
var noteaction_deletenotes_failed = 0x030202;
	
//update note success
var noteaction_updatenote_success = 0x030301;
//update note failed
var noteaction_updatenote_failed = 0x030302;
