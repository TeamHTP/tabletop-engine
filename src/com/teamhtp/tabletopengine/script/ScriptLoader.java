package com.teamhtp.tabletopengine.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public class ScriptLoader {

    private static Logger LOGGER = Logger.getLogger(ScriptLoader.class.getName());

    private ScriptEngineManager scriptEngineManager;

    private ScriptEngine scriptEngine;

    public ScriptLoader() {
        scriptEngineManager = new ScriptEngineManager();
        scriptEngine = scriptEngineManager.getEngineByName("nashorn");
    }

    public void loadScripts(Path path) throws IOException {
        Files.walk(path, FileVisitOption.FOLLOW_LINKS)
                .filter(p -> p.toString().toLowerCase().endsWith(".js"))
                .forEach((p) -> {
            try {
                LOGGER.info("Loading " + p.toString());
                scriptEngine.eval(new FileReader(p.toFile()));
            } catch (ScriptException | FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

}
