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
        System.out.println("所经过的最大欧式距离的平方: " + solution.robotSim(commands, obstacles));
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstacleSet.add(Arrays.toString(obstacle));
        }

        int maxSquaredDistance = 0;
        Robot robot = new Robot(new int[] {0, 0}, 0);
        for (int i=0; i<commands.length; i++) {
            if (commands[i] == -1 || commands[i] == -2) {
                robot.changeDirection(commands[i]);
            } else {
                robot.walk(commands[i], obstacleSet);
                maxSquaredDistance = Math.max(maxSquaredDistance, robot.calcSquaredDistance());
            }
        }
        return maxSquaredDistance;
    }

    class Robot {
        private int[] startPos;
        private int[] curPos;
        private final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        private int curDirectionIndex = 0;
        private final int RIGHT = -1;
        private final int LEFT = -2;

        public Robot() {}
        public Robot(int[] pos, int dirIndex) {
            this.setStartPos(pos);
            this.setCurPos(pos);
            this.setCurDirectionIndex(dirIndex);
        }

        public int[] getStartPos() {
            return startPos;
        }

        public int[] getCurPos() {
            return curPos;
        }

        public int getCurDirectionIndex() {
            return curDirectionIndex;
        }

        public void setStartPos(int[] startPos) {
            this.startPos = Arrays.copyOf(startPos, 2);
        }

        public void setCurPos(int[] curPos) {
            this.curPos = curPos;
        }

        public void setCurDirectionIndex(int curDirectionIndex) {
            this.curDirectionIndex = curDirectionIndex;
        }

        /**
         * 转向
         * @param code 转向: -1 为右转, -2 为左转
         */
        public void changeDirection(int code) {
            if (code == LEFT) {
                this.setCurDirectionIndex((curDirectionIndex + 3) % 4);
            } else if (code == RIGHT) {
                this.setCurDirectionIndex((curDirectionIndex + 1) % 4);
            } else {
                return;
            }
        }

        /**
         * 行走
         * @param step 行走步数
         * @param obstacleSet 障碍集合，遇到障碍就停下
         */
        public void walk(int step, Set<String> obstacleSet) {
            int[] increment = this.directions[this.curDirectionIndex];
            for (int i=0; i<step; i++) {
                if (this.encounterObstacle(increment, obstacleSet)) {
                    System.out.println("遇到了障碍, 当前位置: " + Arrays.toString(this.curPos));
                    break;
                }
                this.curPos[0] += increment[0];
                this.curPos[1] += increment[1];
                System.out.println("当前位置: " + Arrays.toString(this.curPos) + ", 当前方向: " + this.curDirectionIndex);
            }
        }

        /**
         * 某个方向上是否遇到障碍
         * @param direction 方向
         * @param obstacleSet 障碍集合
         * @return boolean 是否遇到阻碍
         */
        public boolean encounterObstacle(int[] direction, Set<String> obstacleSet) {
            int[] nextPos = {this.curPos[0] + direction[0], this.curPos[1] + direction[1]};
            return obstacleSet.contains(Arrays.toString(nextPos));
        }

        /**
         * 计算距离起始点的最大欧氏距离的平方
         * @return
         */
        public int calcSquaredDistance() {
            return (int)(Math.pow(this.curPos[0] - this.startPos[0], 2) + Math.pow(this.curPos[1] - this.startPos[1], 2));
        }
    }
}
