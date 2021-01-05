package org.datafetch.service;

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
import org.datafetch.service.dao.QBSchemaDoa;
import org.datafetch.service.dao.UserDOA;
import org.datafetch.service.controllers.QBApiController;
import org.datafetch.service.schema.*;
import org.datafetch.service.utils.ReadPropertyFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Queries {

    public DataService getDataService(String realmId, String accessToken) throws FMSException {
        String url = ReadPropertyFile.getProp().getProperty("baseUrl") +"/v3/company";

        Config.setProperty(Config.BASE_URL_QBO, url);
        OAuth2Authorizer oauth = new OAuth2Authorizer(accessToken);

        Context context = new Context(oauth, ServiceType.QBO, realmId);

        return new DataService(context);
    }

    public void processResponse(QueryResult queryResult, User user, String modelName){

        for (Object q : queryResult.getEntities()){
            Gson gson = new Gson();
            String jsonString = gson.toJson(q);

            new QBSchemaDoa().saveObject(switchModels(modelName,jsonString, user));

            System.out.println(switchModels(modelName,jsonString, user));
        }



    }

    private Object switchModels(String modelName, String data, User user){
        Gson gson = new Gson();
        switch (modelName){
            case "Account":
                Accounts account = gson.fromJson(data, Accounts.class);
                account.setUser(user);
                return account;

            case "Bill":
                Bills bill = gson.fromJson(data, Bills.class);
                bill.setUser(user);
                return bill;

            case "BillPayment":
                BillPayments billPayment =gson.fromJson(data, BillPayments.class);
                billPayment.setUser(user);
                return billPayment;

            case "Customer":
                Customers customer = gson.fromJson(data, Customers.class);
                customer.setUser(user);
                return customer;

            case "Invoice":
                Invoices invoice = gson.fromJson(data, Invoices.class);
                invoice.setUser(user);
                return invoice;

            case "Payment":
                Payments payment = gson.fromJson(data, Payments.class);
                payment.setUser(user);
                return payment;

            case "PaymentMethod":
                PaymentMethods paymentMethod = gson.fromJson(data, PaymentMethods.class);
                paymentMethod.setUser(user);
                return paymentMethod;

            case "RefundReceipt":
                RefundReceipts refundReceipt = gson.fromJson(data, RefundReceipts.class);
                return refundReceipt;

            case "SalesReceipt":
                SalesReceipts salesReceipt = gson.fromJson(data, SalesReceipts.class);
                salesReceipt.setUser(user);
                return salesReceipt;

            case "Vendor":
                Vendors vendor = gson.fromJson(data, Vendors.class);
                vendor.setUser(user);
                return vendor;

            case "VendorCredit":
                VendorCredits vendorCredit = gson.fromJson(data, VendorCredits.class);
                vendorCredit.setUser(user);
                return vendorCredit;

            default:
                System.out.println("In the Default of the Switch function");
                return null;
        }

    }



    public void testQuery(Integer id){
        System.out.println("In the Test Query method \n");
        User user = new UserDOA().readUser(id);

        List<String> modelNames = new ArrayList(Arrays.asList("Account","Bill","BillPayment","Customer","Invoice", "Payment",
         "PaymentMethod", "RefundReceipt", "SalesReceipt","Vendor","VendorCredit" ));

            for(String modelName : modelNames) {
                String sql = "select * from " + modelName;
                try {
                    DataService service = getDataService((String) user.getRealmId(), (String) user.getToken());

                    QueryResult queryResult = service.executeQuery(sql);
                    processResponse(queryResult, user, modelName);
                } catch (AuthenticationException e) {
                    try {
                        System.out.println("In the Try Section for Invalid Token \n");
                        Map<String, Object> map = new QBApiController().refreshToken(user.getRefreshToken());

                        DataService service = getDataService(user.getRealmId(), (String) map.get("token"));

                        map.put("Id", id);

                        QueryResult queryResult = service.executeQuery(sql);

                        new UserDOA().tokenUpdate(map);

                        processResponse(queryResult, user,modelName);


                    } catch (OAuthException oAuthException) {
                        oAuthException.printStackTrace();
                    } catch (FMSException fmsException) {
                        fmsException.printStackTrace();
                    }
                } catch (FMSException e) {
                    e.printStackTrace();
                }

            }
    }
}
