package online.webssh.beans;

public class CodeMsg {
	
	/*
	 *     actionID     functionID    messageID
	 *	     xxx           xxx           xxx
	 * 
	 * */
	
	/**********************************UserAction*************************************/
	
	//login success
	public static final Integer USERACTION_LOGIN_SUCCESS_CODE = 0x010101;
	public static final String USERACTION_LOGIN_SUCCESS_MSG = "useraction.login.success";
	
	//login failed
	public static final Integer USERACTION_LOGIN_FAILED_CODE = 0x010102;
	public static final String USERACTION_LOGIN_FAILED_MSG = "useraction.login.failed";
	
	//login verifycode error
	public static final Integer USERACTION_LOGIN_VERIFYCODE_ERROR_CODE = 0x010103;
	public static final String USERACTION_LOGIN_VERIFYCODE_ERROR_MSG = "useraction.login.verifycode.error";
		
	//register success
	public static final Integer USERACTION_REGISTER_SUCCESS_CODE = 0x010201;
	public static final String USERACTION_REGISTER_SUCCESS_MSG = "useraction.register.success";
	
	//register failed
	public static final Integer USERACTION_REGISTER_FAILED_CODE = 0x010202;
	public static final String USERACTION_REGISTER_FAILED_MSG = "useraction.register.failed";
	
	//register input error
	public static final Integer USERACTION_REGISTER_INPUT_ERROR_CODE = 0x010203;
	public static final String USERACTION_REGISTER_INPUT_ERROR_MSG = "useraction.register.input.error";
	
	//register verifycode error
	public static final Integer USERACTION_REGISTER_VERIFYCODE_ERROR_CODE = 0x010204;
	public static final String USERACTION_REGISTER_VERIFYCODE_ERROR_MSG = "useraction.register.verifycode.error";
	
	//register email ok
	public static final Integer USERACTION_REGISTER_EMAIL_OK_CODE = 0x010205;
	public static final String USERACTION_REGISTER_EMAIL_OK_MSG = "useraction.register.email.ok";
	
	//register email exist
	public static final Integer USERACTION_REGISTER_EMAIL_EXIST_CODE = 0x010206;
	public static final String USERACTION_REGISTER_EMAIL_EXIST_MSG = "useraction.register.email.exist";
	
	//register email is null
	public static final Integer USERACTION_REGISTER_EMAIL_NULL_CODE = 0x010207;
	public static final String USERACTION_REGISTER_EMAIL_NULL_MSG = "useraction.register.email.null";
	
	//register nick ok
	public static final Integer USERACTION_REGISTER_NICK_OK_CODE = 0x010208;
	public static final String USERACTION_REGISTER_NICK_OK_MSG = "useraction.register.nick.ok";
		
	//register nick exist
	public static final Integer USERACTION_REGISTER_NICK_EXIST_CODE = 0x010209;
	public static final String USERACTION_REGISTER_NICK_EXIST_MSG = "useraction.register.nick.exist";
	
	//register nick is null
	public static final Integer USERACTION_REGISTER_NICK_NULL_CODE = 0x01020A;
	public static final String USERACTION_REGISTER_NICK_NULL_MSG = "useraction.register.nick.null";
		
	//update password success
	public static final Integer USERACTION_UPDATEPASSWORD_SUCCESS_CODE = 0x010301;
	public static final String USERACTION_UPDATEPASSWORD_SUCCESS_MSG = "useraction.updatepassword.success";
	
	//update password failed
	public static final Integer USERACTION_UPDATEPASSWORD_FAILED_CODE = 0x010302;
	public static final String USERACTION_UPDATEPASSWORD_FAILED_MSG = "useraction.updatepassword.failed";
	
	//update password, old password incorrect
	public static final Integer USERACTION_UPDATEPASSWORD_OLDPWD_ERROR_CODE = 0x010303;
	public static final String USERACTION_UPDATEPASSWORD_OLDPWD_ERROR_MSG = "useraction.updatepassword.oldpwd.error";

	/**********************************MachineAction*************************************/
	
	//add machine success
	public static final Integer MACHINEACTION_ADDMACHINE_SUCCESS_CODE = 0x020101;
	public static final String MACHINEACTION_ADDMACHINE_SUCCESS_MSG = "machineaction.addmachine.success";

