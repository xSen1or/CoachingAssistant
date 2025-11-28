package gr.huadit;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.Arrays;

import static java.nio.file.FileVisitResult.*;


public class Find {

    /*
    *   This class was found in the Oracle Documentation
    *   https://docs.oracle.com/javase/tutorial/essential/io/find.html
    * */



    public static class Finder extends SimpleFileVisitor<Path> {


        private Path foundPath = null;
        private final PathMatcher matcher;
//        private final PathMatcher[] pathMatcher;
        private int numMatches = 0;

        public Finder(String pattern) {
            matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
        }

//        public Finder(PathMatcher[] matcher,  String... Pattern) {
//            this.matcher = null;
//            this.pathMatcher = Arrays.stream(Pattern)
//                    .map(p -> FileSystems.getDefault().getPathMatcher("glob:" + p))
//                    .toArray(PathMatcher[]::new);
//        }

        // Compares the glob pattern against
        // the file or directory name.
        void find(Path file) {
            Path name = file.getFileName();
            if (name != null && matcher.matches(name)) {
                numMatches++;
                foundPath = file.toAbsolutePath();
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
            System.err.println(exc.getMessage());
            return CONTINUE;
        }
    }
}