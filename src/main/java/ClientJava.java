import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;

public class ClientJava {

    public static WebTarget target;

    public static void initTarget(){
        System.setProperty(
                ClientBuilder.JAXRS_DEFAULT_CLIENT_BUILDER_PROPERTY,
                "org.apache.cxf.jaxrs.client.spec.ClientBuilderImpl"
        );
        Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
        target = client.target("http://localhost:9001/data");
    }

    public static Data getData(int ID){
        target.queryParam("ID", ID);
        Invocation.Builder builder = target.request();
        return builder.get(Data.class);
    }

    public static void postData(String nom, int date){
        Form form = new Form().param("nom", nom).param("date", Integer.toString(date));
        Invocation.Builder builder = target.request();
        int response = builder.post(Entity.form(form), Integer.class);
        if(response != 201){
            System.out.println("Post failed.");
        }
    }

    public static void putData(int ID, String nom, int date){
        target.queryParam("ID", ID);
        Form form = new Form().param("nom", nom).param("date", Integer.toString(date));
        Invocation.Builder builder = target.request();
        int response = builder.put(Entity.form(form), Integer.class);
        if(response == 404){
            System.out.println("ID not found.");
        }
        else if(response != 201){
            System.out.println("Unknown error.");
        }
    }

    public static void deleteData(int ID){
        target.queryParam("ID", ID);
        Invocation.Builder builder = target.request();
        int response = builder.delete(Integer.class);
        if(response == 404){
            System.out.println("ID not found");
        }
        else if(response != 200){
            System.out.println("Unknown error");
        }
    }

    public static void main(String[] args) {
        initTarget();
        postData("PetitOursBrun", 9999);
        postData("Ouioui", 8888);
        postData("ToChange", 77777);
        putData(2,"Changed", 7788);
        System.out.println(getData(1));
        deleteData(1);
    }

}
