package io.github.inbanithi.jastra.specification.core;

public enum ConstantType {

    INTEGER(1),
    STRING(2),
    FLOAT(3);

    private final int tag;

    ConstantType(int tag){
        this.tag = tag;
    }

    public static ConstantType from(int tag){
        for(ConstantType type : ConstantType.values()){
            if(type.tag==tag){
                return type;
            }
        }
        return null;
    }

    public int getTag() {
        return tag;
    }
}
