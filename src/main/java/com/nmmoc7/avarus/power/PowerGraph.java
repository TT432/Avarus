package com.nmmoc7.avarus.power;

import net.minecraft.core.BlockPos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author DustW
 **/
public class PowerGraph {
    final Map<BlockPos, PowerNode> posMap = new HashMap<>();
    final Map<PowerNode, LinkedList<PowerNode>> powerNodes = new HashMap<>();

    /** 添加一个点到图中 */
    public void addNode(PowerNode node) {
        posMap.put(node.pos, node);
        powerNodes.put(node, new LinkedList<>());
    }

    /** 添加一个边到无向图中 */
    public void addEdge(PowerNode from, PowerNode to) {
        powerNodes.get(from).add(to);
        powerNodes.get(to).add(from);
    }

    /** 获取一个点 */
    public PowerNode getNode(BlockPos pos) {
        return posMap.get(pos);
    }

    /** 取消所有的 visited */
    public void clearVisited() {
        for (PowerNode node : powerNodes.keySet()) {
            node.visited = false;
        }
    }

    /** 深度优先寻找 SEND 模式的节点 */
    public void findSendNodes(PowerNode node, List<PowerNode> nodes) {
        node.visited = true;

        if (node.mode.send()) {
            nodes.add(node);
        }

        for (PowerNode n : powerNodes.get(node)) {
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

        for (PowerNode n : powerNodes.get(node)) {
            if (!n.visited) {
                findBothNodes(n, nodes);
            }
        }
    }
}
