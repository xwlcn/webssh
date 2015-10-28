package online.webssh.service.impl;

import online.webssh.pojos.Machine;
import online.webssh.service.MachineService;
import online.webssh.utils.EndecryptUtil;

public class MachineServiceImpl extends BaseServiceImpl<Machine> implements MachineService{

	@Override
	public void updatemachine(Integer uid, Machine model) {
		//get the old info
		Machine machine = dao.get(model.getId());
		//confirm the machine belong to the user
		if (uid.equals(machine.getUser().getId())) {
			//reset info
			machine.setName(model.getName());
			machine.setHostname(model.getHostname());
			machine.setUsername(model.getUsername());
			machine.setPort(model.getPort());
			//if the password changed
			if (!EndecryptUtil.get3DESDecrypt(machine.getPassword(), uid + "").equals(model.getPassword())) {
				//encrypt the password
				machine.setPassword(EndecryptUtil.get3DESEncrypt(model.getPassword(), uid + ""));
			}
		}
		dao.update(machine);
	}
	
}
