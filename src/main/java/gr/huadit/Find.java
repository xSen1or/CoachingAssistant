package gr.huadit;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.*;


public class Find {
    /*
     *   This class was found in the Oracle Documentation
     *   https://docs.oracle.com/javase/tutorial/essential/io/find.html
     *   Added some minor modifications so it will work with the XMLReader and GUI
     * */

    public static class Finder extends SimpleFileVisitor<Path> {
        private List<String> filenames = new ArrayList<>();
        private Path foundPath = null;
        private final PathMatcher matcher;
        private int numMatches = 0;

        public Finder(String pattern) {
            matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
        }

        // Compares the glob pattern against
        // the file or directory name.
        void find(Path file) {
            Path name = file.getFileName();
            if (name != null && matcher.matches(name)) {
                numMatches++;
                foundPath = file.toAbsolutePath();
                filenames.add(foundPath.toString());
                System.out.println();
            }
        }

        public String getPath() {
            return foundPath == null ? null : foundPath.toString();
        }

        // Prints the total number of
        // matches to standard out.
        void done() {
            System.out.println("Matched: "
                    + numMatches);
        }

        // Invoke the pattern matching
        // method on each file.
        @Override
        public FileVisitResult visitFile(Path file,
                                         BasicFileAttributes attrs) {
            find(file);
            return CONTINUE;
        }


        public int getNumMatches() {
            return numMatches;
        }


        // Invoke the pattern matching
        // method on each directory.
        @Override
        public FileVisitResult preVisitDirectory(Path dir,
                                                 BasicFileAttributes attrs) {
            find(dir);
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file,
                                               IOException exc) {
            return CONTINUE;
        }

        public List<String> getFilenames() {
            return filenames;
        }

        public void getFiles(String pattern) {

        }
    }
}