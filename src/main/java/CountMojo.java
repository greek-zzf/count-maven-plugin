import com.google.common.io.Files;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 统计代码行数的实现类
 *
 * @author Zhaofeng Zhou
 * @date 3/10/2021 下午4:06
 */
@Mojo(name = "countCode")
public class CountMojo extends AbstractMojo {

    private static final List<String> DEFAULT_TYPE = Arrays.asList("java");

    @Parameter(readonly = true, required = true, defaultValue = "${project.build.sourceDirectory}")
    private File sourceDirectory;


    @Parameter(readonly = true, required = true, defaultValue = "${project.build.testSourceDirectory}")
    private File testSourceDirectory;


    /**
     * 需要统计代码行数的文件类型
     *
     * @parameter
     */
    @Parameter(property = "count.file.type")
    private List<String> includes;


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        loadFileTypeConfig();
        countAndPrintResult(sourceDirectory);
        countAndPrintResult(testSourceDirectory);
    }


    private void countAndPrintResult(File directory) {
        AtomicInteger fileNum = new AtomicInteger();
        List<File> fileList = getAllFile(new ArrayList<>(), directory);

        long linesNum = fileList.stream()
                .filter(this::isCountedFile)
                .peek(file -> fileNum.getAndIncrement())
                .mapToLong(this::countLine)
                .sum();

        getLog().info(directory + ": " + linesNum + " line of code in " + fileNum + " files");
    }

    private List<File> getAllFile(List<File> fileList, File directory) {

        for (File file : directory.listFiles()) {
            if (file.isFile()) {
                fileList.add(file);
            } else {
                getAllFile(fileList, file);
            }
        }
        return fileList;
    }

    private long countLine(File file) {
        try {
            return java.nio.file.Files.lines(file.toPath()).count();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private boolean isCountedFile(File file) {
        String name = Files.getFileExtension(file.getName());
        return includes.contains(name);
    }

    private void loadFileTypeConfig() {
        if (includes == null || includes.isEmpty()) {
            includes = DEFAULT_TYPE;
        }
    }
}
