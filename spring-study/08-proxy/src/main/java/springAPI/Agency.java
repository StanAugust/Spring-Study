package springAPI;

public class Agency implements Rent {

    private HouseHolder holder;

    // constructor
    public Agency() {
    }
    public Agency(HouseHolder holder) {
        this.holder = holder;
    }

    @Override
    public void rent() {
        visit();
        holder.rent();
        signContract();
        collectFees();
    }

    private void visit(){
        System.out.println("中介带着参观房子");
    }

    private void collectFees(){
        System.out.println("收取中介费");
    }

    private void signContract(){
        System.out.println("签订合同");
    }
}
