package me.aurel2108.freeze;

/* Download.java file from http://respawner.fr/blog/index.php?post/2008/09/07/Telecharger-un-fichier-depuis-une-URL-avec-Java
 * By Guillaume Mazoyer and adiGuba. Edited by me (aurel2108)
 */

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Download
{
    public static boolean getFile(String host, String dest)
    {
        InputStream input = null;
        FileOutputStream writeFile = null;

        try
        {
            URL url = new URL(host);
            URLConnection connection = url.openConnection();
            int fileLength = connection.getContentLength();

            if (fileLength == -1)
            {
                System.out.println("Invalid URL or file.");
                return false;
            }

            input = connection.getInputStream();
            String fileName = url.getFile().substring(url.getFile().lastIndexOf('/') + 1);
            writeFile = new FileOutputStream(dest + fileName);
            byte[] buffer = new byte[1024];
            int read;

            while ((read = input.read(buffer)) > 0)
                writeFile.write(buffer, 0, read);
            writeFile.flush();
        }
        catch (Exception e)
        {
            return false;
        }
        finally
        {
            try
            {
                writeFile.close();
                input.close();
            }
            catch (Exception e)
            {
                return false;
            }
        }
        
        return true;
    }

    public static void main()
    {
           System.out.println("You must give the URL of the file to download.");
           return;
    }
}