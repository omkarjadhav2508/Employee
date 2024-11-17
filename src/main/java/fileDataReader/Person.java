package fileDataReader;

import java.util.List;

public record Person(String name,int age,Address address,List<Documents> documents) {

}
