package cjy.guava;

/**
 * @author Mikhail Baturov | www.yetanothercoder.ru
 */
public class GuavaTester {
   /* public static void main(String args[]) throws InterruptedException {
        // create a cache for employees based on their employee id
        LoadingCache<String, Employee> employeeCache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite // maximum
        (3000, TimeUnit.MILLISECONDS) // cache will expire after 30 minutes of access
                .build(new CacheLoader<String, Employee>() { // build the cacheloader
                    public Employee load(String empId) throws Exception {
                        // make the expensive call
                        return getFromDatabase(empId);
                    }
                });
        try {
            // on first invocation, cache will be populated with corresponding
            // employee record
            System.out.println("Invocation #1");
            System.out.println(employeeCache.get("100"));
            System.out.println(employeeCache.get("103"));
            System.out.println(employeeCache.get("110"));
            // second invocation, data will be returned from cache
            Thread.sleep(1000);
            System.out.println("Invocation #2");
            employeeCache.invalidate("100");
            System.out.println(employeeCache.get("100"));
            Thread.sleep(3000);
            System.out.println(employeeCache.get("100"));
            System.out.println(employeeCache.get("103"));
            System.out.println(employeeCache.get("110"));
            System.out.println("Invocation #3");
            // employeeCache.invalidateAll();
            System.out.println(employeeCache.get("100"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static Employee getFromDatabase(String empId) {
        Employee e1 = new Employee("Mahesh", "Finance", "100");
        Employee e2 = new Employee("Rohan", "IT", "103");
        Employee e3 = new Employee("Sohan", "Admin", "110");
        Map<String, Employee> database = new HashMap<String, Employee>();
        database.put("100", e1);
        database.put("103", e2);
        database.put("110", e3);
        System.out.println("Database hit for" + empId);
        return database.get(empId);
    }
}

class Employee {
    String name;
    String dept;
    String emplD;

    public Employee(String name, String dept, String empID) {
        this.name = name;
        this.dept = dept;
        this.emplD = empID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getEmplD() {
        return emplD;
    }

    public void setEmplD(String emplD) {
        this.emplD = emplD;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(Employee.class).add("Name", name).add("Department", dept).add("Emp Id", emplD)
                .toString();
    }*/
}
