package io.github.inbanithi.jastra.assembler.core;

import java.util.Map;

public interface Resolvable {

    public void resolve(Map<String, Integer> symbols);

}
