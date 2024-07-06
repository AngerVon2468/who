package wiiu.mavity.who.util.data;

import com.google.gson.*;

import wiiu.mavity.who.Who;

import java.io.*;

public class TardisDataReaderAndWriter {

    public static String tardisDataPath = System.getProperty("user.home") + System.getProperty("file.separator") + "." + Who.NAME;

    public static File tardisDataFile = new File(tardisDataPath, "tardis_data.json");

    public static File tardisDataFolder = new File(tardisDataPath);

    public static void genTardisDataFiles() {

        if (!tardisDataFolder.exists()){
            tardisDataFolder.mkdirs();
        }
        try {
            if (tardisDataFile.createNewFile()) {
                Who.LOGGER.error("File created: " + tardisDataFile.getName());
            } else {
                Who.LOGGER.error("File already exists.");
            }
        } catch (IOException ioException) {
            Who.LOGGER.error(ioException.toString());
        }
        try {
            FileWriter dungeonUtilsConfigWriter = new FileWriter(tardisDataFile);
            dungeonUtilsConfigWriter.write("{" + System.getProperty("line.separator"));
            dungeonUtilsConfigWriter.write("    \"tardisIds\": " + getTardisIds() + System.getProperty("line.separator"));
            dungeonUtilsConfigWriter.write("}");
            dungeonUtilsConfigWriter.close();
        } catch (IOException ioException) {
            Who.LOGGER.error(ioException.toString());
        }
        Who.LOGGER.error("Tardis ids: " + getTardisIds());

    }

    public static void setTardisDataFileValue(Integer value) {

        try {

            FileWriter dungeonUtilsConfigWriter = new FileWriter(tardisDataFile);
            dungeonUtilsConfigWriter.write("{" + System.getProperty("line.separator"));
            dungeonUtilsConfigWriter.write("    \"tardisIds\": " + value + System.getProperty("line.separator"));
            dungeonUtilsConfigWriter.write("}");
            dungeonUtilsConfigWriter.close();

        } catch (IOException ioException) {

            Who.LOGGER.error(ioException.toString());

        }

    }

    public static BufferedReader bufferedReader;

    public static Gson gson = new Gson();

    static {
        try {
            bufferedReader = new BufferedReader(new FileReader(tardisDataFile));
        } catch (FileNotFoundException fileNotFoundException) {
            Who.LOGGER.error(fileNotFoundException.toString());
        }

    }

    public static Object json;
    public static JsonElement jsonTree;

    static {

        if (tardisDataFile.exists()) {

            json = gson.fromJson(bufferedReader, Object.class);
            jsonTree = JsonParser.parseString(json.toString());

        }

    }

    public static JsonObject jsonObject;

    static {

        if (jsonTree != null && jsonTree.isJsonObject()) {
            jsonObject = jsonTree.getAsJsonObject();
        }

    }

    public static int getTardisIds() {

        return jsonTree == null ? 0 : jsonObject.get("tardisIds").getAsInt();
    }
}