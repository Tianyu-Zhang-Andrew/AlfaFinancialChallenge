public class Record {
    private final Customer customer;
    private final Jurisdiction jurisdiction;

    public Record(Customer customer, Jurisdiction jurisdiction) {
        this.jurisdiction = jurisdiction;
        this.customer = customer;
    }
    public Jurisdiction getJurisdiction() {
        return jurisdiction;
    }
    public Customer getCustomer() {
        return customer;
    }
}
