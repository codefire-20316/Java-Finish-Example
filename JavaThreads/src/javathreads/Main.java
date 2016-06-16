/*
 * Copyright (C) 2016 CodeFireUA <edu@codefire.com.ua>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package javathreads;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CodeFireUA <edu@codefire.com.ua>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File storageFile = new File("Downloads");

        if (!storageFile.exists()) {
            storageFile.mkdir();
        }

        List<URL> urls = new ArrayList<>();

        try (Scanner scan = new Scanner(new URL("http://www.ex.ua/playlist/9426590.m3u").openStream())) {
            while (scan.hasNextLine()) {
                urls.add(new URL(scan.nextLine()));
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.printf("Download %d track's\n", urls.size());
        
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (URL url : urls) {
            DownloadFileTask downloadFileTask = new DownloadFileTask(url, storageFile);

            threadPool.execute(downloadFileTask);
//            Thread thread = new Thread(downloadFileTask);
//            thread.start();
        }

        threadPool.shutdown();
    }

}
