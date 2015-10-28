package online.webssh.action;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;

import online.webssh.beans.CodeMsg;
import online.webssh.beans.MessageBean;
import online.webssh.beans.PageBean;
import online.webssh.pojos.Note;
import online.webssh.pojos.User;
import online.webssh.service.NoteService;
import online.webssh.utils.ValidateUtil;
import online.webssh.utils.WebSshUtil;

public class NoteAction extends BaseAction<Note>{

	private static final long serialVersionUID = 1L;
	private NoteService noteService;
	private InputStream inputStream;
	private Integer pageNum = 1;
	private PageBean<Note> page;
	private String titleKeyWord = "";
	private Integer keytype = 0;
	private Integer[] ids;
	
	public String addNote() {
		
		try {
			User user = (User) session.get("user");
			if (user == null) {
				//not login
				return "notlogin";
			} else if (ValidateUtil.validStrings(model.getTitle()) && ValidateUtil.validStrings(model.getContent())) {
				model.setUser(user);
				model.setWriteTime(new Timestamp(new Date().getTime()));
				noteService.save(model);
				
				//add success
				message = new MessageBean(CodeMsg.NOTEACTION_ADDNOTE_SUCCESS_CODE,
						getText(CodeMsg.NOTEACTION_ADDNOTE_SUCCESS_MSG));
			}else{
				//input error
				message = new MessageBean(CodeMsg.NOTEACTION_ADDNOTE_INPUT_ERROR_CODE,
						getText(CodeMsg.NOTEACTION_ADDNOTE_INPUT_ERROR_MSG));
			}
		} catch (Exception e) {
			message = new MessageBean(CodeMsg.NOTEACTION_ADDNOTE_FAILED_CODE,
					getText(CodeMsg.NOTEACTION_ADDNOTE_FAILED_MSG));
		}
		inputStream = WebSshUtil.toJsonMessage(message);
		return "addNote";
	}
	
	//delete notes
	public String deleteNotes() {
		User user = (User) session.get("user");
		if (user != null && ids != null && ids.length > 0) {
			String sql = "delete from note where uid=" + user.getId() + " and id in(";
			for (int i = 0; i < ids.length - 1; i++) {
				sql += ids[i] + ",";
			}
			sql += ids[ids.length - 1] + ")";
			noteService.executeSql(sql);
			message = new MessageBean(CodeMsg.NOTEACTION_DELETENOTES_SUCCESS_CODE,
					getText(CodeMsg.NOTEACTION_DELETENOTES_SUCCESS_MSG));
		} else {
			message = new MessageBean(CodeMsg.NOTEACTION_DELETENOTES_FAILED_CODE,
					getText(CodeMsg.NOTEACTION_DELETENOTES_FAILED_MSG));
		}
		inputStream = WebSshUtil.toJsonMessage(message);
		return "deleteNotes";
	}
	
	//update note
	public String updateNote() {
		User user = (User) session.get("user");
		if (user != null && model.getId() != null) {
			Note n = noteService.get(model.getId());
			if (n.getUser() !=null && n.getUser().getId().equals(user.getId())) {
				model.setUser(user);
				model.setWriteTime(new Timestamp(new Date().getTime()));
				noteService.saveOrUpdate(model);
				
				//update success
				message = new MessageBean(CodeMsg.NOTEACTION_UPDATENOTE_SUCCESS_CODE,
						getText(CodeMsg.NOTEACTION_UPDATENOTE_SUCCESS_MSG));
			} else {
				//illegalOperation
				message = new MessageBean(CodeMsg.NOTEACTION_UPDATENOTE_FAILED_CODE,
						getText(CodeMsg.NOTEACTION_UPDATENOTE_FAILED_MSG));
				inputStream = WebSshUtil.toJsonMessage(message);
				return "illegalOperation";
			}
		}
		inputStream = WebSshUtil.toJsonMessage(message);
		return "updateNote";
	}
	
	public String notelist() {
		User user = (User) session.get("user");
		if (user == null) {
			return "notlogin";
		} else {
			page = new PageBean<Note>();
			page.setPageNum(pageNum);
			if (keytype != null && keytype == 0) {
				page.setQuery("from Note where user.id=" + user.getId() + 
						" and title like ?");
				noteService.findOnePage(page, "%" + titleKeyWord + "%");
			} else {
				page.setQuery("from Note where user.id=" + user.getId() + 
						" and title like ? and finished=?");
				noteService.findOnePage(page, "%" + titleKeyWord + "%", keytype == 1 ? true : false);
			}
		}
		return "notelist";
	}
	
	/******************************go to UI function*********************************/
	public String addNotePage() {
		return "addNotePage";
	}
	
	public String updateNotePage() {
		if (model != null && model.getId() != null) {
			model = noteService.get(model.getId());
		} else {
			return notelist();
		}
		return "updateNotePage";
	}
	
	public String viewNotePage() {
		if (model != null && model.getId() != null) {
			model = noteService.get(model.getId());
		}
		return "viewNotePage";
	}
	/**********************getter and setter function********************************/
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public NoteService getNoteService() {
		return noteService;
	}
	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public PageBean<Note> getPage() {
		return page;
	}

	public void setPage(PageBean<Note> page) {
		this.page = page;
	}

	public String getTitleKeyWord() {
		return titleKeyWord;
	}

	public void setTitleKeyWord(String titleKeyWord) {
		this.titleKeyWord = titleKeyWord;
	}

	public Integer getKeytype() {
		return keytype;
	}

	public void setKeytype(Integer keytype) {
		this.keytype = keytype;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}
	
}
