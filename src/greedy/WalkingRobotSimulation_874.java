package greedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 874. 模拟行走机器人
 *
 * 机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令：
 *     -2：向左转 90 度
 *     -1：向右转 90 度
 *     1 <= x <= 9：向前移动 x 个单位长度
 * 在网格上有一些格子被视为障碍物。
 * 第 i 个障碍物位于网格点  (obstacles[i][0], obstacles[i][1])
 * 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。
 * 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。
 *
 * 示例 1：
 * 输入: commands = [4,-1,3], obstacles = []
 * 输出: 25
 * 解释: 机器人将会到达 (3, 4)
 *
 * 示例 2：
 * 输入: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * 输出: 65
 * 解释: 机器人在左转走到 (1, 8) 之前将被困在 (1, 4) 处
 *
 * 提示：
 *     0 <= commands.length <= 10000
 *     0 <= obstacles.length <= 10000
 *     -30000 <= obstacle[i][0] <= 30000
 *     -30000 <= obstacle[i][1] <= 30000
 *     答案保证小于 2 ^ 31
 */
public class WalkingRobotSimulation_874 {
    public static void main(String[] args) {
        WalkingRobotSimulation_874 solution = new WalkingRobotSimulation_874();
        int[] commands = {4,-1,4,-2,4};
        int[][] obstacles = {{2, 4}};
        int res = solution.robotSim(commands, obstacles);
        System.out.println(res);
    }

    /**
     * 面向过程方式进行机器人行走模拟
     * @param commands
     * @param obstacles
     * @return
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstacleSet.add(Arrays.toString(obstacle));
        }

        int[] pos = {0, 0};
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int curDirIndex = 0;
        int res = 0;
        for (int command : commands) {
            if (command == -1) {
                curDirIndex = (curDirIndex + 1) % 4;
            } else if (command == -2) {
                curDirIndex = (curDirIndex + 3) % 4;
            } else {
                for (int i=0; i<command; i++) {
                    int[] temp = new int[2];
                    temp[0] = pos[0] + directions[curDirIndex][0];
                    temp[1] = pos[1] + directions[curDirIndex][1];
                    if (obstacleSet.contains(Arrays.toString(temp))) {
                        break;
                    }
                    pos[0] = temp[0];
                    pos[1] = temp[1];
                    res = Math.max(res, (int)(Math.pow(pos[0], 2) + Math.pow(pos[1], 2)));
                }
            }
        }
        return res;
    }
}
