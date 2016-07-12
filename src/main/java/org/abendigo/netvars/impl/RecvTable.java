package org.abendigo.netvars.impl;

import static org.abendigo.OffsetManager.process;

/**
 * Created by Jonathan on 11/16/2015.
 */
public final class RecvTable {

    private int base;

    public RecvTable setBase(int base) {
        this.base = base;
        return this;
    }

    public int propForId(int id) {
        return process().readInt(base) + (id * 0x3C);
    }

    public String tableName() {
        return process().readString(process().readInt(base + 0xC), 32);
    }

    public int propCount() {
        return process().readInt(base + 0x4);
    }

    public boolean readable() {
        return process().canRead(base, 0x10);
    }


}
