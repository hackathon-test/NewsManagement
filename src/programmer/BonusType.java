package programmer;

import java.util.HashMap;
import java.util.Map;

/**
 * 奖金计算类型
 * <p>
 *
 * @author Shenmiu
 * @date 2019/05/11
 */
public enum BonusType {

    /**
     * 开发
     */
    DEVELOP("Develop", 0.2, 100, 500),
    /**
     * 测试
     */
    TEST("Test", 0.15, 150, 1000),
    /**
     * UI 设计
     */
    UI("UI", 0.25, 50, 300);

    private static Map<String, BonusType> nameMaps = new HashMap<>(3);

    static {
        for (BonusType bonusType : BonusType.values()) {
            nameMaps.put(bonusType.type, bonusType);
        }
    }

    /**
     * 类型
     */
    private String type;
    /**
     * 每月奖金占基本工资的比例
     */
    private double bonusPercent;
    /**
     * 加班一次补贴
     */
    private int allowance;
    /**
     * 加班补贴上限
     */
    private int limit;

    BonusType(String type, double bonusPercent, int allowance, int limit) {
        this.type = type;
        this.bonusPercent = bonusPercent;
        this.allowance = allowance;
        this.limit = limit;
    }

    public static BonusType fromString(String str) {
        return nameMaps.get(str);
    }

    /**
     * 计算 bonus
     *
     * @param salary   月薪
     * @param overtime 加班次数
     * @return 当月奖金
     */
    public double bonus(int salary, int overtime) {
        double bonus = salary * bonusPercent;
        int countAllowance = overtime * allowance;
        bonus = countAllowance > limit ? bonus + limit : bonus + countAllowance;
        return bonus;
    }
}
