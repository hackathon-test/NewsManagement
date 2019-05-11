/**
 * 会计类
 */
public class Accountant extends Worker {

    private static final String DEPART = "Accountant";

    public String password;

    public Accountant() {

    }

    /**
     * 初始化会计
     *
     * @param name     姓名
     * @param age      年龄
     * @param salary   工资
     * @param password 密码
     */
    public Accountant(String name, int age, int salary, String password) {
        super(name, age, salary, DEPART);
        this.password = password;
    }

    /**
     * 数字转换
     * 随着公司业务的开展，国际性业务也有相应的拓宽，
     * 会计们需要一个自动将数字转换为英文显示的功能。
     * 编辑们希望有一种简约的方法能将数字直接转化为数字的英文显示。
     * <p>
     * 给定一个非负整数型输入，将数字转化成对应的英文显示，省略介词and
     * 正常输入为非负整数，且输入小于2^31-1;
     * 如果有非法输入（字母，负数，范围溢出等），返回illegal
     * <p>
     * 示例：
     * <p>
     * number: 2132866842
     * return: "Two Billion one Hundred Thirty Two Million Eight Hundred Sixty Six Thousand Eight Hundred Forty Two"
     * <p>
     * number：-1
     * return："illegal"
     *
     * @param number
     */
    public String numberToWords(String number) {
        return password;

    }

    /**
     * 检验密码
     * 由于会计身份的特殊性，对会计的密码安全有较高的要求，
     * 会计的密码需要由8-20位字符组成；
     * 至少包含一个小写字母，一个大写字母和一个数字，不允许出现特殊字符；
     * 同一字符不能连续出现三次 (比如 "...ccc..." 是不允许的, 但是 "...cc...c..." 可以)。
     * <p>
     * 如果密码符合要求，则返回0;
     * 如果密码不符合要求，则返回将该密码修改满足要求所需要的最小操作数n，插入、删除、修改均算一次操作。
     * <p>
     * 示例：
     * <p>
     * password: HelloWorld6
     * return: 0
     * <p>
     * password: HelloWorld
     * return: 1
     *
     * @param password
     */
    public int checkPassword() {
        return 0;

    }
}
