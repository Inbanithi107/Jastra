package io.github.inbanithi.jastra.loader;

import io.github.inbanithi.jastra.core.Module;

import java.nio.file.Path;

public interface ModuleLoader {

    public Module load(String name, Path path);

}
