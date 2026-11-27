package io.github.inbanithi.jastra.core;

import io.github.inbanithi.jastra.loader.ApplicationModuleLoader;
import io.github.inbanithi.jastra.loader.ModuleLoader;
import io.github.inbanithi.jastra.runtime.JastraRuntime;
import io.github.inbanithi.jastra.runtime.Runtime;
import io.github.inbanithi.jastra.specification.vm.Frame;
import io.github.inbanithi.jastra.specification.vm.Vm;
import io.github.inbanithi.jastra.specification.core.Module;

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
        this.modules = new HashMap<>();
        this.mainModule = loadModule(name);
        modules.put(name, mainModule);
    }

    public Module loadModule(String name){
        if(modules.containsKey(name)){
            return modules.get(name);
        }
        Module module = loader.load(name, root);
        modules.put(name, module);
        return module;
    }

    @Override
    public void execute(String[] strings) {
        Runtime runtime = new JastraRuntime();
        Frame frame = new Frame(mainModule, null, mainModule.entry);
        this.callStack.push(frame);
        runtime.execute(this);
    }
}
