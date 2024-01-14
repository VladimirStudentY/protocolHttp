package my.project.json;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

/**{
        "id": "5b4910ae0508220014ccfe90",
        "text": "Кошки могут слышать ультразвук и коммуницировать с дельфинами.",
        "type": "cat",
        "user": "Alex Petrov",
        "upvotes": 12
       }
 */


@Getter
@ToString

public class MailRequest {
    private final String id;    //* id - уникальный идентификатор записи
    private final String text;  //text - сообщение
    private final String type;  //type - тип животного
    private final String user;  //user - имя пользователя
    private final Integer upvotes;  //upvotes - голоса


    public MailRequest(@JsonProperty("id") String id, @JsonProperty("text") String text,
                       @JsonProperty("type") String type, @JsonProperty("user") String user,
                       @JsonProperty("upvotes") Integer upvotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }


}
