import programmer.BonusType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * 程序员
 */
public class Programmer extends Worker {

    private static final String DEPT = "Programmer";

    private static final DecimalFormat SALARY_DF = new DecimalFormat("#,###.00");

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * 语言
     */
    public String language;
    /**
     * 程序员类型
     */
    public String type;

    public Programmer() {
    }

    /**
     * 程序员初始化
     *
     * @param name     姓名
     * @param age      年龄
     * @param salary   薪资
     * @param language 语言
     * @param type     类型
     */
    public Programmer(String name, int age, int salary, String language,
                      String type) {
        super(name, age, salary, DEPT);
        this.language = language;
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 根据计算当月奖金
     *
     * @param overtime 加班次数
     * @return 当月奖金，保留两位小数
     */
    public String getBonus(int overtime) {
        if (overtime < 0) {
            throw new IllegalArgumentException("Overtime illegal!");
        }
        BonusType bonusType = BonusType.fromString(type);
        double bonus = bonusType.bonus(salary, overtime);
        return SALARY_DF.format(bonus);
    }

    @Override
    public String show() {
        return String.format("My name is %s ; age : %d ; language : %s ; salary : %d.", name, age, language, salary);
    }


    /**
     * 信息隐藏
     * <p>
     * 为了保护用户的隐私，系统需要将用户号码和邮箱隐藏起来。 对输入的邮箱或电话号码进行加密。 commnet
     * 可能是一个邮箱地址，也可能是一个电话号码。
     * <p>
     * 1. 电子邮箱 邮箱格式为 str1@str2
     * 电子邮箱的名称str1是一个长度大于2.并且仅仅包含大小写字母和数字的字符串，名称str1后紧接符号@
     * 最后是邮箱所在的服务器str2,str2中可能包含多个. 如qq.com smail.nju.edu.cn等 邮箱地址是有效的，一个正确的示例为:
     * str1@smail.nju.edu.cn 为了隐藏电子邮箱，所有的str1和str2中的字母必须被转换成小写的，
     * 并且名称str1的第一个字和最后一个字的中间的所有字由 5 个 '*' 代替。 如果邮箱中含有非法字符或格式不正确，则返回illegal
     * <p>
     * 示例：
     * <p>
     * comment: "Qm@Qq.com"
     * <p>
     * return: "q*****m@qq.com"
     * <p>
     * 2. 电话号码 电话号码是一串包括数字 0-9，以及 {'+', '-', '(', ')', ' '} 这几个字符的字符串。
     * 你可以假设电话号码包含 10 到 13 个数字。 电话号码的最后 10 个数字组成本地号码，在这之前的数字组成国际号码。
     * 国际号码是可选的。我们只暴露最后 4 个数字并隐藏所有其他数字。 本地号码有格式，并且如 "***-***-1111" 这样显示，
     * 为了隐藏有国际号码的电话号码，像 "+111 111 111 1111"，我们以 "+***-***-***-1111"
     * 的格式来显示。在本地号码前面的 '+' 号 和第一个 '-' 号仅当电话号码中包含国际号码时存在。 例如，一个 12 位的电话号码应当以
     * "+**-" 开头进行显示。 注意：像 "("，")"，" " 这样的不相干的字符以及不符合上述格式的额外的减号或者加号都应当被删除。
     * 示例1:
     * <p>
     * comment: "1(234)567-890"
     * <p>
     * return: "***-***-7890"
     * <p>
     * 示例2:
     * <p>
     * comment: "86-(10)12345678"
     * <p>
     * return: "+**-***-***-5678"
     *
     * @param comment
     */
    public String hideUserinfo(String comment) {
        String pattern = ".*@.*";
        boolean isEmail = Pattern.matches(pattern, comment);
        if (isEmail) {
            return handleEmail(comment.toLowerCase());
        } else {
            return handlePhoneNum(comment);
        }
    }

    /**
     * @param email 待处理的邮箱
     * @return 信息隐藏的邮箱
     */
    private String handleEmail(String email) {
        if (VALID_EMAIL_ADDRESS_REGEX.matcher(email).matches()) {
            int atIndex = email.indexOf('@');
            String str1 = email.substring(0, atIndex);
            String str2 = email.substring(atIndex + 1);
            String hiddenStr1 = str1.charAt(0) + "*****" + str1.charAt(str1.length() - 1);
            return hiddenStr1 + '@' + str2;
        } else {
            // 邮箱格式不正确
            return "illegal";
        }
    }

    /**
     * 电话号码包含 10 个数字 => 本地号码 => ***-***-1111
     * 电话号码数字大于 10 个数字 => 国际号码 => +[*]-***-***-1111
     *
     * @param phoneNum 待处理的手机号
     * @return 信息隐藏的手机号
     */
    private String handlePhoneNum(String phoneNum) {
        ArrayList<Character> phoneNumList = new ArrayList<>();
        for (Character c : phoneNum.toCharArray()) {
            if (c >= '0' && c <= '9') {
                phoneNumList.add(c);
            }
        }

        return phoneNumList.size() > 10 ? hidePhoneNumber(phoneNumList, false) : hidePhoneNumber(phoneNumList, true);
    }

    private String hidePhoneNumber(List<Character> phoneNumList, boolean isLocal) {
        char hyphen = '-';
        List<Character> hiddenPhoneNumRepreList = new LinkedList<>();
        for (int i = phoneNumList.size() - 1; i >= phoneNumList.size() - 4; i--) {
            hiddenPhoneNumRepreList.add(0, phoneNumList.get(i));
        }
        hiddenPhoneNumRepreList.add(0, hyphen);


        int curPartLen = 0;
        for (int i = phoneNumList.size() - 5; i >= 0; i--) {
            hiddenPhoneNumRepreList.add(0, '*');
            if (curPartLen < 2) {
                curPartLen++;
            } else {
                curPartLen = 0;
                if (i != 0) {
                    hiddenPhoneNumRepreList.add(0, hyphen);
                }
            }
        }

        if (!isLocal) {
            hiddenPhoneNumRepreList.add(0, '+');
        }

        StringBuilder sb = new StringBuilder(hiddenPhoneNumRepreList.size());
        for (Character c : hiddenPhoneNumRepreList) {
            sb.append(c);
        }
        return sb.toString();
    }

}