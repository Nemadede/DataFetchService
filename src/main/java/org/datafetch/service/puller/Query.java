package org.datafetch.service.puller;

import com.google.gson.Gson;
import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.exception.AuthenticationException;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.security.OAuth2Authorizer;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.services.QueryResult;
import com.intuit.ipp.util.Config;
import com.intuit.oauth2.exception.OAuthException;
import org.datafetch.service.controllers.QBApiController;
import org.datafetch.service.dao.UserDOA;
import org.datafetch.service.schema.*;
import org.datafetch.service.utils.Queue;
import org.datafetch.service.utils.ReadPropertyFile;

import java.util.ArrayList;
import java.util.Map;

public class Query implements Runnable {

    User user;
    String modelName;
    volatile DataService service;
    private Queue queue = Queue.getInstance();

    public Query(User user, String modelName){
        this.user = user;
        this.modelName = modelName;
    }

    @Override
    public void run() {
        String sql = "select * from " + modelName;
        try {
            System.out.println("########### Model: "+ modelName + " is executed");

            service = getDataService(user.getRealmId(), user.getToken());
            QueryResult queryResult = service.executeQuery(sql);
            processResponse(queryResult);

        }catch (AuthenticationException e){
            synchronized (service){
                System.out.println("In the Synchronised for Invalid Token \n");
                try {

                    Map<String, Object> map = new QBApiController().refreshToken(user.getRefreshToken());
                    service = getDataService(user.getRealmId(), (String) map.get("token"));
                    map.put("Id", user.getId());
                    QueryResult queryResult = service.executeQuery(sql);
                    new UserDOA().tokenUpdate(map);
                    processResponse(queryResult);

                } catch (OAuthException | FMSException oAuthException) {
                    oAuthException.printStackTrace();

                }

            }
        }
        catch (FMSException e) {
            e.printStackTrace();
        }

    }



    private DataService getDataService(String realmId, String accessToken) throws FMSException {
        String url = ReadPropertyFile.getProp().getProperty("baseUrl") +"/v3/company";

        Config.setProperty(Config.BASE_URL_QBO, url);
        OAuth2Authorizer oauth = new OAuth2Authorizer(accessToken);

        Context context = new Context(oauth, ServiceType.QBO, realmId);

        return new DataService(context);
    }

    private void processResponse(QueryResult queryResult){
        for (Object q: queryResult.getEntities()){
            Gson gson = new Gson();
            String jsonString = gson.toJson(q);
            switchModels(jsonString);
        }

    }

    private void switchModels( String data){

        Gson gson = new Gson();
        CompositeId compositeId = new CompositeId();

        compositeId.setUser_id(user.getId());
        switch (modelName){
            case "Account":
                Accounts account = gson.fromJson(data, Accounts.class);
                compositeId.setId(account.getId());
                account.setUser(user);
                account.setCompositeId(compositeId);
                queue.enqueue(account);
                break;

            case "Bill":
                Bills bill = gson.fromJson(data, Bills.class);
                bill.setUser(user);
                compositeId.setId(bill.getId());
                bill.setCompositeId(compositeId);
                queue.enqueue(bill);
                break;

            case "BillPayment":
                BillPayments billPayment =gson.fromJson(data, BillPayments.class);
                billPayment.setUser(user);
                compositeId.setId(billPayment.getId());
                billPayment.setCompositeId(compositeId);
                queue.enqueue(billPayment);
                break;

            case "Customer":
                Customers customer = gson.fromJson(data, Customers.class);
                customer.setUser(user);
                compositeId.setId(customer.getId());
                customer.setCompositeId(compositeId);
                queue.enqueue(customer);
                break;

            case "Invoice":
                Invoices invoice = gson.fromJson(data, Invoices.class);
                invoice.setUser(user);
                compositeId.setId(invoice.getId());
                invoice.setCompositeId(compositeId);
                queue.enqueue(invoice);
                break;

            case "Payment":
                Payments payment = gson.fromJson(data, Payments.class);
                payment.setUser(user);
                compositeId.setId(payment.getId());
                payment.setCompositeId(compositeId);
                queue.enqueue(payment);
                break;

            case "PaymentMethod":
                PaymentMethods paymentMethod = gson.fromJson(data, PaymentMethods.class);
                paymentMethod.setUser(user);
                compositeId.setId(paymentMethod.getId());
                paymentMethod.setCompositeId(compositeId);
                queue.enqueue(paymentMethod);
                break;

            case "RefundReceipt":
                RefundReceipts refundReceipt = gson.fromJson(data, RefundReceipts.class);
                refundReceipt.setUser(user);
                compositeId.setId(refundReceipt.getId());
                refundReceipt.setCompositeId(compositeId);
                queue.enqueue(refundReceipt);
                break;

            case "SalesReceipt":
                SalesReceipts salesReceipt = gson.fromJson(data, SalesReceipts.class);
                salesReceipt.setUser(user);
                compositeId.setId(salesReceipt.getId());
                salesReceipt.setCompositeId(compositeId);
                queue.enqueue(salesReceipt);
                break;

            case "Vendor":
                Vendors vendor = gson.fromJson(data, Vendors.class);
                vendor.setUser(user);
                compositeId.setId(vendor.getId());
                vendor.setCompositeId(compositeId);
                queue.enqueue(vendor);
                break;

            case "VendorCredit":
                VendorCredits vendorCredit = gson.fromJson(data, VendorCredits.class);
                vendorCredit.setUser(user);
                compositeId.setId(vendorCredit.getId());
                vendorCredit.setCompositeId(compositeId);
                queue.enqueue(vendorCredit);
                break;

            default:
                System.out.println("In the Default of the Switch function");
                return;
        }

    }

}
