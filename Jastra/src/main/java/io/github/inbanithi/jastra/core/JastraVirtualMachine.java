package io.github.inbanithi.jastra.core;

import io.github.inbanithi.jastra.loader.ApplicationModuleLoader;
import io.github.inbanithi.jastra.loader.ModuleLoader;
import io.github.inbanithi.jastra.specification.vm.Vm;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class JastraVirtualMachine extends Vm {

    private final Map<String, Module> modules;

    private final Module mainModule;

    private final ModuleLoader loader;

    private final Path root;

    public JastraVirtualMachine(String name) {
        root = Paths.get("").normalize().toAbsolutePath();
        this.loader = new ApplicationModuleLoader();
        this.mainModule = loadModule(name);
        this.modules = new HashMap<>();
        modules.put(name, mainModule);
    }

    private Module loadModule(String name){
        return loader.load(name, root);
    }

    @Override
    public void execute(String[] strings) {

    }
}
