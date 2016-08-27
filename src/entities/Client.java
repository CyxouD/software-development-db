package entities;

import java.util.List;

/**
 * Created by Dima on 25.04.2016.
 */
public class Client extends Entity{
    private String clientName;
    private Double payment;

    public Client(List<Object> list) {
        super(list);
    }

    public String getClientName() {
        return clientName;
    }

    public Double getPayment() {
        return payment;
    }

    @Override
    public void createObjectFromList(List<Object> list) {
        clientName = list.get(0).equals("")? null : (String) list.get(0);
        payment = list.get(1).equals("")? null : Double.parseDouble((String) list.get(1));
    }
}
