package com.nmmoc7.avarus.client.machine;

import com.nmmoc7.avarus.client.machine.api.MachineClientRegistry;
import com.nmmoc7.avarus.client.machine.machineclients.TestMachineClient;
import com.nmmoc7.avarus.machine.AvarusMachineTypes;

/**
 * @author DustW
 **/
public class AvarusMachineClients {
    public static void register() {
        MachineClientRegistry.register(AvarusMachineTypes.TEST.get(), new TestMachineClient());
    }
}
