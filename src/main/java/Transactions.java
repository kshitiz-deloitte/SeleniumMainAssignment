
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Transactions {
    List<String> type;
    List<Double> amount;
    Transactions(){
        type = new ArrayList<>();
        amount = new ArrayList<>();
    }

    public List<String> getType() {
        return type;
    }

    public void setType(String type) {
        this.type.add(type);
    }

    public List<Double> getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.add(amount);
    }
}
