package kyungjoon.lemon.crud_login12345.client;

import com.mongodb.lang.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Client")
public class Client {

    @Id
    private String id;
    private String fullName;
    private String nickname;
    private int age;

    private String createdDt;

    @Nullable
    private Object features;





    @Nullable
    public Object getFeatures() {
        return features;
    }

    public String getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(String createdDt) {
        this.createdDt = createdDt;
    }

    public void setFeatures(@Nullable Object features) {
        this.features = features;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
