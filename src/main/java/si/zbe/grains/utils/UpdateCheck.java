package si.zbe.grains.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Map;
import ca.fuzzlesoft.JsonParse;
import si.zbe.grains.Main;


public class UpdateCheck {

    public static String version;

    public static void init() {
        try {
            version = getVersion();
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkForUpdates();
    }

    private static void checkForUpdates() {
        if (version == null) {
            Main.plugin.getLogger().severe(LanguageManager.get("plugin.update.fail"));
            return;
        }

        // Parse version
        String[] versionSplit = version.split("\\.");
        int major = Integer.parseInt(versionSplit[0]);
        int minor = Integer.parseInt(versionSplit[1]);
        int patch = Integer.parseInt(versionSplit[2]);

        // Parse current version
        String[] currentVersionSplit = Main.plugin.getDescription().getVersion().split("\\.");
        int currentMajor = Integer.parseInt(currentVersionSplit[0]);
        int currentMinor = Integer.parseInt(currentVersionSplit[1]);
        int currentPatch = Integer.parseInt(currentVersionSplit[2]);

        // Compare versions
        if (major > currentMajor) {
            Main.plugin.getLogger().info(MessageFormat.format(LanguageManager.get("plugin.update.major"), version));
        } else if (major == currentMajor && minor > currentMinor) {
            Main.plugin.getLogger().info(MessageFormat.format(LanguageManager.get("plugin.update.minor"), version));
        } else if (major == currentMajor && minor == currentMinor && patch > currentPatch) {
            Main.plugin.getLogger().info(MessageFormat.format(LanguageManager.get("plugin.update.patch"), version));
        } else {
            Main.plugin.getLogger().info(LanguageManager.get("plugin.update.latest"));
        }
    }

    private static String getVersion() throws IOException {
        URL url = new URL("https://api.github.com/repos/pablo3x6/Grains/releases/latest");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        int status = con.getResponseCode();

        Reader streamReader = null;

        if (status > 299) {
            streamReader = new InputStreamReader(con.getErrorStream());
        } else {
            streamReader = new InputStreamReader(con.getInputStream());
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();

        Map<String, Object> map = JsonParse.map(content.toString());

        return map.get("tag_name").toString();
    }
}
