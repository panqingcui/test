package cjy.file;

import java.io.IOException;
import java.util.List;

import name.pachler.nio.file.ClosedWatchServiceException;
import name.pachler.nio.file.FileSystems;
import name.pachler.nio.file.Path;
import name.pachler.nio.file.Paths;
import name.pachler.nio.file.StandardWatchEventKind;
import name.pachler.nio.file.WatchEvent;
import name.pachler.nio.file.WatchKey;
import name.pachler.nio.file.WatchService;

public class FileWatchForLinux {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        String tempDir = System.getProperty("java.io.tmpdir");
        System.out.println("system 路径：" + System.getProperty("java.io.tmpdir"));
        // String tempDir = "f:/test";
        Path path = Paths.get(tempDir);
        // Path path1 = Paths.get(tempDir);
        System.out.println("using tempdir: " + path.toString());
        WatchKey key = null;
        WatchKey key1 = null;
        try {
            key = path.register(watchService, StandardWatchEventKind.ENTRY_CREATE, StandardWatchEventKind.ENTRY_DELETE,
                    StandardWatchEventKind.ENTRY_MODIFY);
            // key1 = path1.register(watchService, StandardWatchEventKind.ENTRY_CREATE,
            // StandardWatchEventKind.ENTRY_DELETE, StandardWatchEventKind.ENTRY_MODIFY);
        } catch (UnsupportedOperationException uox) {
            System.err.println("file watching not supported!");
            // handle this error here
        } catch (IOException iox) {
            System.err.println("I/O errors");
            // handle this error here
        }
        // typically, real world applications will run this loop in a
        // separate thread and signal directory changes to another thread
        // (often, this will be Swing's event dispatcher thread; use
        // SwingUtilities.invokeLater())
        for (;;) {
            // take() will block until a file has been created/deleted
            WatchKey signalledKey = null;
            try {
                signalledKey = watchService.take();
            } catch (InterruptedException ix) {
                // we'll ignore being interrupted
                continue;
            } catch (ClosedWatchServiceException cwse) {
                // other thread closed watch service
                System.out.println("watch service closed, terminating.");
                break;
            }
            // get list of events from key
            List<WatchEvent<?>> list = signalledKey.pollEvents();
            // VERY IMPORTANT! call reset() AFTER pollEvents() to allow the
            // key to be reported again by the watch service
            signalledKey.reset();
            // we'll simply print what has happened; real applications
            // will do something more sensible here
            for (WatchEvent e : list) {
                String message = "";
                Path context = (Path) e.context();
                String filename = context.toString();
                if (e.kind() == StandardWatchEventKind.ENTRY_CREATE) {
                    message = filename + " ----------created";
                } else if (e.kind() == StandardWatchEventKind.ENTRY_DELETE) {
                    message = filename + " ----------deleted";
                } else if (e.kind() == StandardWatchEventKind.ENTRY_MODIFY) {
                    message = filename + " ----------modify";
                } else if (e.kind() == StandardWatchEventKind.OVERFLOW) {
                    message = "OVERFLOW: more changes happened than we could retreive";
                }
                if (filename.endsWith("swp") || filename.endsWith("swpx")) {
                    continue;
                } else {
                    System.out.println(message);
                }
            }
        }
    }
}
