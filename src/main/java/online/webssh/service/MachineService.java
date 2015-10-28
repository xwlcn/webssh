package online.webssh.service;

import online.webssh.pojos.Machine;

public interface MachineService extends BaseService<Machine>{

	void updatemachine(Integer uid, Machine model);

}
