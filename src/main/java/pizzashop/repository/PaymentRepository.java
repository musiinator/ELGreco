package pizzashop.repository;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class PaymentRepository implements IPaymentRepository{
    private static final String filename = "data/payments.txt";
    private List<Payment> paymentList;

    public PaymentRepository(){
        this.paymentList = new ArrayList<>();
        readPayments();
    }

    private void readPayments() {
        ClassLoader classLoader = PaymentRepository.class.getClassLoader();
        try {
            File file = new File(URLDecoder.decode(Objects.requireNonNull(classLoader.getResource(filename)).getFile(), StandardCharsets.UTF_8.name()));
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                Payment payment = getPayment(line);
                if (payment != null) {
                    paymentList.add(payment);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Payment getPayment(String line){
        Payment item=null;
        if (line==null|| line.equals("")) return null;
        StringTokenizer st=new StringTokenizer(line, ",");
        int tableNumber= Integer.parseInt(st.nextToken());
        String type= st.nextToken();
        double amount = Double.parseDouble(st.nextToken());
        item = new Payment(tableNumber, PaymentType.valueOf(type), amount);
        return item;
    }

    @Override
    public void add(Payment payment){
        paymentList.add(payment);
        writeAll();
    }

    @Override
    public List<Payment> getAll(){
        return paymentList;
    }

    @Override
    public void writeAll(){
        ClassLoader classLoader = PaymentRepository.class.getClassLoader();
        BufferedWriter bw;
        try {
            File file = new File(URLDecoder.decode(Objects.requireNonNull(classLoader.getResource(filename)).getFile(), StandardCharsets.UTF_8.name()));
            bw = new BufferedWriter(new FileWriter(file));
            for (Payment p:paymentList) {
                System.out.println(p.toString());
                bw.write(p.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
