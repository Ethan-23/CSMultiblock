package org.evasive.me.cSMultiblock.preview;

import java.util.LinkedList;
import java.util.Queue;

public class PreviewIds {

    private int nextId = 2147483647;
    private final Queue<Integer> recycledIds = new LinkedList<>();

    public int getUniqueAnimationId() {
        return recycledIds.isEmpty() ? nextId-- : recycledIds.poll();
    }

    public void releaseAnimationId(int id) {
        recycledIds.offer(id);
    }

}
