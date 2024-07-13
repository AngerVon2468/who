package wiiu.mavity.who.util;

import wiiu.mavity.who.Who;

import java.io.*;

public class WhoResourcePackWitchCraft {

    public static void main(String[] args) {
        WhoResourcePackWitchCraft.experimentalConfigTesting();
    }

    public static String whoResourcesPath = System.getProperty("user.dir") + System.getProperty("file.separator") + "texture_test";

    public static File whoResourcePack = new File(whoResourcesPath, "who_emissives.zip");

    public static File whoResourcesFolder = new File(whoResourcesPath);

    public static void experimentalConfigTesting() {

        if (!whoResourcesFolder.exists()){
            whoResourcesFolder.mkdirs();
        }
        try {
            if (whoResourcePack.createNewFile()) {
                Who.LOGGER.info("File created: " + whoResourcePack.getName());
            } else {
                Who.LOGGER.info("File already exists.");
            }
            try {
                FileWriter whoResourcesWriter = new FileWriter(whoResourcePack);

                whoResourcesWriter.write("");

                whoResourcesWriter.close();
            } catch (IOException ioException) {
                Who.LOGGER.info(ioException.toString());
            }

        } catch (IOException ioException) {
            Who.LOGGER.info(ioException.toString());
        }
    }
}