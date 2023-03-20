import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class Main {
    // A simple sendMessageToDownstreamSystem function to make the code run
    public static void sendMessageToDownstreamSystem(Record r){
        System.out.println("Sending " + r.getCustomer().getName() + "'s info to downstream");
    }

    public static void processRecord(Record r) {
        log.info("Sending customer information downstream: " + r.getCustomer().getName());
        sendMessageToDownstreamSystem(r);
    }

    //Check if the statesToProcess contains a different jurisdiction instance with the same name as a jurisdiction
    public static boolean containJurisdiction(Set<Jurisdiction> statesToProcess, Jurisdiction jurisdiction){
        for(Jurisdiction state: statesToProcess){
            if(state.getJurisdiction().equals(jurisdiction.getJurisdiction())){
                return true;
            }
        }
        return false;
    }

    public static void process(Set<Jurisdiction> statesToProcess, List<Record> records) {
        records.stream()
                .filter(record -> containJurisdiction(statesToProcess, record.getJurisdiction()))
                .forEach(record -> processRecord(record));
    }

    public static void main(String[] args){
        Jurisdiction jurisdiction1 = new Jurisdiction("jurisdiction1");
        Jurisdiction jurisdiction2 = new Jurisdiction("jurisdiction2");
        Jurisdiction jurisdiction3 = new Jurisdiction("jurisdiction3");

        Jurisdiction sameNameJurisdiction1 = new Jurisdiction("jurisdiction1");
        Jurisdiction sameNameJurisdiction2 = new Jurisdiction("jurisdiction2");

        //Put different jurisdiction instances with same names
        Set<Jurisdiction> statesToProcess = new HashSet<>();
        statesToProcess.add(sameNameJurisdiction1);
        statesToProcess.add(sameNameJurisdiction2);

        Customer customer1 = new Customer("customer1");
        Customer customer2 = new Customer("customer2");
        Customer customer3 = new Customer("customer3");

        Record record1 = new Record(customer1, jurisdiction1);
        Record record2 = new Record(customer2, jurisdiction2);
        Record record3 = new Record(customer3, jurisdiction3);

        List<Record> records = new ArrayList<>();
        records.add(record1);
        records.add(record2);
        records.add(record3);

        process(statesToProcess, records);
    }
}
