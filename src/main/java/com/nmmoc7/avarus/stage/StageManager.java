package com.nmmoc7.avarus.stage;

/**
 * @author DustW
 **/
public class StageManager {
    public enum Stage {
        /** 一开始的阶段，暖暖的，很贴心 */
        DIRT(0),
        /** 打败末影龙后的阶段，很好 */
        AQUA(1);

        int index;

        Stage(int index) {
            this.index = index;
        }

        public boolean dirt() {
            return this.index <= DIRT.index;
        }

        public boolean aqua() {
            return this.index <= AQUA.index;
        }
    }

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    // TODO 弄成世界数据，保存在主世界
    public static StageManager getManager() {
        return new StageManager();
    }
}
