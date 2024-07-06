package wiiu.mavity.who.util.data;

import com.google.gson.Gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.Nullable;
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
        /*
        try {
            FileWriter dungeonUtilsConfigWriter = new FileWriter(tardisDataFile);

            dungeonUtilsConfigWriter.write("{" + System.getProperty("line.separator"));
            dungeonUtilsConfigWriter.write("    \"tardisIds\": " + 0 + System.getProperty("line.separator"));
            dungeonUtilsConfigWriter.write("}");
            dungeonUtilsConfigWriter.close();
        } catch (IOException ioException) {
            Who.LOGGER.info(ioException.toString());
        }

        System.out.println(tardisIds());
        */
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

    public static Object json = gson.fromJson(bufferedReader, Object.class);

    public static JsonParser parser = new JsonParser();
    public static JsonElement jsonTree = parser.parse(json.toString());
    public static JsonObject jsonObject;

    static {

        if (jsonTree.isJsonObject()) {
            jsonObject = jsonTree.getAsJsonObject();
        }

    }

    public static int tardisIds() {

        return jsonObject.get("tardisIds").getAsInt();
    }

    public static void main(String[] args) {
        System.out.println(tardisIds());
    }
}