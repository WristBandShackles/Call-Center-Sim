public class SupportSession implements Comparable<SupportSession> {
    private Customer customer;
    private Tech tech;
    private double callLength;
    private double callFinish;

    public SupportSession(Customer customer, Tech tech, double callLength, double callFinish) {
        this.customer = customer;
        this.tech = tech;
        this.callLength = callLength;
        this.callFinish = callFinish;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Tech getTech() {
        return this.tech;
    }

    public double getCallLength() {
        return this.callLength;
    }

    public double getCallFinish() {
        return this.callFinish;
    }

    @Override
    public int compareTo(SupportSession other) {
        return (int) Math.round(this.callFinish - other.getCallFinish());
    }

    public String toString() {
        return "Customer: " + customer.getIdNumber() + ", Tech: " + tech.getIdNumber() + ", Length: " + Math.round(callLength) + ", Finished at: " + Math.round(callFinish);
    }
}
