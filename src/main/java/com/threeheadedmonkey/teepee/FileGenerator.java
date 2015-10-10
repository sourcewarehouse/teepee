package com.threeheadedmonkey.teepee;

import com.threeheadedmonkey.teepee.entity.Consolidator;
import com.threeheadedmonkey.teepee.entity.Item;
import com.threeheadedmonkey.teepee.io.FileReader;
import com.threeheadedmonkey.teepee.web.DailyTasksDecorator;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;

/**
 * Take a Taskpaper file and generate an HTML file
 */
public class FileGenerator {

    private File taskpaperFile;
    private File destinationDirectory;
    private String label;

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: java -jar teepee.jar <file-to-generate-with> <destination-directory>");
            System.exit(1);
        }
        String taskpaperFile = args[0];
        String destinationDirectory = args[1];

        FileGenerator self = new FileGenerator(taskpaperFile, destinationDirectory);
        if (!self.valid()) {
            System.exit(2);
        }
        System.out.print(self.logStart());
        self.generate();
        System.out.println(self.logFinish());
    }

    public FileGenerator(String taskpaperFile, String destinationDirectory) {
        this.taskpaperFile = new File(taskpaperFile);
        this.destinationDirectory = new File(destinationDirectory);
    }

    private boolean valid() {
        if (!taskpaperFile.exists()) {
            System.err.println("Taskpaper file to process not found");
            return false;
        }
        if (!destinationDirectory.exists()) {
            System.err.println("Destination directory not found");
            return false;
        }
        if (!destinationDirectory.isDirectory()) {
            System.err.println("Destination directory is not a directory");
            return false;
        }
        return true;
    }

    private String logStart() {
        return "Generating Taskpaper " + taskpaperFile + " as HTML at " + destinationDirectory + " ...";
    }

    private String logFinish() {
        return " done.";
    }

    private void generate() throws IOException {

        this.label = taskpaperFile.getName().substring(0, taskpaperFile.getName().lastIndexOf("."));

        Collection<Item> items = new FileReader().read(new java.io.FileReader(taskpaperFile));
        Collection<Item> consolidatedItems = new Consolidator(items).consolidate();
        DailyTasksDecorator dailyTasks = new DailyTasksDecorator(consolidatedItems);

        String outputFileName = label.replaceAll(" ", "-").toLowerCase() + ".html";
        File outputFile = new File(destinationDirectory, outputFileName);
        Writer output = new FileWriter(outputFile);
        runVelocity(dailyTasks, output);
        output.flush();
        output.close();
    }

    private void runVelocity(DailyTasksDecorator dailyTasks, Writer output) {

        Properties p = new Properties();
        p.setProperty("resource.loader", "classpath");
        p.setProperty("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        Velocity.init(p);

        VelocityContext context = new VelocityContext();
        context.put("date", new DateTool());
        context.put("key", label);
        context.put("tasks", dailyTasks);
        context.put("timestamp", new DateTime().toString("dd MMM yyyy HH:mm"));

        Template template = Velocity.getTemplate("tasks.vm");

        template.merge(context, output);
    }

    public static class DateTool {

        public String format(String pattern, Date date) {
            return new SimpleDateFormat(pattern).format(date);
        }
    }
}