package wiiu.mavity.who.util.data;

import com.google.gson.*;

import wiiu.mavity.who.Who;

import java.io.*;

public class TardisDataReaderAndWriter {

    public static String tardisDataPath = System.getProperty("user.home") + System.getProperty("file.separator") + "." + Who.NAME;

    public static File tardisDataFile = new File(tardisDataPath, "tardis_data.json");

    public static File tardisDataFolder = new File(tardisDataPath);

    public static void tardisData() {

        if (!tardisDataFolder.exists()){
            tardisDataFolder.mkdirs();
        }
        try {
            if (tardisDataFile.createNewFile()) {
                Who.LOGGER.info("File created: " + tardisDataFile.getName());
            } else {
                Who.LOGGER.info("File already exists.");
            }
        } catch (IOException ioException) {
            Who.LOGGER.info(ioException.toString());
        }
        try {
            FileWriter dungeonUtilsConfigWriter = new FileWriter(tardisDataFile);

            dungeonUtilsConfigWriter.write("{" + System.getProperty("line.separator"));
            dungeonUtilsConfigWriter.write("    \"tardisIds\": " + tardisIds() + System.getProperty("line.separator"));
            dungeonUtilsConfigWriter.write("}");
            dungeonUtilsConfigWriter.close();
        } catch (IOException ioException) {
            Who.LOGGER.info(ioException.toString());
        }

    }

    public static BufferedReader bufferedReader;

    public static Gson gson = new Gson();

    static {
        try {
            bufferedReader = new BufferedReader(new FileReader(tardisDataFile));
        } catch (FileNotFoundException e) {
            Who.LOGGER.info(e.toString());
        }

    }

    public static Object json;
    public static JsonParser parser = new JsonParser();
    public static JsonElement jsonTree;

    static {

        if (tardisDataFile.exists()) {

            json = gson.fromJson(bufferedReader, Object.class);
            jsonTree = parser.parse(json.toString());

        }

    }

    public static JsonObject jsonObject;

    static {

        if (jsonTree != null && jsonTree.isJsonObject()) {
            jsonObject = jsonTree.getAsJsonObject();
        }

    }

    public static int tardisIds() {

        return jsonTree == null ? 0 : jsonObject.get("tardisIds").getAsInt();
    }

    public static void main(String[] args) {
        tardisData();
        System.out.println(tardisIds());
    }
}