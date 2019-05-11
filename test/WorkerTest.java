//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class WorkerTest {
    String sep;
    PrintStream console = null;
    ByteArrayOutputStream out = null;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public WorkerTest() {
    }

    @Before
    public void setUp() throws Exception {
        this.out = new ByteArrayOutputStream();
        this.console = System.out;
        System.setOut(new PrintStream(this.out));
        this.sep = System.getProperty("line.separator");
    }

    @After
    public void tearDown() throws Exception {
        this.out.close();
        System.setOut(this.console);
    }

    @Test(
            timeout = 4000L
    )
    public void test1() {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("age must be greater than 18 and salary must be greater than 2000.");
        new Programmer("p", 17, 8000, "Java", "UI");
    }

    @Test(
            timeout = 4000L
    )
    public void test3() {
        Manager m = new Manager("a", 19, 10000, "Editor");
        Editor s = new Editor("s", 24, 8000);
        Assert.assertEquals("My name is s ; age : 24 ; salary : 8000 ; department : Editor.", m.inquire(s));
    }

    @Test(
            timeout = 4000L
    )
    public void test4() {
        Manager m = new Manager("a", 19, 10000, "Programmer");
        Editor s = new Editor("s", 21, 6000);
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Access denied!");
        m.inquire(s);
    }

    @Test(
            timeout = 4000L
    )
    public void test5() {
        Manager m = new Manager("a", 19, 10000, "Programmer");
        Editor s = new Editor("s", 24, 4000);
        Assert.assertFalse(m.lead(s));
    }

    @Test(
            timeout = 4000L
    )
    public void test6() {
        Manager m = new Manager("a", 19, 10000, "Editor");
        Editor s = new Editor("s", 24, 4000);
        Assert.assertTrue(m.lead(s));
    }

    @Test(
            timeout = 4000L
    )
    public void test7() {
        Manager m = new Manager("a", 29, 24000, "Editor");
        Assert.assertEquals("Empty", m.print());
    }

    @Test(
            timeout = 4000L
    )
    public void test8() {
        Manager m = new Manager("any", 19, 10000, "Editor");
        Editor s1 = new Editor("sec1", 24, 4000);
        Editor s2 = new Editor("sec2", 25, 7000);
        m.lead(s1);
        m.lead(s2);
        Assert.assertEquals("Statement for any\n - sec1\n - sec2", m.print());
    }

    @Test(
            timeout = 4000L
    )
    public void test10() {
        Programmer p = new Programmer("p", 21, 8000, "Java", "Test");
        Assert.assertEquals("2,200.00", p.getBonus(7));
    }

    @Test
    public void test2() {
        Accountant a = new Accountant("p", 21, 8000, "Helloworld");
        Assert.assertEquals(1L, (long)a.checkPassword());
    }

    @Test
    public void test11() {
        Accountant a = new Accountant("p", 21, 8000, "********");
        Assert.assertEquals(8L, (long)a.checkPassword());
    }

    @Test
    public void test12() {
        Accountant a = new Accountant("p", 21, 8000, "132****");
        Assert.assertEquals(7L, (long)a.checkPassword());
    }

    @Test
    public void test13() {
        Accountant a = new Accountant("p", 21, 8000, "IamOK");
        Assert.assertEquals("One Hundred Eleven", a.numberToWords("111"));
    }

    @Test
    public void test14() {
        Accountant a = new Accountant("p", 21, 8000, "IamOK");
        Assert.assertEquals("Two Billion One Hundred Thirty Two Million Eight Hundred Sixty Six Thousand Eight Hundred Forty Two", a.numberToWords("2132866842"));
    }

    @Test
    public void test15() {
        Accountant a = new Accountant("p", 21, 8000, "IamOK");
        Assert.assertEquals("illegal", a.numberToWords("abc"));
    }

    @Test
    public void test16() {
        Accountant a = new Accountant("p", 21, 8000, "IamOK");
        Assert.assertEquals("illegal", a.numberToWords("888888888888888888"));
    }

    @Test(
            timeout = 4000L
    )
    public void test17() {
        Editor e = new Editor("e", 21, 8000);
        String newsContent = "说到平安祥和稳定，今天的中国，呈现给世界的不仅有波澜壮阔的改革发展图景，更有一以贯之的平安祥和稳定。这平安祥和稳定的背后，凝聚着中国治国理政的卓越智慧，也凝结着中国公安民警的辛勤奉献。";
        Assert.assertEquals("平安祥和稳定", e.findHotWords(newsContent));
    }

    @Test(
            timeout = 4000L
    )
    public void test18() {
        Editor e = new Editor("e", 21, 8000);
        String newsContent = "本次比赛由vivo赞助。凡取得一、二等奖团队的所有学生，可直接获得 vivo 暑期实习 offer，取得三等奖团队的所有学生，可免实习笔试及技术面试，直接参与 vivo 暑期实习终面。";
        Assert.assertEquals("vivo", e.findHotWords(newsContent));
    }

    @Test(
            timeout = 4000L
    )
    public void test19() {
        Editor e = new Editor("e", 21, 8000);
        String newsContent = "说到平安祥和稳定，今天的中国，呈现给世界的不仅有波澜壮阔的改革发展图景。";
        Assert.assertEquals("说到平安祥和稳定", e.findHotWords(newsContent));
    }

    @Test(
            timeout = 4000L
    )
    public void test20() {
        Editor e = new Editor("e", 21, 8000);
        String newsContent = " 对于非南京大学软件学院学生：进入决赛者可以免试参加暑期推免夏令营。";
        Assert.assertEquals("对于非南京大学软件学", e.findHotWords(newsContent));
    }

    @Test(
            timeout = 4000L
    )
    public void test21() {
        Editor e = new Editor("e", 21, 18000);
        String title1 = "我是冠军";
        String title2 = "我们又是冠军呀";
        Assert.assertEquals(57.14D, e.minDistance(title1, title2), 1.0E-7D);
    }

    @Test(
            timeout = 4000L
    )
    public void test22() {
        Editor e = new Editor("e", 21, 18000);
        String title1 = "我们是冠军";
        String title2 = "我们是冠军";
        Assert.assertEquals(100.0D, e.minDistance(title1, title2), 1.0E-7D);
    }

    @Test(
            timeout = 4000L
    )
    public void test23() {
        Editor e = new Editor("e", 21, 18000);
        String title1 = "天天打球";
        String title2 = "我们是冠军";
        Assert.assertEquals(0.0D, e.minDistance(title1, title2), 1.0E-7D);
    }

    @Test(
            timeout = 4000L
    )
    public void test24() {
        Editor e = new Editor("e", 21, 18000);
        String title1 = "我是军队迷";
        String title2 = "我们是冠军队员";
        Assert.assertEquals(42.86D, e.minDistance(title1, title2), 1.0E-7D);
    }

    @Test(
            timeout = 4000L
    )
    public void test25() {
        Editor e = new Editor("e", 21, 18000);
        ArrayList<String> newsList = new ArrayList();
        newsList.add("我是谁");
        newsList.add("我是我");
        ArrayList<String> finalList = new ArrayList();
        List list = Arrays.asList("我是谁", "我是我");
        finalList.addAll(list);
        Assert.assertEquals(finalList, e.newsSort(newsList));
    }

    @Test(
            timeout = 4000L
    )
    public void test26() {
        Editor e = new Editor("e", 21, 18000);
        ArrayList<String> newsList = new ArrayList();
        newsList.add("我");
        newsList.add("是");
        newsList.add("谁");
        ArrayList<String> finalList = new ArrayList();
        List list = Arrays.asList("是", "谁", "我");
        finalList.addAll(list);
        Assert.assertEquals(finalList, e.newsSort(newsList));
    }

    @Test(
            timeout = 4000L
    )
    public void test27() {
        Editor e = new Editor("e", 21, 18000);
        String data = "给定一段32个长度字符串，重新排版，使得每行有32个字符，并输出至控制台首行缩进，其余行数左对齐，每个短句不超过32个字符。";
        e.textExtraction(data);
        String ans = this.out.toString();
        Assert.assertEquals("    给定一段32个长度字符串，" + this.sep + "重新排版，使得每行有32个字符，" + this.sep + "并输出至控制台首行缩进，" + this.sep + "其余行数左对齐，" + this.sep + "每个短句不超过32个字符。" + this.sep, ans);
    }

    @Test(
            timeout = 4000L
    )
    public void test28() {
        Editor e = new Editor("e", 21, 18000);
        String data = "我，我我，我我我，我我我我，我我我我我，我我我我我我，我我我我我我我，我我我我我我我我，我我我我我我我我我。";
        e.textExtraction(data);
        String ans = this.out.toString();
        Assert.assertEquals("    我，我我，我我我，我我我我，" + this.sep + "我我我我我，我我我我我我，" + this.sep + "我我我我我我我，" + this.sep + "我我我我我我我我，" + this.sep + "我我我我我我我我我。" + this.sep, ans);
    }

    @Test(
            timeout = 4000L
    )
    public void test29() {
        Programmer p = new Programmer("p", 21, 8000, "Java", "UI");
        Assert.assertEquals("h*****a@smail.nju.edu.cn", p.hideUserinfo("haHaha@smail.nju.edu.cn"));
    }

    @Test(
            timeout = 4000L
    )
    public void test30() {
        Programmer p = new Programmer("p", 21, 8000, "Java", "UI");
        Assert.assertEquals("illegal", p.hideUserinfo("haHahasmail.nju.edu.cn"));
    }

    @Test(
            timeout = 4000L
    )
    public void test31() {
        Programmer p = new Programmer("p", 21, 8000, "Java", "UI");
        Assert.assertEquals("illegal", p.hideUserinfo("haHa!ha@smail.nju.edu.cn"));
    }

    @Test(
            timeout = 4000L
    )
    public void test32() {
        Programmer p = new Programmer("p", 21, 8000, "Java", "UI");
        Assert.assertEquals("1*****2@smail.nju.edu.cn", p.hideUserinfo("1h1aHaha2@smail.nju.edu.cn"));
    }

    @Test(
            timeout = 4000L
    )
    public void test33() {
        Programmer p = new Programmer("p", 21, 8000, "Java", "UI");
        Assert.assertEquals("illegal", p.hideUserinfo("ha哇ha@smail.nju.edu.cn"));
    }

    @Test(
            timeout = 4000L
    )
    public void test34() {
        Programmer p = new Programmer("p", 21, 8000, "Java", "UI");
        Assert.assertEquals("+*-***-***-7890", p.hideUserinfo("1(2234)567-890"));
    }
}
