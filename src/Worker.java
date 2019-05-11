/**
 * 所有职员父类
 */
public class Worker {

    /**
     * 限制年龄
     */
    private static final int AGE_LIMIT = 18;
    /**
     * 限制工资
     */
    private static final int SALARY_LIMIT = 2000;

    /**
     * 姓名
     */
    protected String name;
    /**
     * 年龄
     */
    protected int age;
    /**
     * 基本薪资
     */
    protected int salary;
    /**
     * 部门
     */
    protected String department;

    public Worker() {

    }

    /**
     * @param name       姓名
     * @param age        年龄
     * @param salary     工资
     * @param department 部门
     * @throws IllegalArgumentException 当年龄小于18或工资低于2000时，抛出异常
     */
    public Worker(String name, int age, int salary, String department) {
        this.name = name;
        this.age = age;
        if (age < AGE_LIMIT || salary < SALARY_LIMIT) {
            throw new IllegalArgumentException("age must be greater than 18 and salary must be greater than 2000.");
        }
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * 展示员工信息
     *
     * @return 基本信息
     */
    public String show() {
        return String.format("My name is %s ; age : %d ; salary : %d ; department : %s.", name, age, salary, department);
    }
}
