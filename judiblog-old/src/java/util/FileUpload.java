/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.fileupload.FileItemStream;

/**
 *
 * @author Admin
 */
public class FileUpload {

    public static boolean processFile(String filePath, FileItemStream itemStream, String imageName, String fileExtension) {
        try {
            File f = new File(filePath);
            if (f.exists()) {
                f.mkdir();
            }
            File saveFile = new File(f.getAbsolutePath() + File.separator + imageName + "." + fileExtension);
            try (FileOutputStream fos = new FileOutputStream(saveFile)) {
                InputStream is = itemStream.openStream();
                int x = 0;
                byte[] b = new byte[1024];
                while ((x = is.read(b)) != -1) {
                    fos.write(b, 0, x);
                }
                fos.flush();
                fos.close();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
