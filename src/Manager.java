import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 管理人员
 */
public class Manager extends Worker {

    /**
     * 领导的队伍
     */
    private List<Worker> worker;

    public Manager() {

    }

    /**
     * 初始化管理人员
     *
     * @param name       姓名
     * @param age        年龄
     * @param salary     工资
     * @param department 部门
     */
    public Manager(String name, int age, int salary, String department) {
        super(name, age, salary, department);
        this.worker = new ArrayList<>();
        lead(this);
    }

    /**
     * 管理人员可以查询本部门员工的基本信息，跨部门查询提示权限不足，提示“Access Denied!”
     *
     * @param e 员工
     * @return 员工基本信息
     */
    public String inquire(Worker e) {
        if (hasAuthority(e.getDepartment())) {
            return e.show();
        } else {
            throw new IllegalArgumentException("Access denied!");
        }
    }

    /**
     * 管理人员给自己的队伍添加工作人员，同一部门的工作人员可以添加，并返回true，不同部门的工作人员无法添加，返回false
     *
     * @param e worker
     * @return 添加是否成功
     */
    public boolean lead(Worker e) {
        boolean result = false;
        if (hasAuthority(e.getDepartment())) {
            worker.add(e);
            result = true;
        }
        return result;
    }

    /**
     * 打印自己队伍的人员姓名，没有打印“Empty”
     *
     * @return 自己队伍的人员姓名字符串
     */
    public String print() {
        if (worker.isEmpty()) {
            return "Empty";
        } else {
            List<String> nameList = worker.stream().map(Worker::getName).collect(Collectors.toList());
            String nameStr = String.join("\n - ", nameList);
            return "Statement for " + nameStr;
        }
    }

    /**
     * 判断该管理人员是否有权限
     *
     * @param workerDept 员工所属部门
     * @return 该管理员是否有权限
     */
    private boolean hasAuthority(String workerDept) {
        return this.department.equals(workerDept);
    }

}
