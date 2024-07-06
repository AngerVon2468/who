package wiiu.mavity.who.util.data;

import com.google.gson.Gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
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

    public static void main(String[] args) throws IOException {
        what();
    }

    public static void what() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(tardisDataFile));

        Gson gson = new Gson();
        Object json = gson.fromJson(bufferedReader, Object.class);

        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(json.toString());

        if (jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            System.out.println(jsonObject.get("tardisIds"));
        }
    }

    /*
    public static int tardisIds() {

        return jsonObject == null ? 0 : jsonObject.get("tardisIds").getAsInt();
    }
    */
}