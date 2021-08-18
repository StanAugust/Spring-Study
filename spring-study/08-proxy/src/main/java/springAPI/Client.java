package springAPI;

public class Client {
    public static void main(String[] args) {
        // 真实角色
        HouseHolder houseHolder = new HouseHolder();
        // 代理角色
        Agency agency = new Agency(houseHolder);
        // 客户是通过代理角色完成相关操作的
        agency.rent();
    }
}
