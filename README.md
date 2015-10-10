# Teepee - Generate a HTML dashboard from a Taskpaper file

## Description

A Java application which generates a HTML file with a prioritised list of tasks
from a Taskpaper-formatted file. It groups tasks into Overdue, Today, Tomorrow
and then the rest.

The idea was to run the script, publish the HTML to a web server and provide a
sort of dashboard where you could view your current tasks (which were managed
through Taskpaper for iPhone and Dropbox-syncing). Then using [Guard] or
similar the generation and publish would be re-run on each saved change to the
file.


## Limitations

There are some limitations to the script. Although it generates the HTML, the
grouping it does ignores Projects. So you can end up with a big list of
seemingly random tasks to do (Projects provide handy context for to-do items).


## Running the app

The Java app is structured as a [Maven] project.

Running:

   ```sh
      mvn install
   ```
   
will compile and package a *jar* file of the application.

Then to run the script:

   ```sh
      java -jar java -jar teepee.jar <taskpaper_file> <destination_dir>
   ```


[Guard]: https://github.com/guard/guard
