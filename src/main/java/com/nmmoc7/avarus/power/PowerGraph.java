package com.nmmoc7.avarus.power;

import net.minecraft.core.BlockPos;

import java.util.*;

/**
 * @author DustW
 **/
public class PowerGraph {
    final Map<BlockPos, PowerNode> posMap = new LinkedHashMap<>();
    final Map<BlockPos, LinkedList<BlockPos>> powerNodes = new HashMap<>();

    /** 添加一个点到图中 */
    public void addNode(PowerNode node) {
        if (posMap.containsKey(node.pos)) {
            throw new IllegalArgumentException("Node already exists");
        }

        posMap.put(node.pos, node);
        powerNodes.put(node.pos, new LinkedList<>());
    }

    /** 添加一个边到无向图中 */
    public void addEdge(PowerNode from, PowerNode to) {
        powerNodes.get(from.pos).add(to.pos);
        powerNodes.get(to.pos).add(from.pos);
    }

    /** 获取一个点 */
    public PowerNode getNode(BlockPos pos) {
        return posMap.get(pos);
    }

    /** 取消所有的 visited */
    public void clearVisited() {
        for (PowerNode node : posMap.values()) {
            node.visited = false;
        }
    }

    /** 深度优先寻找 SEND 模式的节点 */
    public void findSendNodes(PowerNode node, List<PowerNode> nodes) {
        node.visited = true;

        if (node.mode.send()) {
            nodes.add(node);
        }

        for (BlockPos pos : powerNodes.get(node.pos)) {
            PowerNode n = posMap.get(pos);

            if (!n.visited) {
                findSendNodes(n, nodes);
            }
        }
    }

    /** 深度优先寻找 SEND 以及 BOTH 模式的节点 */
    public void findBothNodes(PowerNode node, List<PowerNode> nodes) {
        node.visited = true;

        if (node.mode.send() || node.mode.both()) {
            nodes.add(node);
        }

        for (BlockPos pos : powerNodes.get(node.pos)) {
            PowerNode n = posMap.get(pos);

            if (!n.visited) {
                findBothNodes(n, nodes);
            }
        }
    }
}
