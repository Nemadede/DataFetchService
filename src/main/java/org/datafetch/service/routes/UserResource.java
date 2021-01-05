package org.datafetch.service.routes;

import com.intuit.oauth2.exception.InvalidRequestException;
import org.datafetch.service.controllers.JobController;
import org.datafetch.service.dao.UserDOA;
import org.datafetch.service.controllers.QBApiController;
import org.datafetch.service.schema.OAuthResponse;
import org.datafetch.service.schema.User;
import org.datafetch.service.utils.ReadPropertyFile;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


@Path("user")
@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {



    @GET
    public Response redirect(){
        try {
            URI url = new URI(ReadPropertyFile.getProp().getProperty("url"));
            return Response.temporaryRedirect(url).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }


    @GET
    @Path("connect")
    @Produces(MediaType.TEXT_PLAIN)
    public String url(){
        try {
            String link = QBApiController.generateOauthUrl();
            return link;
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }
        return " ";
    }


    @POST
    @Path("signup")
    @Produces(MediaType.TEXT_PLAIN)
    public String create_user(User user){
        System.out.println("Hello User");
        try{
            String res = new UserDOA().createNewUser(user);
            return res;
        } catch (Exception e){
            e.printStackTrace();
        }
        return "{}";
    }



    @GET
    @Path("quickbooks")
    public OAuthResponse data(@QueryParam("code") String code, @QueryParam("realmId") String realmId,
                              @QueryParam("state") String state){

        OAuthResponse oAuthResponse = new OAuthResponse();
        oAuthResponse.setRealmId(realmId);
        oAuthResponse.setCode(code);
        oAuthResponse.setState(state);

        return oAuthResponse;
    }



    @POST
    @Path("quickbooks/{userId}/token")
    @Produces(MediaType.TEXT_PLAIN)
    public String getToken(@PathParam("userId") Integer userId, OAuthResponse in_data){

        Map<String, Object> data = new HashMap<>();

        data.put("code", in_data.getCode());
        data.put("realmId", in_data.getRealmId());
        data.put("state", in_data.getState());
        data.put("Id", userId);

        User user = new UserDOA().UpdateUser(data);

        JobController jobController = new JobController();
        jobController.userJob(user);

        return user.toString();

    }


}