	//add machine failed
	public static final Integer MACHINEACTION_ADDMACHINE_FAILED_CODE = 0x020102;
	public static final String MACHINEACTION_ADDMACHINE_FAILED_MSG = "machineaction.addmachine.failed";
	
	//add machine,input error
	public static final Integer MACHINEACTION_ADDMACHINE_INPUT_ERROR_CODE = 0x020103;
	public static final String MACHINEACTION_ADDMACHINE_INPUT_ERROR_MSG = "machineaction.addmachine.input.error";

	//delete machines success
	public static final Integer MACHINEACTION_DELETEMACHINES_SUCCESS_CODE = 0x020201;
	public static final String MACHINEACTION_DELETEMACHINES_SUCCESS_MSG = "machineaction.deletemachine.success";
	
	//delete machines failed
	public static final Integer MACHINEACTION_DELETEMACHINES_FAILED_CODE = 0x020202;
	public static final String MACHINEACTION_DELETEMACHINES_FAILED_MSG = "machineaction.deletemachine.failed";
		
	//update machine success
	public static final Integer MACHINEACTION_UPDATEMACHINE_SUCCESS_CODE = 0x020301;
	public static final String MACHINEACTION_UPDATEMACHINE_SUCCESS_MSG = "machineaction.updatemachine.success";
		
	//update machine failed
	public static final Integer MACHINEACTION_UPDATEMACHINE_FAILED_CODE = 0x020302;
	public static final String MACHINEACTION_UPDATEMACHINE_FAILED_MSG = "machineaction.updatemachine.failed";
	
	//connect sftp failed
	public static final Integer MACHINEACTION_CONNECTSFTP_FAILED_CODE = 0x020401;
	public static final String MACHINEACTION_CONNECTSFTP_FAILED_MSG = "machineaction.connectsftp.failed";
	
	//connect sftp sys error
	public static final Integer MACHINEACTION_CONNECTSFTP_SYS_ERROR_CODE = 0x020402;
	public static final String MACHINEACTION_CONNECTSFTP_SYS_ERROR_MSG = "machineaction.connectsftp.sys.error";
	
	//execute command error
	public static final Integer MACHINEACTION_EXECCOMMAND_ERROR_CODE = 0x020501;
	public static final String MACHINEACTION_EXECCOMMAND_ERROR_MSG = "machineaction.execcommand.error";
	
	/**********************************NoteAction*************************************/
	
	//add note success
	public static final Integer NOTEACTION_ADDNOTE_SUCCESS_CODE = 0x030101;
	public static final String NOTEACTION_ADDNOTE_SUCCESS_MSG = "noteaction.addnote.success";

	//add note failed
	public static final Integer NOTEACTION_ADDNOTE_FAILED_CODE = 0x030102;
	public static final String NOTEACTION_ADDNOTE_FAILED_MSG = "noteaction.addnote.failed";
	
	//add note, input error
	public static final Integer NOTEACTION_ADDNOTE_INPUT_ERROR_CODE = 0x030103;
	public static final String NOTEACTION_ADDNOTE_INPUT_ERROR_MSG = "noteaction.addnote.input.error";
	
	//delete notes success
	public static final Integer NOTEACTION_DELETENOTES_SUCCESS_CODE = 0x030201;
	public static final String NOTEACTION_DELETENOTES_SUCCESS_MSG = "noteaction.deletenotes.success";
	
	//delete notes failed
	public static final Integer NOTEACTION_DELETENOTES_FAILED_CODE = 0x030202;
	public static final String NOTEACTION_DELETENOTES_FAILED_MSG = "noteaction.deletenotes.failed";
	
	//update note success
	public static final Integer NOTEACTION_UPDATENOTE_SUCCESS_CODE = 0x030301;
	public static final String NOTEACTION_UPDATENOTE_SUCCESS_MSG = "noteaction.updatenote.success";
	
	//update note failed
	public static final Integer NOTEACTION_UPDATENOTE_FAILED_CODE = 0x030302;
	public static final String NOTEACTION_UPDATENOTE_FAILED_MSG = "noteaction.updatenote.failed";

}
