/**
 * 会计类
 */
public class Accountant extends Worker {

    private static final String DEPT = "Accountant";

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
        super(name, age, salary, DEPT);
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
        int intNumber;
        try {
            intNumber = Integer.parseInt(number);
            if (intNumber < 0) {
                return "illegal";
            }
        } catch (NumberFormatException e) {
            return "illegal";
        }

        String[] digitGroup = initDigitGroup(number);
        return handleDigitGroup(digitGroup);

    }

    /**
     * 处理各段切分好的dig并返回结果
     */
    private String handleDigitGroup(String[] digGroup) {
        String[] digitUnit = new String[]{"Billion ", "Million ", "Thousand ", ""};
        StringBuilder words = new StringBuilder();
        for (int i = 0; i < digGroup.length; i++) {
            if (digGroup[i] != null) {
                words.append(handleThreeBitDigit(digGroup[i], digitUnit[i]));
            }
        }
        return words.toString().trim();
    }

    private String handleThreeBitDigit(String num, String unit) {
        if (Integer.parseInt(num) == 0) {
            return "";
        }
        String[] numToWords = new String[]{ // 基本数词表
                "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
                "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
                "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen",
                "Twenty", "", "", "", "", "", "", "", "", "", "Thirty", "", "", "",
                "", "", "", "", "", "", "Forty", "", "", "", "", "", "", "", "",
                "", "Fifty", "", "", "", "", "", "", "", "", "", "Sixty", "", "",
                "", "", "", "", "", "", "", "Seventy", "", "", "", "", "", "", "",
                "", "", "Eighty", "", "", "", "", "", "", "", "", "", "Ninety"};
        StringBuilder words = new StringBuilder();
        char[] chars = num.toCharArray();
        if (chars[0] != '0') {
            words.append(numToWords[chars[0] - '0'] + " " + "Hundred ");
        }
        int lastTwo = Integer.parseInt(num) % 100;
        if (lastTwo <= 20) {
            words.append(numToWords[lastTwo] + " ");
        } else {
            if (chars[1] != '0') {
                words.append(numToWords[(chars[1] - '0') * 10] + " ");
            }
            if (chars[2] != '0') {
                words.append(numToWords[chars[2] - '0'] + " ");
            }
        }
        return words.append(unit).toString();
    }

    /**
     * 将String每三个字符切为4段并返回
     */
    private String[] initDigitGroup(String number) {
        String[] digGroup = new String[4];
        int length = number.length();
        for (int i = 3; i >= 0; i--, length -= 3) {
            if (length - 3 > 0) {
                digGroup[i] = number.substring(length - 3, length);
            } else {
                digGroup[i] = number.substring(0, length);
                if (digGroup[i].length() == 1) {
                    digGroup[i] = "00" + digGroup[i];
                } else if (digGroup[i].length() == 2) {
                    digGroup[i] = "0" + digGroup[i];
                }
                break;
            }
        }
        return digGroup;
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
     * @param
     */
    public int checkPassword() {
        Boolean[] hasRequire = new Boolean[]{false, false, false};
        char currentChar = ' ';
        int repeat = 0;
        int operator = 0;
        int length = password.length();
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                hasRequire[0] = true;
            } else if (c >= 'a' && c <= 'z') {
                hasRequire[1] = true;
            } else if (c >= '0' && c <= '9') {
                hasRequire[2] = true;
            } else {
                operator++;
                length = judge(hasRequire, length);
                currentChar = ' ';
                repeat = 0;
                continue;
            }
            if (currentChar != password.charAt(i)) {
                repeat = 1;
                currentChar = password.charAt(i);
            } else {
                repeat++;
                if (repeat == 3) {
                    operator++;
                    length = judge(hasRequire, length);
                    currentChar = ' ';
                    repeat = 0;
                }
            }
        }
        for (int i = 0; i < hasRequire.length; i++) {
            if (!hasRequire[i]) {
                operator++;
                if (length < 8) {
                    length++;
                }
            }
        }
        if (length < 8) {
            operator += (8 - length);
        }
        return operator;
    }

    private int judge(Boolean[] hasRequire, int length) {
        for (int i = 0; i < hasRequire.length; i++) {
            if (!hasRequire[i]) {
                hasRequire[i] = true;
            }
        }
        if (length > 20) {
            length--;
        } else if (length < 8) {
            length++;
        }
        return length;
    }
}
