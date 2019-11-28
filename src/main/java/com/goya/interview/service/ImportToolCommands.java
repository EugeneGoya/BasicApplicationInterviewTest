package com.goya.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ImportToolCommands {
    @Autowired
    private ToolService service;

    @ShellMethod("Import data from users, cities and countries files into database")
    public void importData(
            @ShellOption(help = "Provide path to users CSV file") String user,
            @ShellOption(help = "Provide path to cities CSV file") String city,
            @ShellOption(help = "Provide path to countries CSV file") String country)
    {
        service.importData(user, city, country);
    }
}
