package classes;

import classes.models.Button;
import classes.services.FilesUtil;
import classes.services.XML_Parser;
import classes.services.XlsWriter;
import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static void parseIcons() {
        String icons = "D:\\Apex\\Search\\src\\staticresources\\SearchMapFrame\\icons";
        FilesUtil.listFilesForFolder(icons).forEach(file -> {
            String iconName = file.getName().split("\\.")[0];
            String path = XML_Parser.getSvgPath(file);
            System.out.println(iconName);
        });

    }
    public static void main(String... args) {
        parseIcons();
//        parceSalesforceMetadata();
    }

    private static void parceSalesforceMetadata() {
        List<String> names = XlsWriter.readColumn("D:\\Apex\\Projects\\Juniper\\ButtonsInfo.xls", "Name");
        List<Button> buttons = new ArrayList<>();

        String layouts = "D:\\Apex\\Projects\\Juniper2\\src\\layouts";
        String profiles = "D:\\Apex\\Projects\\Juniper2\\src\\permissionsets";
        Map<String, List<String>> buttonMap = new HashMap<>();
        Map<String, List<String>> layoutMap = new HashMap<>();
        FilesUtil.listFilesForFolder(layouts).forEach(file -> {
            String layoutName = file.getName().split("\\.")[0];
            XML_Parser.getButtons(file).forEach(but -> {
                if (!layoutMap.containsKey(but)) {
                    layoutMap.put(but, new ArrayList<>());
                }
                layoutMap.get(but).add(layoutName);
            });
        });

        Map<String, List<String>> profilesMap = new HashMap<>();
        FilesUtil.listFilesForFolder(profiles).forEach(file -> {
            String name = file.getName().split("\\.")[0];
            XML_Parser.parseLayout(file).forEach(layout -> {
                if (!profilesMap.containsKey(layout)) {
                    profilesMap.put(layout, new ArrayList<>());
                }
                profilesMap.get(layout).add(name);
            });
        });
        Map<String, List<String>> butProfilesMap = new HashMap<>();
        layoutMap.forEach((k, value) -> {
            List<String> prf = new ArrayList<>();
            value.forEach(layout -> {
                if (profilesMap.containsKey(layout)) {
                    prf.addAll(profilesMap.get(layout));
                }
            });
            if (!prf.isEmpty()) {
                if (!butProfilesMap.containsKey(k)) {
                    butProfilesMap.put(k, new ArrayList<>());
                }
                butProfilesMap.get(k).addAll(prf);
            }

        });

        names.forEach(name -> {
                Button button = new Button();
                button.setName(name);
            if (butProfilesMap.containsKey(name)) {
                button.setProfiles(StringUtils.join(butProfilesMap.get(name), ", "));
            } else {
                button.setProfiles("");
            }
            buttons.add(button);
        });
        XlsWriter.writeFiles(buttons);
    }
}
