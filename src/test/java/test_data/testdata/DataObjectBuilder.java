package test_data.testdata;

import com.google.gson.Gson;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataObjectBuilder {

    public static<T> T buildDataObjectFrom(String filelocation, Class<T> dataType){
        T data;
        String currentProjectLocation = System.getProperty("user.dir");
        String fileAbsolutepath = currentProjectLocation + filelocation;
        try(
                Reader jsonContentReader = Files.newBufferedReader(Paths.get(fileAbsolutepath));
                ){
            Gson gson = new Gson();
            data = gson.fromJson(jsonContentReader, dataType);
        }
        catch(Exception e){
            throw new RuntimeException("[ERROR] Error while reading to file " + fileAbsolutepath);
        }
        return data;


    }
}
