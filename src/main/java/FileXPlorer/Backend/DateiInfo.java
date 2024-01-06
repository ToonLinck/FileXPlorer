package FileXPlorer.Backend;

import java.io.File;
import java.util.Date;

public class DateiInfo {
    public String trueDateiPfad;
    public String dateiName;
    public String dateiTyp;
    public long dateigröße;
    public long dateiAenderungsdatum;
    public static DateiInfo ConvertFileToInfo(File file){
        DateiInfo temp = new DateiInfo();
        temp.trueDateiPfad = file.getPath();
        temp.dateiName = file.getName();
        temp.dateiTyp = getFileExtension(file);
        temp.dateigröße = file.getTotalSpace();
        temp.dateiAenderungsdatum = file.lastModified();
        return temp;
    }

    public static File convertInfoToFile(DateiInfo info){
        return new File(info.trueDateiPfad);
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }
}
