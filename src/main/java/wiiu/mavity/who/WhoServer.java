package wiiu.mavity.who;

import net.fabricmc.api.DedicatedServerModInitializer;

import wiiu.mavity.who.util.data.TardisDataReaderAndWriter;

public class WhoServer implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        TardisDataReaderAndWriter.genTardisDataFiles();
    }
}